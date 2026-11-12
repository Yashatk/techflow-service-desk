using System;

namespace Techflow
{
 public class TicketFilter
 {
 public int? Id { get; set; }
 public int? Status { get; set; }
 public int? StatusId { get; set; }
 public int? PriorityId { get; set; }
 public int? CategoryId { get; set; }
 public int? UserId { get; set; }
 public int? SupportUserId { get; set; }
 public DateTimeOffset? StartDate { get; set; }
 public DateTimeOffset? EndDate { get; set; }
 public string Key { get; set; }
 }
}
