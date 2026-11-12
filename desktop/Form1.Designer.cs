namespace Techflow
{
    partial class Form1
    {
        /// <summary>
        /// Variável de designer necessária.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Limpar os recursos que estão sendo usados.
        /// </summary>
        /// <param name="disposing">true se for necessário descartar os recursos gerenciados; caso contrário, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Código gerado pelo Windows Form Designer

        /// <summary>
        /// Método necessário para suporte ao Designer - não modifique 
        /// o conteúdo deste método com o editor de código.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Form1));
            this.statusStrip1 = new System.Windows.Forms.StatusStrip();
            this.eMsg = new System.Windows.Forms.ToolStripStatusLabel();
            this.eMsgConnection = new System.Windows.Forms.ToolStripStatusLabel();
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.mFile = new System.Windows.Forms.ToolStripMenuItem();
            this.mChangePassword = new System.Windows.Forms.ToolStripMenuItem();
            this.mChangeUser = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripMenuItem1 = new System.Windows.Forms.ToolStripSeparator();
            this.mConfig = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripMenuItem2 = new System.Windows.Forms.ToolStripSeparator();
            this.mExit = new System.Windows.Forms.ToolStripMenuItem();
            this.mHelp = new System.Windows.Forms.ToolStripMenuItem();
            this.mAbout = new System.Windows.Forms.ToolStripMenuItem();
            this.bindingSource1 = new System.Windows.Forms.BindingSource(this.components);
            this.panel1 = new System.Windows.Forms.Panel();
            this.eMsgUsername = new System.Windows.Forms.Label();
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.toolStrip1 = new System.Windows.Forms.ToolStrip();
            this.bNewTicket = new System.Windows.Forms.ToolStripButton();
            this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
            this.bDeleteTicket = new System.Windows.Forms.ToolStripButton();
            this.bCommentTicket = new System.Windows.Forms.ToolStripButton();
            this.bEditTicket = new System.Windows.Forms.ToolStripButton();
            this.tabControl1 = new System.Windows.Forms.TabControl();
            this.tabPage1 = new System.Windows.Forms.TabPage();
            this.dataGridView1 = new System.Windows.Forms.DataGridView();
            this.splitter1 = new System.Windows.Forms.Splitter();
            this.panel3 = new System.Windows.Forms.Panel();
            this.button1 = new System.Windows.Forms.Button();
            this.label4 = new System.Windows.Forms.Label();
            this.eFilterKey = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.eFilterStatus = new System.Windows.Forms.ComboBox();
            this.label2 = new System.Windows.Forms.Label();
            this.eFilterPriority = new System.Windows.Forms.ComboBox();
            this.label1 = new System.Windows.Forms.Label();
            this.eFilterCategory = new System.Windows.Forms.ComboBox();
            this.panel2 = new System.Windows.Forms.Panel();
            this.eSelectedUser = new System.Windows.Forms.TextBox();
            this.label13 = new System.Windows.Forms.Label();
            this.eSelectedSupportUser = new System.Windows.Forms.TextBox();
            this.label12 = new System.Windows.Forms.Label();
            this.eSelectedInsertDate = new System.Windows.Forms.TextBox();
            this.label11 = new System.Windows.Forms.Label();
            this.eSelectedDescription = new System.Windows.Forms.RichTextBox();
            this.label10 = new System.Windows.Forms.Label();
            this.eSelectedStatus = new System.Windows.Forms.TextBox();
            this.label9 = new System.Windows.Forms.Label();
            this.eSelectedPriority = new System.Windows.Forms.TextBox();
            this.label8 = new System.Windows.Forms.Label();
            this.eSelectedCategory = new System.Windows.Forms.TextBox();
            this.label7 = new System.Windows.Forms.Label();
            this.eSelectedSubject = new System.Windows.Forms.TextBox();
            this.eSelectedId = new System.Windows.Forms.TextBox();
            this.label6 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.statusStrip1.SuspendLayout();
            this.menuStrip1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.bindingSource1)).BeginInit();
            this.panel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.toolStrip1.SuspendLayout();
            this.tabControl1.SuspendLayout();
            this.tabPage1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).BeginInit();
            this.panel3.SuspendLayout();
            this.panel2.SuspendLayout();
            this.SuspendLayout();
            // 
            // statusStrip1
            // 
            this.statusStrip1.ImageScalingSize = new System.Drawing.Size(24, 24);
            this.statusStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.eMsg,
            this.eMsgConnection});
            this.statusStrip1.Location = new System.Drawing.Point(0, 703);
            this.statusStrip1.Name = "statusStrip1";
            this.statusStrip1.Padding = new System.Windows.Forms.Padding(2, 0, 21, 0);
            this.statusStrip1.Size = new System.Drawing.Size(887, 32);
            this.statusStrip1.TabIndex = 0;
            this.statusStrip1.Text = "statusStrip1";
            // 
            // eMsg
            // 
            this.eMsg.Name = "eMsg";
            this.eMsg.Size = new System.Drawing.Size(728, 25);
            this.eMsg.Spring = true;
            this.eMsg.Text = "toolStripStatusLabel1";
            this.eMsg.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // eMsgConnection
            // 
            this.eMsgConnection.Name = "eMsgConnection";
            this.eMsgConnection.Size = new System.Drawing.Size(136, 25);
            this.eMsgConnection.Text = "Não Conectado";
            // 
            // menuStrip1
            // 
            this.menuStrip1.GripMargin = new System.Windows.Forms.Padding(2, 2, 0, 2);
            this.menuStrip1.ImageScalingSize = new System.Drawing.Size(24, 24);
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.mFile,
            this.mHelp});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(887, 33);
            this.menuStrip1.TabIndex = 1;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // mFile
            // 
            this.mFile.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.mChangePassword,
            this.mChangeUser,
            this.toolStripMenuItem1,
            this.mConfig,
            this.toolStripMenuItem2,
            this.mExit});
            this.mFile.Name = "mFile";
            this.mFile.Size = new System.Drawing.Size(91, 29);
            this.mFile.Text = "&Arquivo";
            // 
            // mChangePassword
            // 
            this.mChangePassword.Name = "mChangePassword";
            this.mChangePassword.Size = new System.Drawing.Size(236, 34);
            this.mChangePassword.Text = "Alterar &senha...";
            this.mChangePassword.Click += new System.EventHandler(this.mChangePassword_Click);
            // 
            // mChangeUser
            // 
            this.mChangeUser.Name = "mChangeUser";
            this.mChangeUser.Size = new System.Drawing.Size(236, 34);
            this.mChangeUser.Text = "&Trocar usuário...";
            this.mChangeUser.Click += new System.EventHandler(this.mChangeUser_Click);
            // 
            // toolStripMenuItem1
            // 
            this.toolStripMenuItem1.Name = "toolStripMenuItem1";
            this.toolStripMenuItem1.Size = new System.Drawing.Size(233, 6);
            // 
            // mConfig
            // 
            this.mConfig.Name = "mConfig";
            this.mConfig.Size = new System.Drawing.Size(236, 34);
            this.mConfig.Text = "&Configuração...";
            this.mConfig.Click += new System.EventHandler(this.mConfig_Click);
            // 
            // toolStripMenuItem2
            // 
            this.toolStripMenuItem2.Name = "toolStripMenuItem2";
            this.toolStripMenuItem2.Size = new System.Drawing.Size(233, 6);
            // 
            // mExit
            // 
            this.mExit.Name = "mExit";
            this.mExit.Size = new System.Drawing.Size(236, 34);
            this.mExit.Text = "Sai&r";
            this.mExit.Click += new System.EventHandler(this.mExit_Click);
            // 
            // mHelp
            // 
            this.mHelp.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.mAbout});
            this.mHelp.Name = "mHelp";
            this.mHelp.Size = new System.Drawing.Size(74, 29);
            this.mHelp.Text = "Aj&uda";
            // 
            // mAbout
            // 
            this.mAbout.Name = "mAbout";
            this.mAbout.Size = new System.Drawing.Size(173, 34);
            this.mAbout.Text = "&Sobre...";
            this.mAbout.Click += new System.EventHandler(this.mAbout_Click);
            // 
            // bindingSource1
            // 
            this.bindingSource1.CurrentChanged += new System.EventHandler(this.bindingSource1_CurrentChanged);
            // 
            // panel1
            // 
            this.panel1.Controls.Add(this.eMsgUsername);
            this.panel1.Controls.Add(this.pictureBox1);
            this.panel1.Controls.Add(this.toolStrip1);
            this.panel1.Dock = System.Windows.Forms.DockStyle.Top;
            this.panel1.Location = new System.Drawing.Point(0, 33);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(887, 109);
            this.panel1.TabIndex = 2;
            // 
            // eMsgUsername
            // 
            this.eMsgUsername.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.eMsgUsername.Location = new System.Drawing.Point(12, 74);
            this.eMsgUsername.Name = "eMsgUsername";
            this.eMsgUsername.Size = new System.Drawing.Size(721, 23);
            this.eMsgUsername.TabIndex = 5;
            this.eMsgUsername.Text = "-";
            this.eMsgUsername.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // pictureBox1
            // 
            this.pictureBox1.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.pictureBox1.Image = global::Techflow.Properties.Resources.logo1;
            this.pictureBox1.Location = new System.Drawing.Point(770, 3);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(117, 107);
            this.pictureBox1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox1.TabIndex = 4;
            this.pictureBox1.TabStop = false;
            // 
            // toolStrip1
            // 
            this.toolStrip1.ImageScalingSize = new System.Drawing.Size(24, 24);
            this.toolStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.bNewTicket,
            this.toolStripSeparator1,
            this.bDeleteTicket,
            this.bCommentTicket,
            this.bEditTicket});
            this.toolStrip1.Location = new System.Drawing.Point(0, 0);
            this.toolStrip1.Name = "toolStrip1";
            this.toolStrip1.Size = new System.Drawing.Size(887, 33);
            this.toolStrip1.TabIndex = 0;
            this.toolStrip1.Text = "toolStrip1";
            // 
            // bNewTicket
            // 
            this.bNewTicket.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.bNewTicket.Image = ((System.Drawing.Image)(resources.GetObject("bNewTicket.Image")));
            this.bNewTicket.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.bNewTicket.Name = "bNewTicket";
            this.bNewTicket.Size = new System.Drawing.Size(34, 28);
            this.bNewTicket.Text = "toolStripButton1";
            this.bNewTicket.ToolTipText = "Novo Chamado";
            this.bNewTicket.Click += new System.EventHandler(this.bNewTicket_Click);
            // 
            // toolStripSeparator1
            // 
            this.toolStripSeparator1.Name = "toolStripSeparator1";
            this.toolStripSeparator1.Size = new System.Drawing.Size(6, 33);
            // 
            // bDeleteTicket
            // 
            this.bDeleteTicket.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.bDeleteTicket.Enabled = false;
            this.bDeleteTicket.Image = ((System.Drawing.Image)(resources.GetObject("bDeleteTicket.Image")));
            this.bDeleteTicket.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.bDeleteTicket.Name = "bDeleteTicket";
            this.bDeleteTicket.Size = new System.Drawing.Size(34, 28);
            this.bDeleteTicket.Text = "Cancelar Chamado";
            this.bDeleteTicket.Click += new System.EventHandler(this.bDeleteTicket_Click);
            // 
            // bCommentTicket
            // 
            this.bCommentTicket.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.bCommentTicket.Enabled = false;
            this.bCommentTicket.Image = ((System.Drawing.Image)(resources.GetObject("bCommentTicket.Image")));
            this.bCommentTicket.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.bCommentTicket.Name = "bCommentTicket";
            this.bCommentTicket.Size = new System.Drawing.Size(34, 28);
            this.bCommentTicket.Text = "Incluir Comentário";
            this.bCommentTicket.ToolTipText = "Adicionar Comentário";
            this.bCommentTicket.Click += new System.EventHandler(this.bCommentTicket_Click);
            // 
            // bEditTicket
            // 
            this.bEditTicket.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.bEditTicket.Enabled = false;
            this.bEditTicket.Image = ((System.Drawing.Image)(resources.GetObject("bEditTicket.Image")));
            this.bEditTicket.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.bEditTicket.Name = "bEditTicket";
            this.bEditTicket.Size = new System.Drawing.Size(34, 28);
            this.bEditTicket.Text = "toolStripButton1";
            this.bEditTicket.Click += new System.EventHandler(this.bEditTicket_Click);
            // 
            // tabControl1
            // 
            this.tabControl1.Controls.Add(this.tabPage1);
            this.tabControl1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tabControl1.Location = new System.Drawing.Point(0, 142);
            this.tabControl1.Name = "tabControl1";
            this.tabControl1.SelectedIndex = 0;
            this.tabControl1.Size = new System.Drawing.Size(887, 561);
            this.tabControl1.TabIndex = 3;
            // 
            // tabPage1
            // 
            this.tabPage1.BackColor = System.Drawing.SystemColors.ButtonFace;
            this.tabPage1.Controls.Add(this.dataGridView1);
            this.tabPage1.Controls.Add(this.splitter1);
            this.tabPage1.Controls.Add(this.panel3);
            this.tabPage1.Controls.Add(this.panel2);
            this.tabPage1.Location = new System.Drawing.Point(4, 29);
            this.tabPage1.Name = "tabPage1";
            this.tabPage1.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage1.Size = new System.Drawing.Size(879, 528);
            this.tabPage1.TabIndex = 0;
            this.tabPage1.Text = "Chamados";
            // 
            // dataGridView1
            // 
            this.dataGridView1.AllowUserToAddRows = false;
            this.dataGridView1.AllowUserToDeleteRows = false;
            this.dataGridView1.AllowUserToOrderColumns = true;
            this.dataGridView1.AllowUserToResizeRows = false;
            this.dataGridView1.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.dataGridView1.CausesValidation = false;
            this.dataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dataGridView1.EditMode = System.Windows.Forms.DataGridViewEditMode.EditProgrammatically;
            this.dataGridView1.Location = new System.Drawing.Point(3, 85);
            this.dataGridView1.MultiSelect = false;
            this.dataGridView1.Name = "dataGridView1";
            this.dataGridView1.RowHeadersVisible = false;
            this.dataGridView1.RowHeadersWidth = 62;
            this.dataGridView1.RowTemplate.Height = 28;
            this.dataGridView1.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dataGridView1.Size = new System.Drawing.Size(873, 203);
            this.dataGridView1.TabIndex = 3;
            this.dataGridView1.RowEnter += new System.Windows.Forms.DataGridViewCellEventHandler(this.dataGridView1_RowEnter);
            this.dataGridView1.DoubleClick += new System.EventHandler(this.dataGridView1_DoubleClick);
            // 
            // splitter1
            // 
            this.splitter1.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.splitter1.Location = new System.Drawing.Point(3, 288);
            this.splitter1.Name = "splitter1";
            this.splitter1.Size = new System.Drawing.Size(873, 3);
            this.splitter1.TabIndex = 2;
            this.splitter1.TabStop = false;
            // 
            // panel3
            // 
            this.panel3.Controls.Add(this.button1);
            this.panel3.Controls.Add(this.label4);
            this.panel3.Controls.Add(this.eFilterKey);
            this.panel3.Controls.Add(this.label3);
            this.panel3.Controls.Add(this.eFilterStatus);
            this.panel3.Controls.Add(this.label2);
            this.panel3.Controls.Add(this.eFilterPriority);
            this.panel3.Controls.Add(this.label1);
            this.panel3.Controls.Add(this.eFilterCategory);
            this.panel3.Dock = System.Windows.Forms.DockStyle.Top;
            this.panel3.Location = new System.Drawing.Point(3, 3);
            this.panel3.Name = "panel3";
            this.panel3.Size = new System.Drawing.Size(873, 82);
            this.panel3.TabIndex = 1;
            // 
            // button1
            // 
            this.button1.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.button1.Image = global::Techflow.Properties.Resources._16localizar;
            this.button1.Location = new System.Drawing.Point(693, 20);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(175, 43);
            this.button1.TabIndex = 8;
            this.button1.Text = "Localizar";
            this.button1.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.button1.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText;
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.BLocate_Click);
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(14, 7);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(72, 20);
            this.label4.TabIndex = 7;
            this.label4.Text = "Localizar";
            // 
            // eFilterKey
            // 
            this.eFilterKey.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.eFilterKey.Location = new System.Drawing.Point(9, 35);
            this.eFilterKey.Name = "eFilterKey";
            this.eFilterKey.Size = new System.Drawing.Size(137, 26);
            this.eFilterKey.TabIndex = 6;
            this.eFilterKey.Leave += new System.EventHandler(this.eFilterKey_Leave);
            // 
            // label3
            // 
            this.label3.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(512, 7);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(56, 20);
            this.label3.TabIndex = 5;
            this.label3.Text = "Status";
            // 
            // eFilterStatus
            // 
            this.eFilterStatus.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.eFilterStatus.FormattingEnabled = true;
            this.eFilterStatus.Location = new System.Drawing.Point(512, 33);
            this.eFilterStatus.Name = "eFilterStatus";
            this.eFilterStatus.Size = new System.Drawing.Size(175, 28);
            this.eFilterStatus.TabIndex = 4;
            this.eFilterStatus.SelectedValueChanged += new System.EventHandler(this.eFilterCategory_SelectedValueChanged);
            // 
            // label2
            // 
            this.label2.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(336, 7);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(80, 20);
            this.label2.TabIndex = 3;
            this.label2.Text = "Prioridade";
            // 
            // eFilterPriority
            // 
            this.eFilterPriority.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.eFilterPriority.FormattingEnabled = true;
            this.eFilterPriority.Location = new System.Drawing.Point(336, 33);
            this.eFilterPriority.Name = "eFilterPriority";
            this.eFilterPriority.Size = new System.Drawing.Size(170, 28);
            this.eFilterPriority.TabIndex = 2;
            this.eFilterPriority.SelectedIndexChanged += new System.EventHandler(this.eFilterCategory_SelectedValueChanged);
            // 
            // label1
            // 
            this.label1.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(152, 7);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(78, 20);
            this.label1.TabIndex = 1;
            this.label1.Text = "Categoria";
            // 
            // eFilterCategory
            // 
            this.eFilterCategory.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.eFilterCategory.FormattingEnabled = true;
            this.eFilterCategory.Location = new System.Drawing.Point(152, 33);
            this.eFilterCategory.Name = "eFilterCategory";
            this.eFilterCategory.Size = new System.Drawing.Size(178, 28);
            this.eFilterCategory.TabIndex = 0;
            this.eFilterCategory.SelectedValueChanged += new System.EventHandler(this.eFilterCategory_SelectedValueChanged);
            // 
            // panel2
            // 
            this.panel2.Controls.Add(this.eSelectedUser);
            this.panel2.Controls.Add(this.label13);
            this.panel2.Controls.Add(this.eSelectedSupportUser);
            this.panel2.Controls.Add(this.label12);
            this.panel2.Controls.Add(this.eSelectedInsertDate);
            this.panel2.Controls.Add(this.label11);
            this.panel2.Controls.Add(this.eSelectedDescription);
            this.panel2.Controls.Add(this.label10);
            this.panel2.Controls.Add(this.eSelectedStatus);
            this.panel2.Controls.Add(this.label9);
            this.panel2.Controls.Add(this.eSelectedPriority);
            this.panel2.Controls.Add(this.label8);
            this.panel2.Controls.Add(this.eSelectedCategory);
            this.panel2.Controls.Add(this.label7);
            this.panel2.Controls.Add(this.eSelectedSubject);
            this.panel2.Controls.Add(this.eSelectedId);
            this.panel2.Controls.Add(this.label6);
            this.panel2.Controls.Add(this.label5);
            this.panel2.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.panel2.Location = new System.Drawing.Point(3, 291);
            this.panel2.Name = "panel2";
            this.panel2.Size = new System.Drawing.Size(873, 234);
            this.panel2.TabIndex = 0;
            // 
            // eSelectedUser
            // 
            this.eSelectedUser.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.eSelectedUser.Location = new System.Drawing.Point(6, 189);
            this.eSelectedUser.Name = "eSelectedUser";
            this.eSelectedUser.ReadOnly = true;
            this.eSelectedUser.Size = new System.Drawing.Size(324, 26);
            this.eSelectedUser.TabIndex = 25;
            // 
            // label13
            // 
            this.label13.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.label13.AutoSize = true;
            this.label13.Location = new System.Drawing.Point(2, 166);
            this.label13.Name = "label13";
            this.label13.Size = new System.Drawing.Size(87, 20);
            this.label13.TabIndex = 24;
            this.label13.Text = "Solicitante:";
            // 
            // eSelectedSupportUser
            // 
            this.eSelectedSupportUser.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.eSelectedSupportUser.Location = new System.Drawing.Point(339, 189);
            this.eSelectedSupportUser.Name = "eSelectedSupportUser";
            this.eSelectedSupportUser.ReadOnly = true;
            this.eSelectedSupportUser.Size = new System.Drawing.Size(324, 26);
            this.eSelectedSupportUser.TabIndex = 23;
            // 
            // label12
            // 
            this.label12.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.label12.AutoSize = true;
            this.label12.Location = new System.Drawing.Point(336, 166);
            this.label12.Name = "label12";
            this.label12.Size = new System.Drawing.Size(88, 20);
            this.label12.TabIndex = 22;
            this.label12.Text = "Atendente:";
            // 
            // eSelectedInsertDate
            // 
            this.eSelectedInsertDate.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.eSelectedInsertDate.Location = new System.Drawing.Point(669, 189);
            this.eSelectedInsertDate.Name = "eSelectedInsertDate";
            this.eSelectedInsertDate.ReadOnly = true;
            this.eSelectedInsertDate.Size = new System.Drawing.Size(190, 26);
            this.eSelectedInsertDate.TabIndex = 21;
            // 
            // label11
            // 
            this.label11.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.label11.AutoSize = true;
            this.label11.Location = new System.Drawing.Point(668, 166);
            this.label11.Name = "label11";
            this.label11.Size = new System.Drawing.Size(75, 20);
            this.label11.TabIndex = 20;
            this.label11.Text = "Abertura:";
            // 
            // eSelectedDescription
            // 
            this.eSelectedDescription.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.eSelectedDescription.BackColor = System.Drawing.SystemColors.ButtonFace;
            this.eSelectedDescription.Location = new System.Drawing.Point(5, 88);
            this.eSelectedDescription.Name = "eSelectedDescription";
            this.eSelectedDescription.ReadOnly = true;
            this.eSelectedDescription.Size = new System.Drawing.Size(854, 66);
            this.eSelectedDescription.TabIndex = 19;
            this.eSelectedDescription.Text = "";
            // 
            // label10
            // 
            this.label10.AutoSize = true;
            this.label10.Location = new System.Drawing.Point(10, 66);
            this.label10.Name = "label10";
            this.label10.Size = new System.Drawing.Size(84, 20);
            this.label10.TabIndex = 18;
            this.label10.Text = "Descrição:";
            // 
            // eSelectedStatus
            // 
            this.eSelectedStatus.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.eSelectedStatus.Location = new System.Drawing.Point(669, 27);
            this.eSelectedStatus.Name = "eSelectedStatus";
            this.eSelectedStatus.ReadOnly = true;
            this.eSelectedStatus.Size = new System.Drawing.Size(190, 26);
            this.eSelectedStatus.TabIndex = 17;
            // 
            // label9
            // 
            this.label9.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.label9.AutoSize = true;
            this.label9.Location = new System.Drawing.Point(668, 4);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(60, 20);
            this.label9.TabIndex = 16;
            this.label9.Text = "Status:";
            // 
            // eSelectedPriority
            // 
            this.eSelectedPriority.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.eSelectedPriority.Location = new System.Drawing.Point(470, 27);
            this.eSelectedPriority.Name = "eSelectedPriority";
            this.eSelectedPriority.ReadOnly = true;
            this.eSelectedPriority.Size = new System.Drawing.Size(190, 26);
            this.eSelectedPriority.TabIndex = 15;
            // 
            // label8
            // 
            this.label8.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.label8.AutoSize = true;
            this.label8.Location = new System.Drawing.Point(469, 4);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(84, 20);
            this.label8.TabIndex = 14;
            this.label8.Text = "Prioridade:";
            // 
            // eSelectedCategory
            // 
            this.eSelectedCategory.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.eSelectedCategory.Location = new System.Drawing.Point(274, 27);
            this.eSelectedCategory.Name = "eSelectedCategory";
            this.eSelectedCategory.ReadOnly = true;
            this.eSelectedCategory.Size = new System.Drawing.Size(190, 26);
            this.eSelectedCategory.TabIndex = 13;
            // 
            // label7
            // 
            this.label7.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(273, 4);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(82, 20);
            this.label7.TabIndex = 12;
            this.label7.Text = "Categoria:";
            // 
            // eSelectedSubject
            // 
            this.eSelectedSubject.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.eSelectedSubject.Location = new System.Drawing.Point(115, 27);
            this.eSelectedSubject.Name = "eSelectedSubject";
            this.eSelectedSubject.ReadOnly = true;
            this.eSelectedSubject.Size = new System.Drawing.Size(153, 26);
            this.eSelectedSubject.TabIndex = 11;
            // 
            // eSelectedId
            // 
            this.eSelectedId.Location = new System.Drawing.Point(9, 27);
            this.eSelectedId.Name = "eSelectedId";
            this.eSelectedId.ReadOnly = true;
            this.eSelectedId.Size = new System.Drawing.Size(100, 26);
            this.eSelectedId.TabIndex = 10;
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(114, 4);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(72, 20);
            this.label6.TabIndex = 9;
            this.label6.Text = "Assunto:";
            this.label6.Click += new System.EventHandler(this.label6_Click);
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(10, 4);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(27, 20);
            this.label5.TabIndex = 8;
            this.label5.Text = "Id:";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(887, 735);
            this.Controls.Add(this.tabControl1);
            this.Controls.Add(this.panel1);
            this.Controls.Add(this.statusStrip1);
            this.Controls.Add(this.menuStrip1);
            this.MainMenuStrip = this.menuStrip1;
            this.MinimumSize = new System.Drawing.Size(909, 791);
            this.Name = "Form1";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Techflow";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.Form1_FormClosing);
            this.Shown += new System.EventHandler(this.Form1_Shown);
            this.statusStrip1.ResumeLayout(false);
            this.statusStrip1.PerformLayout();
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.bindingSource1)).EndInit();
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.toolStrip1.ResumeLayout(false);
            this.toolStrip1.PerformLayout();
            this.tabControl1.ResumeLayout(false);
            this.tabPage1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).EndInit();
            this.panel3.ResumeLayout(false);
            this.panel3.PerformLayout();
            this.panel2.ResumeLayout(false);
            this.panel2.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.StatusStrip statusStrip1;
        private System.Windows.Forms.ToolStripStatusLabel eMsg;
        private System.Windows.Forms.ToolStripStatusLabel eMsgConnection;
        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem mFile;
        private System.Windows.Forms.ToolStripMenuItem mChangeUser;
        private System.Windows.Forms.ToolStripSeparator toolStripMenuItem1;
        private System.Windows.Forms.ToolStripMenuItem mConfig;
        private System.Windows.Forms.ToolStripSeparator toolStripMenuItem2;
        private System.Windows.Forms.ToolStripMenuItem mExit;
        private System.Windows.Forms.ToolStripMenuItem mHelp;
        private System.Windows.Forms.ToolStripMenuItem mAbout;
        private System.Windows.Forms.BindingSource bindingSource1;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.TabControl tabControl1;
        private System.Windows.Forms.TabPage tabPage1;
        private System.Windows.Forms.ToolStrip toolStrip1;
        private System.Windows.Forms.ToolStripButton bNewTicket;
        private System.Windows.Forms.ToolStripButton bCommentTicket;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
        private System.Windows.Forms.ToolStripButton bDeleteTicket;
        private System.Windows.Forms.PictureBox pictureBox1;
        private System.Windows.Forms.Label eMsgUsername;
        private System.Windows.Forms.Panel panel2;
        private System.Windows.Forms.DataGridView dataGridView1;
        private System.Windows.Forms.Splitter splitter1;
        private System.Windows.Forms.Panel panel3;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox eFilterKey;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.ComboBox eFilterStatus;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.ComboBox eFilterPriority;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.ComboBox eFilterCategory;
        private System.Windows.Forms.ToolStripMenuItem mChangePassword;
        private System.Windows.Forms.TextBox eSelectedSubject;
        private System.Windows.Forms.TextBox eSelectedId;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.TextBox eSelectedInsertDate;
        private System.Windows.Forms.Label label11;
        private System.Windows.Forms.RichTextBox eSelectedDescription;
        private System.Windows.Forms.Label label10;
        private System.Windows.Forms.TextBox eSelectedStatus;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.TextBox eSelectedPriority;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.TextBox eSelectedCategory;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.TextBox eSelectedUser;
        private System.Windows.Forms.Label label13;
        private System.Windows.Forms.TextBox eSelectedSupportUser;
        private System.Windows.Forms.Label label12;
        private System.Windows.Forms.ToolStripButton bEditTicket;
    }
}

