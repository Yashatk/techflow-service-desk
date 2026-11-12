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
    public partial class FormChangePassword : Form
    {
        SqlConnection _connection;
        SysUser _currentUser;
        public FormChangePassword()
        {
            InitializeComponent();
        }
        public void Execute(SqlConnection connection, SysUser user)
        {
            this._connection = connection;
            this._currentUser = user;
            this.eUsername.Text = user.Username;
            this.ShowDialog();
        }

        private void bChangePassword_Click(object sender, EventArgs e)
        {
            if(this._connection != null && this._connection.State == ConnectionState.Open)
            {                
                if (EncryptUtils.md5(this.eCurrentPassword.Text) == _currentUser.Password)
                {
                    if(this.eNewPassword.Text.Length < 6)
                    {
                        eMsg.Text = "A nova senha deve ter pelo menos 6 caracteres.";
                        return;
                    }
                    if (this.eNewPassword.Text == this.eConfirmPassword.Text)
                    {
                        String encryptedPassword = EncryptUtils.md5(this.eNewPassword.Text);
                        Repository.ChangePassword(this._connection, _currentUser.UserId, encryptedPassword);
                        Repository.history(this._connection, "SYSTEM", $"Usuário '{_currentUser.Username}' alterou sua senha.");
                        MessageBox.Show("Senha alterada com sucesso.", "Sucesso", MessageBoxButtons.OK, MessageBoxIcon.Information);
                        this.Close();
                    }
                    else
                    {
                        eMsg.Text = "A nova senha e a confirmação não coincidem.";
                    }
                }
                else
                {
                    eMsg.Text = "A senha atual está incorreta.";
                }
            }
            else
            {
                eMsg.Text = "Conexão com o banco de dados não está aberta.";
            }
        }

        private void bCancel_Click(object sender, EventArgs e)
        {
            Close();
        }
    }
}
