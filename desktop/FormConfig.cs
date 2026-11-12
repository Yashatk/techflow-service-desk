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
    public partial class FormConfig : Form
    {
        public FormConfig()
        {
            InitializeComponent();
            this.LoadConfig();
        }

        private void LoadConfig()
        {
            this.eDatabaseServer.Text = Properties.Settings.Default.DatabaseServer;
            this.eDatabasePort.Text = Properties.Settings.Default.DatabasePort;
            this.eDatabaseUser.Text = Properties.Settings.Default.DatabaseUser;
            this.eDatabasePassword.Text = Properties.Settings.Default.DatabasePassword;
            this.eDatabaseName.Text = Properties.Settings.Default.DatabaseName;
        }

        private void SaveConfig()
        {
            Properties.Settings.Default.DatabaseServer = this.eDatabaseServer.Text.Trim();
            Properties.Settings.Default.DatabasePort = this.eDatabasePort.Text.Trim();
            Properties.Settings.Default.DatabaseUser = this.eDatabaseUser.Text.Trim();
            Properties.Settings.Default.DatabasePassword = this.eDatabasePassword.Text.Trim();
            Properties.Settings.Default.DatabaseName = this.eDatabaseName.Text.Trim();
            Properties.Settings.Default.Save();
        }

        private void bCancel_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void bOK_Click(object sender, EventArgs e)
        {
            this.SaveConfig();
            Close();
        }

        private void bTestConnection_Click(object sender, EventArgs e)
        {
            // Obtenha os valores dos campos TextBox
            string server = eDatabaseServer.Text.Trim();
            string port = eDatabasePort.Text.Trim();
            string user = eDatabaseUser.Text.Trim();
            string password = eDatabasePassword.Text.Trim();
            string database = eDatabaseName.Text.Trim();

            // Monta a string de conexão
            string connectionString = $"Server={server},{port};Database={database};User Id={user};Password={password};Connection Timeout=5;";

            // Tenta abrir a conexão
            using (var connection = new SqlConnection(connectionString))
            {
                try
                {
                    connection.Open();
                    MessageBox.Show("Conexão bem-sucedida!", "Teste de Conexão", MessageBoxButtons.OK, MessageBoxIcon.Information);
                }
                catch (Exception ex)
                {
                    MessageBox.Show($"Falha na conexão:\n{ex.Message}", "Teste de Conexão", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
        }
    }
}
