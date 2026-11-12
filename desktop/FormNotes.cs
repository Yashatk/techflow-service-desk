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
    public partial class FormNotes : Form
    {
        SqlConnection _connection;
        Ticket ticket;
        SysUser user;
        public FormNotes()
        {
            InitializeComponent();
            DialogResult = DialogResult.Cancel;
        }

        public DialogResult Execute(SqlConnection connection, Ticket ticket, SysUser user)
        {
            this.user = user;
            this._connection = connection;
            this.ticket = ticket;
            eSelectedId.Text = ticket.Id.ToString();
            eSelectedSubject.Text = ticket.Subject;
            return ShowDialog();
        }

        private void bCancel_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void bOK_Click(object sender, EventArgs e)
        {
            try
            {
                Repository.TicketDetailCreate(_connection, ticket.Id, eNotes.Text, 1, user.UserId);
                DialogResult = DialogResult.OK;
                Close();
            }
            catch(Exception ex)
            {
                MessageBox.Show(ex.Message, "Erro", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }
    }
}
