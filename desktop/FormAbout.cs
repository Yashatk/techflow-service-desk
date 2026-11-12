using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Techflow
{
    public partial class FormAbout : Form
    {
        public FormAbout()
        {
            InitializeComponent();
            labelApp.Text = Application.ProductName;
            labelVersion.Text = "Versão: " + Application.ProductVersion;
            eAbout.LoadFile("about.txt", RichTextBoxStreamType.PlainText);
        }

        private void bLogin_Click(object sender, EventArgs e)
        {
            Close();
        }
    }
}
