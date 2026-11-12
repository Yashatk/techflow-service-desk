using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Techflow
{
    public partial class Form1 : Form
    {
        public SysUser _currentUser;
        public SqlConnection _connection;

        private List<SysColumn> ticketColumns = new List<SysColumn>();

        public Form1()
        {
            InitializeComponent();
            this.Text = Application.ProductName + " v" + Application.ProductVersion;
            this.eMsg.Text = Properties.Resources.MsgWelcome;
            this.eMsgConnection.Text = Properties.Resources.MsgNotConnected;          
            InitializeTicketColumns();
        }

        private void InitializeTicketColumns()
        {
            ticketColumns.Add(new SysColumn { ColumnName = "Id", Header = "Id", Visible=true, Width = 50 });
            ticketColumns.Add(new SysColumn { ColumnName = "Subject", Header = "Assunto", Visible =true });
            ticketColumns.Add(new SysColumn { ColumnName = "Description", Header = "Descrição", Visible =false });
            ticketColumns.Add(new SysColumn { ColumnName = "StatusName", Header = "Status", Visible =true, Width = 100 });
            ticketColumns.Add(new SysColumn { ColumnName = "PriorityName", Header = "Prioridade", Visible =true, Width = 100 });
            ticketColumns.Add(new SysColumn { ColumnName = "CategoryName", Header = "Categoria", Visible =true, Width = 100 });
            ticketColumns.Add(new SysColumn { ColumnName = "UserFullName", Header = "Solicitante", Visible =false, Width = 150 });
            ticketColumns.Add(new SysColumn { ColumnName = "InsertDate", Header = "Abertura", Visible =true, Width = 150 });
            ticketColumns.Add(new SysColumn { ColumnName = "UpdateDate", Header = "Atualização", Visible =true, Width = 150 });
            ticketColumns.Add(new SysColumn { ColumnName = "Username", Header = "Usuário", Visible =false });
            ticketColumns.Add(new SysColumn { ColumnName = "UserEmail", Header = "E-mail", Visible =false });
            ticketColumns.Add(new SysColumn { ColumnName = "SupportUserFullName", Header = "Atendente", Visible =false });
            ticketColumns.Add(new SysColumn { ColumnName = "SupportUsername", Header = "Usuário Atendente", Visible =false });
            ticketColumns.Add(new SysColumn { ColumnName = "SupportUserEmail", Header = "E-mail Atendente", Visible =false });
            ticketColumns.Add(new SysColumn { ColumnName = "StatusId", Header = "StatusId", Visible = false });
            ticketColumns.Add(new SysColumn { ColumnName = "PriorityId", Header = "PriorityId", Visible = false });
            ticketColumns.Add(new SysColumn { ColumnName = "CategoryId", Header = "CategoryId", Visible = false });
            ticketColumns.Add(new SysColumn { ColumnName = "UserId", Header = "UserId", Visible = false });
            ticketColumns.Add(new SysColumn { ColumnName = "SupportUserId", Header = "SupportUserId", Visible = false });
        }

        private void mExit_Click(object sender, EventArgs e)
        {            
            Close();
        }

        private void Form1_FormClosing(object sender, FormClosingEventArgs e)
        {
            if (_currentUser != null && MessageBox.Show("Deseja realmente sair do sistema?", "Sair", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.No)
            {
                e.Cancel = true;
                this.eMsg.Text = Properties.Resources.MsgWelcome;
            }
            else
            {
                if (_connection != null && _connection.State == ConnectionState.Open)
                {
                    if (_currentUser != null)
                    {
                        Repository.history(_connection, "LOGIN", $"Usuário '{_currentUser.Username}' efetuou logout no sistema.");
                    }
                    try
                    {
                        if (_connection.State == ConnectionState.Open)
                            _connection.Close();
                    }
                    catch (Exception)
                    {
                        // Ignorar erros ao fechar a conexão
                    }
                }
            }
        }

        private void mAbout_Click(object sender, EventArgs e)
        {
            new FormAbout().ShowDialog();
        }

        private void bindingSource1_CurrentChanged(object sender, EventArgs e)
        {

        }

        private void mConfig_Click(object sender, EventArgs e)
        {
            new FormConfig().ShowDialog();
        }

        private void mChangeUser_Click(object sender, EventArgs e)
        {
            SysUser newUser = new FormLogin().execute();
            if (newUser != null && newUser.Username != null && newUser.Username != _currentUser.Username)
            {
                Repository.history(_connection, "LOGIN", $"Usuário '{_currentUser.Username}' trocado por '{newUser.Username}'.");
                _currentUser = newUser;
                this.eMsgUsername.Text = _currentUser.Name;
                this.eMsg.Text = "Usuário alterado.";

                TicketRefresh();
            }
            else
            {
                this.eMsg.Text = "";
            }
        }

        private void mChangePassword_Click(object sender, EventArgs e)
        {
            new FormChangePassword().Execute(_connection, _currentUser);
        }

        

        private void OpenSupportDataRef()
        {
            if (_connection != null)
            {
                Utils.ComboBox(eFilterStatus, Repository.StatusList(_connection));
                eFilterStatus.Items.Insert(0, new SysCommon { Id = 0, Name = "- Todos -" });
                eFilterStatus.SelectedIndex = 0;
                Utils.ComboBox(eFilterCategory, Repository.CategoryList(_connection));
                eFilterCategory.Items.Insert(0, new SysCommon { Id = 0, Name = "- Todos -" });
                eFilterCategory.SelectedIndex = 0;
                Utils.ComboBox(eFilterPriority, Repository.PriorityList(_connection));
                eFilterPriority.Items.Insert(0, new SysCommon { Id = 0, Name = "- Todos -" });
                eFilterPriority.SelectedIndex = 0;
            }
        }

        private void Form1_Shown(object sender, EventArgs e)
        {
            _currentUser = new FormLogin().execute();
            if (_currentUser != null)
            {
                this.eMsgUsername.Text = _currentUser.Name;
                this.eMsgConnection.Text = Properties.Resources.MsgConnected;

                string connectionString = $"Server={Properties.Settings.Default.DatabaseServer},{Properties.Settings.Default.DatabasePort};Database={Properties.Settings.Default.DatabaseName};User Id={Properties.Settings.Default.DatabaseUser};Password={Properties.Settings.Default.DatabasePassword};Connection Timeout=5;";
                _connection = new SqlConnection(connectionString);
                _connection.Open();

                OpenSupportDataRef();
                TicketRefresh();
            }
            else
            {
                Close();
            }
        }

        private void BLocate_Click(object sender, EventArgs e)
        {
            TicketRefresh();
        }

        private void TicketRefresh()
        {
            if (_connection != null && _connection.State == ConnectionState.Open)
            {
                TicketFilter filter = new TicketFilter
                {
                    Key = eFilterKey.Text,
                    Status = 1,
                    StatusId = (eFilterStatus.SelectedItem as SysCommon)?.Id ?? 0,
                    CategoryId = (eFilterCategory.SelectedItem as SysCommon)?.Id ?? 0,
                    PriorityId = (eFilterPriority.SelectedItem as SysCommon)?.Id ?? 0,
                    UserId = _currentUser.UserId
                };
                List<Ticket> tickets = Repository.TicketFindBy(_connection, filter);
                dataGridView1.DataSource = tickets;
                eMsg.Text = $"{tickets.Count} chamados encontrados.";

                foreach(SysColumn column in ticketColumns)
                {
                    if(dataGridView1.Columns.Contains(column.ColumnName))
                    {
                        dataGridView1.Columns[column.ColumnName].HeaderText = column.Header != null ? column.Header : "";
                        dataGridView1.Columns[column.ColumnName].Visible = column.Visible;
                        if (column.Width > 0)
                            dataGridView1.Columns[column.ColumnName].Width = column.Width;
                    }
                }
            }
            else
            {
                eMsg.Text = "Conexão com o banco de dados não está aberta.";
            }
        }

        private void bNewTicket_Click(object sender, EventArgs e)
        {
            if(new FormTicket().Execute(_connection, _currentUser) == DialogResult.OK)
            {
                TicketRefresh();
            }
        }

        private void dataGridView1_RowEnter(object sender, DataGridViewCellEventArgs e)
        {
            bEditTicket.Enabled = bCommentTicket.Enabled = bDeleteTicket.Enabled = (e.RowIndex >= 0 && e.RowIndex < dataGridView1.Rows.Count);

            if(e.RowIndex >= 0 && e.RowIndex < dataGridView1.Rows.Count)
            {
                eSelectedId.Text = dataGridView1.Rows[e.RowIndex].Cells["Id"].Value.ToString();
                eSelectedSubject.Text = dataGridView1.Rows[e.RowIndex].Cells["Subject"].Value.ToString();
                eSelectedDescription.Text = dataGridView1.Rows[e.RowIndex].Cells["Description"].Value.ToString();
                eSelectedCategory.Text = dataGridView1.Rows[e.RowIndex].Cells["CategoryName"].Value.ToString();
                eSelectedPriority.Text = dataGridView1.Rows[e.RowIndex].Cells["PriorityName"].Value.ToString();
                eSelectedStatus.Text = dataGridView1.Rows[e.RowIndex].Cells["StatusName"].Value.ToString();
                eSelectedUser.Text = dataGridView1.Rows[e.RowIndex].Cells["UserFullName"].Value.ToString();
                eSelectedSupportUser.Text = dataGridView1.Rows[e.RowIndex].Cells["SupportUserFullName"].Value != null ? dataGridView1.Rows[e.RowIndex].Cells["SupportUserFullName"].Value.ToString() : "";
                eSelectedInsertDate.Text = dataGridView1.Rows[e.RowIndex].Cells["InsertDate"].Value.ToString();
            }
            else
            {
                eSelectedId.Text = "";
                eSelectedSubject.Text = "";
                eSelectedDescription.Text = "";
                eSelectedCategory.Text = "";
                eSelectedPriority.Text = "";
                eSelectedStatus.Text = "";  
                eSelectedUser.Text = "";    
                eSelectedSupportUser.Text = "";
                eSelectedInsertDate.Text = "";
            }
        }

        private void bDeleteTicket_Click(object sender, EventArgs e)
        {
            Ticket ticket = dataGridView1.Rows[dataGridView1.CurrentCell.RowIndex].DataBoundItem as Ticket;
            if (MessageBox.Show("Confirma o cancelamento do chamado: " + ticket.Id + "?", "Cancelar Chamado", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.Yes)
            {                
                if(ticket != null)
                {
                    Repository.TicketUpdateStatus(_connection, ticket.Id, 0);
                    Repository.history(_connection, "SUPPOT", $"Usuário '{_currentUser.Username}' cancelou o chamado ID {ticket.Id}.");
                    TicketRefresh();
                }
            }
        }

        private void label6_Click(object sender, EventArgs e)
        {

        }

        private void eFilterKey_Leave(object sender, EventArgs e)
        {
            TicketRefresh();
        }

        private void eFilterCategory_SelectedValueChanged(object sender, EventArgs e)
        {
            TicketRefresh();
        }

        private void bCommentTicket_Click(object sender, EventArgs e)
        {
            Ticket ticket = dataGridView1.Rows[dataGridView1.CurrentCell.RowIndex].DataBoundItem as Ticket;
            if (ticket != null)
            {
                new FormNotes().Execute(_connection, ticket, _currentUser);
            }
        }

        private void bEditTicket_Click(object sender, EventArgs e)
        {
            Ticket ticket = dataGridView1.Rows[dataGridView1.CurrentCell.RowIndex].DataBoundItem as Ticket;
            if (ticket != null)
            {
                new FormTicketDetail().Execute(_connection, ticket, _currentUser);
            }
        }

        private void dataGridView1_DoubleClick(object sender, EventArgs e)
        {
            Ticket ticket = dataGridView1.Rows[dataGridView1.CurrentCell.RowIndex].DataBoundItem as Ticket;
            if (ticket != null)
            {
                new FormTicketDetail().Execute(_connection, ticket, _currentUser);
            }
        }
    }
}
