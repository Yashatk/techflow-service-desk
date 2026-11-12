namespace Techflow
{
    partial class FormNotes
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
            this.panel2 = new System.Windows.Forms.Panel();
            this.eNotes = new System.Windows.Forms.RichTextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.eSelectedSubject = new System.Windows.Forms.TextBox();
            this.eSelectedId = new System.Windows.Forms.TextBox();
            this.label6 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.panel1.SuspendLayout();
            this.panel2.SuspendLayout();
            this.SuspendLayout();
            // 
            // panel1
            // 
            this.panel1.Controls.Add(this.bCancel);
            this.panel1.Controls.Add(this.bOK);
            this.panel1.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.panel1.Location = new System.Drawing.Point(0, 241);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(521, 72);
            this.panel1.TabIndex = 1;
            // 
            // bCancel
            // 
            this.bCancel.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.bCancel.Location = new System.Drawing.Point(145, 16);
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
            this.bOK.Location = new System.Drawing.Point(330, 16);
            this.bOK.Name = "bOK";
            this.bOK.Size = new System.Drawing.Size(179, 40);
            this.bOK.TabIndex = 0;
            this.bOK.Text = "&Incluir Comentário";
            this.bOK.UseVisualStyleBackColor = true;
            this.bOK.Click += new System.EventHandler(this.bOK_Click);
            // 
            // panel2
            // 
            this.panel2.Controls.Add(this.eSelectedSubject);
            this.panel2.Controls.Add(this.eSelectedId);
            this.panel2.Controls.Add(this.label6);
            this.panel2.Controls.Add(this.label5);
            this.panel2.Dock = System.Windows.Forms.DockStyle.Top;
            this.panel2.Location = new System.Drawing.Point(0, 0);
            this.panel2.Name = "panel2";
            this.panel2.Size = new System.Drawing.Size(521, 70);
            this.panel2.TabIndex = 2;
            // 
            // eNotes
            // 
            this.eNotes.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.eNotes.Location = new System.Drawing.Point(12, 107);
            this.eNotes.Name = "eNotes";
            this.eNotes.Size = new System.Drawing.Size(497, 116);
            this.eNotes.TabIndex = 3;
            this.eNotes.Text = "";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(12, 77);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(95, 20);
            this.label1.TabIndex = 4;
            this.label1.Text = "Comentário:";
            // 
            // eSelectedSubject
            // 
            this.eSelectedSubject.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.eSelectedSubject.Location = new System.Drawing.Point(118, 30);
            this.eSelectedSubject.Name = "eSelectedSubject";
            this.eSelectedSubject.ReadOnly = true;
            this.eSelectedSubject.Size = new System.Drawing.Size(391, 26);
            this.eSelectedSubject.TabIndex = 15;
            // 
            // eSelectedId
            // 
            this.eSelectedId.Location = new System.Drawing.Point(12, 30);
            this.eSelectedId.Name = "eSelectedId";
            this.eSelectedId.ReadOnly = true;
            this.eSelectedId.Size = new System.Drawing.Size(100, 26);
            this.eSelectedId.TabIndex = 14;
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(117, 7);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(72, 20);
            this.label6.TabIndex = 13;
            this.label6.Text = "Assunto:";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(13, 7);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(27, 20);
            this.label5.TabIndex = 12;
            this.label5.Text = "Id:";
            // 
            // FormNotes
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(521, 313);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.eNotes);
            this.Controls.Add(this.panel2);
            this.Controls.Add(this.panel1);
            this.MinimumSize = new System.Drawing.Size(543, 369);
            this.Name = "FormNotes";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;
            this.Text = "Comentário";
            this.panel1.ResumeLayout(false);
            this.panel2.ResumeLayout(false);
            this.panel2.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.Button bCancel;
        private System.Windows.Forms.Button bOK;
        private System.Windows.Forms.Panel panel2;
        private System.Windows.Forms.RichTextBox eNotes;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox eSelectedSubject;
        private System.Windows.Forms.TextBox eSelectedId;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label5;
    }
}