namespace Techflow
{
    partial class FormConfig
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.panel1 = new System.Windows.Forms.Panel();
            this.bCancel = new System.Windows.Forms.Button();
            this.bOK = new System.Windows.Forms.Button();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.eDatabaseName = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.bTestConnection = new System.Windows.Forms.Button();
            this.eDatabasePassword = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.eDatabaseUser = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.eDatabasePort = new System.Windows.Forms.TextBox();
            this.eDatabaseServer = new System.Windows.Forms.TextBox();
            this.panel1.SuspendLayout();
            this.groupBox1.SuspendLayout();
            this.SuspendLayout();
            // 
            // panel1
            // 
            this.panel1.Controls.Add(this.bCancel);
            this.panel1.Controls.Add(this.bOK);
            this.panel1.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.panel1.Location = new System.Drawing.Point(0, 359);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(822, 72);
            this.panel1.TabIndex = 0;
            // 
            // bCancel
            // 
            this.bCancel.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.bCancel.Location = new System.Drawing.Point(446, 16);
            this.bCancel.Name = "bCancel";
            this.bCancel.Size = new System.Drawing.Size(179, 40);
            this.bCancel.TabIndex = 1;
            this.bCancel.Text = "&Cancelar";
            this.bCancel.UseVisualStyleBackColor = true;
            this.bCancel.Click += new System.EventHandler(this.bCancel_Click);
            // 
            // bOK
            // 
            this.bOK.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.bOK.Location = new System.Drawing.Point(631, 16);
            this.bOK.Name = "bOK";
            this.bOK.Size = new System.Drawing.Size(179, 40);
            this.bOK.TabIndex = 0;
            this.bOK.Text = "&OK";
            this.bOK.UseVisualStyleBackColor = true;
            this.bOK.Click += new System.EventHandler(this.bOK_Click);
            // 
            // groupBox1
            // 
            this.groupBox1.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.groupBox1.Controls.Add(this.eDatabaseName);
            this.groupBox1.Controls.Add(this.label5);
            this.groupBox1.Controls.Add(this.bTestConnection);
            this.groupBox1.Controls.Add(this.eDatabasePassword);
            this.groupBox1.Controls.Add(this.label4);
            this.groupBox1.Controls.Add(this.eDatabaseUser);
            this.groupBox1.Controls.Add(this.label3);
            this.groupBox1.Controls.Add(this.label2);
            this.groupBox1.Controls.Add(this.label1);
            this.groupBox1.Controls.Add(this.eDatabasePort);
            this.groupBox1.Controls.Add(this.eDatabaseServer);
            this.groupBox1.Location = new System.Drawing.Point(22, 15);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(776, 323);
            this.groupBox1.TabIndex = 1;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Conexão com o Banco de Dados";
            // 
            // eDatabaseName
            // 
            this.eDatabaseName.Location = new System.Drawing.Point(28, 124);
            this.eDatabaseName.Name = "eDatabaseName";
            this.eDatabaseName.Size = new System.Drawing.Size(287, 26);
            this.eDatabaseName.TabIndex = 10;
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(28, 101);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(132, 20);
            this.label5.TabIndex = 9;
            this.label5.Text = "Banco de Dados:";
            // 
            // bTestConnection
            // 
            this.bTestConnection.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.bTestConnection.Location = new System.Drawing.Point(579, 241);
            this.bTestConnection.Name = "bTestConnection";
            this.bTestConnection.Size = new System.Drawing.Size(179, 40);
            this.bTestConnection.TabIndex = 8;
            this.bTestConnection.Text = "&Testar Conexão";
            this.bTestConnection.UseVisualStyleBackColor = true;
            this.bTestConnection.Click += new System.EventHandler(this.bTestConnection_Click);
            // 
            // eDatabasePassword
            // 
            this.eDatabasePassword.Location = new System.Drawing.Point(28, 255);
            this.eDatabasePassword.Name = "eDatabasePassword";
            this.eDatabasePassword.PasswordChar = '*';
            this.eDatabasePassword.Size = new System.Drawing.Size(287, 26);
            this.eDatabasePassword.TabIndex = 7;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(28, 232);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(60, 20);
            this.label4.TabIndex = 6;
            this.label4.Text = "Senha:";
            // 
            // eDatabaseUser
            // 
            this.eDatabaseUser.Location = new System.Drawing.Point(28, 188);
            this.eDatabaseUser.Name = "eDatabaseUser";
            this.eDatabaseUser.Size = new System.Drawing.Size(287, 26);
            this.eDatabaseUser.TabIndex = 5;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(28, 165);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(68, 20);
            this.label3.TabIndex = 4;
            this.label3.Text = "Usuário:";
            // 
            // label2
            // 
            this.label2.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(658, 38);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(51, 20);
            this.label2.TabIndex = 3;
            this.label2.Text = "Porta:";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(24, 35);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(71, 20);
            this.label1.TabIndex = 2;
            this.label1.Text = "Servidor:";
            // 
            // eDatabasePort
            // 
            this.eDatabasePort.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.eDatabasePort.Location = new System.Drawing.Point(658, 61);
            this.eDatabasePort.Name = "eDatabasePort";
            this.eDatabasePort.Size = new System.Drawing.Size(100, 26);
            this.eDatabasePort.TabIndex = 1;
            // 
            // eDatabaseServer
            // 
            this.eDatabaseServer.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.eDatabaseServer.Location = new System.Drawing.Point(24, 61);
            this.eDatabaseServer.Name = "eDatabaseServer";
            this.eDatabaseServer.Size = new System.Drawing.Size(628, 26);
            this.eDatabaseServer.TabIndex = 0;
            // 
            // FormConfig
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(822, 431);
            this.Controls.Add(this.groupBox1);
            this.Controls.Add(this.panel1);
            this.Name = "FormConfig";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;
            this.Text = "Configurações";
            this.panel1.ResumeLayout(false);
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.Button bCancel;
        private System.Windows.Forms.Button bOK;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.TextBox eDatabaseUser;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox eDatabasePort;
        private System.Windows.Forms.TextBox eDatabaseServer;
        private System.Windows.Forms.TextBox eDatabasePassword;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Button bTestConnection;
        private System.Windows.Forms.TextBox eDatabaseName;
        private System.Windows.Forms.Label label5;
    }
}