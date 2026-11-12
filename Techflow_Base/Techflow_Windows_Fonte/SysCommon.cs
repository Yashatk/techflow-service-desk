using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Techflow
{
    public class SysCommon
    {
        private int  _id;
        private string name;

        public int Id
        {
            get { return _id; }
            set { _id = value; }
        }
        public string Name 
        {
            get { return name; }
            set { name = value; }
        }
    }
}
