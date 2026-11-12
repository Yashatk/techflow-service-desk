using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Techflow
{
    public class Utils
    {
        public static void ComboBox(ComboBox cb, List<SysCommon> items)
        {
            cb.Items.Clear();
            foreach (var item in items)
            {
                cb.Items.Add(item);
            }
            cb.DisplayMember = "Name";
            cb.ValueMember = "Id";
        }
    }
}
