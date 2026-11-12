using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Techflow
{
    public class TicketDetail
    {
        public int Id { get; set; }
        public int Status { get; set; }
        public int TicketId { get; set; }
        public int StatusId { get; set; }
        public String StatusName { get; set; }
        public string Description { get; set; }
        public int UserId { get; set; }
        public String UserFullName { get; set; }
        public String Username { get; set;  }
        public String UserEmail { get; set; }
        public DateTimeOffset InsertDate { get; set; }
        public DateTimeOffset UpdateDate { get; set; }
    }
}
