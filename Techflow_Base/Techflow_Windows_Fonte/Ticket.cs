using System;

namespace Techflow
{
 public class Ticket
 {
 public int Id { get; set; }
 public string Subject { get; set; }
 public string Description { get; set; }
 public int StatusId { get; set; }
 public string StatusName { get; set; }
 public int PriorityId { get; set; }
 public string PriorityName { get; set; }
 public int CategoryId { get; set; }
 public string CategoryName { get; set; }
 public int UserId { get; set; }
 public string UserFullName { get; set; }
 public string UserEmail { get; set; }
 public string Username { get; set; }

 public int SupportUserId { get; set; } 

 public string SupportUserFullName { get; set; }

 public string SupportUsername { get; set; }

 public string SupportUserEmail { get; set; }
 
 public DateTimeOffset InsertDate { get; set; }
 public DateTimeOffset UpdateDate { get; set; }
 }
}
