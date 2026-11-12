using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.Common;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Techflow
{
    public partial class FormTicket : Form
    {
        SysUser currentUser;
        SqlConnection connection;
        public FormTicket()
        {
            InitializeComponent();
        }

        public DialogResult Execute(SqlConnection connection, SysUser user)
        {
            this.currentUser = user;
            this.connection = connection;

            Utils.ComboBox(eCategory, Repository.CategoryList(connection));
            eCategory.SelectedIndex = 3;
            Utils.ComboBox(ePriority, Repository.PriorityList(connection));            
            ePriority.SelectedIndex = 3;
            return ShowDialog();
        }

        private void bCancel_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void bNew_Click(object sender, EventArgs e)
        {
            try
            {
                Ticket ticket = new Ticket
                {
                    Subject = eSubject.Text,
                    Description = eDescription.Text,
                    StatusId = 1,
                    CategoryId = ((SysCommon)eCategory.SelectedItem).Id,
                    PriorityId = ((SysCommon)ePriority.SelectedItem).Id,
                    UserId = currentUser.UserId
                };
                int createdCount = Repository.TicketCreate(connection, ticket);
                if(createdCount > 0)
                {
                    DialogResult = DialogResult.OK;
                    Close();
                }
                else
                {
                    MessageBox.Show("Nenhum chamado foi criado.", "Atenção", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                }
                
            }
            catch (Exception ex)
            {
                MessageBox.Show("Erro ao criar o chamado: " + ex.Message, "Erro", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void eSubject_TextChanged(object sender, EventArgs e)
        {
            bNew.Enabled = (eSubject.Text.Length > 3 && eDescription.Text.Length > 3);
        }
    }
}
