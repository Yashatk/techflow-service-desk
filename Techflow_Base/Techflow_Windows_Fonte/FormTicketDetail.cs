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
    public partial class FormTicketDetail : Form
    {
        Ticket ticket;
        SqlConnection _connection;
        SysUser _currentUser;

        private List<SysColumn> ticketDetailColumns = new List<SysColumn>();
        public FormTicketDetail()
        {
            InitializeComponent();
            DialogResult = DialogResult.Cancel;
            InitializeTicketColumns();
        }

        private void InitializeTicketColumns()
        {
            ticketDetailColumns.Add(new SysColumn { ColumnName = "Id", Header = "Id", Visible = false, Width = 50 });
            ticketDetailColumns.Add(new SysColumn { ColumnName = "Status", Header = "Status", Visible = false, Width = 50 });
            ticketDetailColumns.Add(new SysColumn { ColumnName = "TicketId", Header = "Chamado", Visible = false });
            ticketDetailColumns.Add(new SysColumn { ColumnName = "StatusId", Header = "Status", Visible = false });
            ticketDetailColumns.Add(new SysColumn { ColumnName = "StatusName", Header = "Status", Visible = true });
            ticketDetailColumns.Add(new SysColumn { ColumnName = "Description", Header = "Descrição", Visible = true });
            ticketDetailColumns.Add(new SysColumn { ColumnName = "UserId", Header = "Usuário", Visible = false });
            ticketDetailColumns.Add(new SysColumn { ColumnName = "UserFullName", Header = "Usuário", Visible = true });
            ticketDetailColumns.Add(new SysColumn { ColumnName = "Username", Header = "Login", Visible = false });
            ticketDetailColumns.Add(new SysColumn { ColumnName = "UserEmail", Header = "E-mail", Visible = false });
            ticketDetailColumns.Add(new SysColumn { ColumnName = "InsertDate", Header = "Criação", Visible = true });
            ticketDetailColumns.Add(new SysColumn { ColumnName = "UpdateDate", Header = "Atualização", Visible = false });

        }

        public DialogResult Execute(SqlConnection connection, Ticket ticket, SysUser user)
        {
            this.ticket = ticket;
            this._currentUser = user;
            this._connection = connection;

            eSelectedId.Text = ticket.Id.ToString();
            eSelectedSubject.Text = ticket.Subject;
            eSelectedDescription.Text = ticket.Description;
            eSelectedCategory.Text = ticket.CategoryName;
            eSelectedStatus.Text = ticket.StatusName;
            eSelectedPriority.Text = ticket.PriorityName;
            eSelectedUser.Text = ticket.UserFullName;
            eSelectedSupportUser.Text = ticket.SupportUserFullName;
            eSelectedInsertDate.Text = ticket.InsertDate.ToString();

            TicketDetailRefresh();

            return ShowDialog();

        }

        private void bOK_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void TicketDetailRefresh()
        {
            if (_connection != null && _connection.State == ConnectionState.Open)
            {               
                List<TicketDetail> ticketDetails = Repository.TicketDetailFindBy(_connection, ticket.Id);
                dataGridView1.DataSource = ticketDetails;
                
                foreach (SysColumn column in ticketDetailColumns)
                {
                    if (dataGridView1.Columns.Contains(column.ColumnName))
                    {
                        dataGridView1.Columns[column.ColumnName].HeaderText = column.Header != null ? column.Header : "";
                        dataGridView1.Columns[column.ColumnName].Visible = column.Visible;
                        if (column.Width > 0)
                            dataGridView1.Columns[column.ColumnName].Width = column.Width;
                    }
                }

            }
        }

        private void bRefresh_Click(object sender, EventArgs e)
        {
            TicketDetailRefresh();
        }

        private void dataGridView1_RowEnter(object sender, DataGridViewCellEventArgs e)
        {
            eDetailDescription.Text = "";
            if (e.RowIndex >= 0 && e.RowIndex < dataGridView1.Rows.Count)
            {
                DataGridViewRow row = dataGridView1.Rows[e.RowIndex];
                if (row.DataBoundItem is TicketDetail)
                {
                    TicketDetail ticketDetail = (TicketDetail)row.DataBoundItem;
                    eDetailDescription.Text = ticketDetail.Description;
                }
            }
        }
    }
}
