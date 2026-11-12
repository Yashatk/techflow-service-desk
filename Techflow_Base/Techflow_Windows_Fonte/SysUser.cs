using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Techflow
{
    public class SysUser
    {
        private int _userId;
        private string _username;
        private string _name;
        private string _email;
        private string _password;

        public int UserId
        {
            get { return _userId; }
            set { _userId = value; }
        }
        public string Username
        {
            get { return _username; }
            set { _username = value; }
        }
        public string Email{
            get { return _email; }
            set { _email = value; }
        }
        public string Name
        {
            get { return _name; }
            set { _name = value; }
        }

        public string Password
        {
            get { return _password; }
            set { _password = value; }
        }
    }
}
