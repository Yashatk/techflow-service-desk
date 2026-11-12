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
    public partial class FormLogin : Form
    {
        SysUser _sysUser = null;
        
        public FormLogin()
        {
            InitializeComponent();
            this.eUsername.Text = Properties.Settings.Default.LastUsername;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            new FormConfig().ShowDialog();
        }

        private void bCancel_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void bLogin_Click(object sender, EventArgs e)
        {
            SqlConnection _connection = null;
            if (_connection == null || _connection.State != ConnectionState.Open)
            {
                string server = Properties.Settings.Default.DatabaseServer.Trim();
                string port = Properties.Settings.Default.DatabasePort.Trim();
                string user = Properties.Settings.Default.DatabaseUser.Trim();
                string password = Properties.Settings.Default.DatabasePassword.Trim();
                string database = Properties.Settings.Default.DatabaseName.Trim();

                // Monta a string de conexão
                string connectionString = $"Server={server},{port};Database={database};User Id={user};Password={password};Connection Timeout=5;";

                // Tenta abrir a conexão
                using (_connection = new SqlConnection(connectionString))
                {
                    try
                    {
                        _connection.Open();

                        _sysUser = Repository.FindUserByUsername(_connection, this.eUsername.Text);
                        if (_sysUser != null && _sysUser.Password == EncryptUtils.md5(this.ePassword.Text))
                        {
                            Properties.Settings.Default.LastUsername = this.eUsername.Text.Trim();
                            Properties.Settings.Default.Save();
                            Repository.history(_connection, "LOGIN", $"Usuário '{_sysUser.Username}' efetuou login no sistema.");
                            Close();
                        }
                        else
                        {
                            MessageBox.Show("Usuário ou senha inválidos.", "Erro de autenticação", MessageBoxButtons.OK, MessageBoxIcon.Error);
                        }
                    }
                    catch (Exception ex)
                    {
                        MessageBox.Show($"Falha na conexão:\n{ex.Message}", "Banco de Dados", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    }
                    finally
                    {
                        if (_connection.State == ConnectionState.Open)
                        {
                            _connection.Close();
                        }
                    }
                }
            }
            
        }

        public SysUser execute()
        {            
            this.ShowDialog();
            return this._sysUser;

        }
    }
}
