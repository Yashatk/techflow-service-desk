using System;
using System.Collections.Generic;
using System.Data.Common;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using static System.Windows.Forms.VisualStyles.VisualStyleElement.TrackBar;

namespace Techflow
{
    public class Repository
    {
        public static SysUser FindUserByUsername(SqlConnection _connection, String username)
        {
            SysUser _sysUser = null;
            string query = "select idusuario as userId, usuario as username, nome as name, email, senha as password from tflow_usuario where status=1 and usuario = @username";
            using (var cmd = new SqlCommand(query, _connection))
            {
                cmd.Parameters.AddWithValue("@username", username.Trim());
                using (var reader = cmd.ExecuteReader())
                {
                    if (reader.Read())
                    {
                        _sysUser = new SysUser
                        {
                            UserId = reader.GetInt32(reader.GetOrdinal("userId")),
                            Username = reader.GetString(reader.GetOrdinal("username")),
                            Name = reader.GetString(reader.GetOrdinal("name")),
                            Email = reader.GetString(reader.GetOrdinal("email")),
                            Password = reader.GetString(reader.GetOrdinal("password"))
                        };
                    }
                }
            }
            return _sysUser;
        }

        public static int ChangePassword(SqlConnection _connection, int userId, String encryptedPassword)
        {
            string query = "update tflow_usuario set senha=@senha, dataatualizacao=@dataatualizacao where idusuario=@idusuario";
            using (var cmd = new SqlCommand(query, _connection))
            {
                cmd.Parameters.AddWithValue("@senha", encryptedPassword);
                cmd.Parameters.AddWithValue("@dataatualizacao", DateTime.Now);
                cmd.Parameters.AddWithValue("@idusuario", userId);
                return cmd.ExecuteNonQuery();
            }
        }

        public static int history(SqlConnection _connection, String code, String description)
        {
            string query = "insert into tflow_historico(status, idhistorico_tipo, descricao, datacriacao, dataatualizacao) " +
                           "values (@status, @idhistorico_tipo, @descricao, @datacriacao, @dataatualizacao)";
            using (var cmd = new SqlCommand(query, _connection))
            {
                cmd.Parameters.AddWithValue("@status", 1);
                cmd.Parameters.AddWithValue("@idhistorico_tipo", code);
                cmd.Parameters.AddWithValue("@descricao", description);
                cmd.Parameters.AddWithValue("@datacriacao", DateTime.Now);
                cmd.Parameters.AddWithValue("@dataatualizacao", DateTime.Now);
                return cmd.ExecuteNonQuery();
            }
        }

        public static List<SysCommon> CommonList(SqlConnection _connection, String query)
        {
            var list = new List<SysCommon>();            
            using (var cmd = new SqlCommand(query, _connection))
            {
                using (var reader = cmd.ExecuteReader())
                {
                    while (reader.Read())
                    {                        
                        list.Add(new SysCommon
                        {
                            Id = reader.GetInt32(reader.GetOrdinal("id")),
                            Name = reader.GetString(reader.GetOrdinal("name"))
                        });
                    }
                }
            }
            return list;
        }

        public static List<SysCommon> CategoryList(SqlConnection _connection)
        {
            return CommonList(_connection, "select idcategoria as id, categoria as name from tflow_chamado_categoria where status=1 order by categoria");
        }

        public static List<SysCommon> PriorityList(SqlConnection _connection)
        {
            return CommonList(_connection, "select idprioridade as id, prioridade as name from tflow_chamado_prioridade");
        }

        public static List<SysCommon> StatusList(SqlConnection _connection)
        {
            return CommonList(_connection, "select idstatus as id, status_chamado as name from tflow_chamado_status");
        }

        public static int TicketCreate(SqlConnection _connection, Ticket ticket)
        {
            string query = @"insert into tflow_chamado 
                            (status, assunto, descricao, idstatus, idprioridade, idcategoria, idusuario, datacriacao, dataatualizacao) 
                            values 
                            (1, @assunto, @descricao, @idstatus, @idprioridade, @idcategoria, @idusuario, @datacriacao, @dataatualizacao);
                            select SCOPE_IDENTITY();";
            using (var cmd = new SqlCommand(query, _connection))
            {
                cmd.Parameters.AddWithValue("@assunto", ticket.Subject);
                cmd.Parameters.AddWithValue("@descricao", ticket.Description);
                cmd.Parameters.AddWithValue("@idstatus", ticket.StatusId);
                cmd.Parameters.AddWithValue("@idprioridade", ticket.PriorityId);
                cmd.Parameters.AddWithValue("@idcategoria", ticket.CategoryId > 0 ? (object)ticket.CategoryId : DBNull.Value);
                cmd.Parameters.AddWithValue("@idusuario", ticket.UserId);                
                cmd.Parameters.AddWithValue("@datacriacao", DateTimeOffset.Now);
                cmd.Parameters.AddWithValue("@dataatualizacao", DateTimeOffset.Now);
                return cmd.ExecuteNonQuery();
            }
        }

        public static int TicketUpdateStatus(SqlConnection _connection, int ticketId, int statusId)
        {
            string query = @"update tflow_chamado set idstatus=@idstatus where idchamado=@id";
            using (var cmd = new SqlCommand(query, _connection))
            {
                cmd.Parameters.AddWithValue("@idstatus", statusId);
                cmd.Parameters.AddWithValue("@id", ticketId);
                return cmd.ExecuteNonQuery();
            }
        }

        
        public static List<Ticket> TicketFindBy(SqlConnection _connection, TicketFilter filter)
        {
            var tickets = new List<Ticket>();
            var query = new StringBuilder();
            query.Append(@"select 
                        tc.idchamado,
                        tc.assunto,
                        tc.descricao,
                        tc.idstatus,
                        tcs.status_chamado,
                        tc.idprioridade,
                        tcp.prioridade,
                        tc.idcategoria,
                        tcc.categoria,
                        tc.idusuario,
                        tu.nome,
                        tu.email,
                        tu.usuario,
                        tc.idusuariosuporte,
                        tus.nome as nomeusuariosuporte,
                        tus.email as emailusuariosuporte,
                        tus.usuario as usuariosuporte,
                        tc.datacriacao,
                        tc.dataatualizacao
                        from tflow_chamado tc 
                        inner join tflow_chamado_status tcs on tc.idstatus = tcs.idstatus
                        left join tflow_chamado_categoria tcc on tc.idcategoria = tcc.idcategoria 
                        inner join tflow_chamado_prioridade tcp on tc.idprioridade = tcp.idprioridade
                        inner join tflow_usuario tu on tc.idusuario = tu.idusuario
                        left join tflow_usuario tus on tc.idusuariosuporte = tus.idusuario
                        where 1=1");

            var cmd = new SqlCommand();
            cmd.Connection = _connection;

            if(filter.Status.HasValue && filter.Status > 0)
            {
                query.Append(" and tc.status = @status");
                cmd.Parameters.AddWithValue("@status", filter.Status.Value);
            }
            if (filter.StatusId.HasValue && filter.StatusId > 0)
            {
                query.Append(" and tc.idstatus = @idstatus");
                cmd.Parameters.AddWithValue("@idstatus", filter.StatusId.Value);
            }
            if (filter.PriorityId.HasValue && filter.PriorityId > 0)
            {
                query.Append(" and tc.idprioridade = @idprioridade");
                cmd.Parameters.AddWithValue("@idprioridade", filter.PriorityId.Value);
            }
            if (filter.CategoryId.HasValue && filter.CategoryId > 0)
            {
                query.Append(" and tc.idcategoria = @idcategoria");
                cmd.Parameters.AddWithValue("@idcategoria", filter.CategoryId.Value);
            }
            if (filter.UserId.HasValue && filter.UserId > 0)
            {
                query.Append(" and tc.idusuario = @idusuario");
                cmd.Parameters.AddWithValue("@idusuario", filter.UserId.Value);
            }
            if(filter.SupportUserId.HasValue && filter.SupportUserId > 0)
            {
                query.Append(" and tc.idusuariosuporte = @idusuariosuporte");
                cmd.Parameters.AddWithValue("@idusuariosuporte", filter.SupportUserId.Value);
            }
            if (!string.IsNullOrEmpty(filter.Key))
            {
                query.Append(" and (tc.assunto like @key or tc.descricao like @key)");
                cmd.Parameters.AddWithValue("@key", "%" + filter.Key + "%");
            }
            if (filter.StartDate.HasValue)
            {
                query.Append(" and tc.datacriacao >= @datacriacaoinicio");
                cmd.Parameters.AddWithValue("@datacriacaoinicio", filter.StartDate.Value);
            }
            if (filter.EndDate.HasValue)
            {
                query.Append(" and tc.datacriacao <= @datacriacaofim");
                cmd.Parameters.AddWithValue("@datacriacaofim", filter.EndDate.Value);
            }

            cmd.CommandText = query.ToString();

            using (var reader = cmd.ExecuteReader())
            {
                while (reader.Read())
                {
                    tickets.Add(new Ticket
                    {
                        Id = reader.GetInt32(reader.GetOrdinal("idchamado")),
                        Subject = reader.IsDBNull(reader.GetOrdinal("assunto")) ? "" : reader.GetString(reader.GetOrdinal("assunto")),
                        Description = reader.IsDBNull(reader.GetOrdinal("descricao")) ? "" : reader.GetString(reader.GetOrdinal("descricao")),
                        StatusId = reader.GetInt32(reader.GetOrdinal("idstatus")),
                        StatusName = reader.GetString(reader.GetOrdinal("status_chamado")),
                        PriorityId = reader.GetInt32(reader.GetOrdinal("idprioridade")),
                        PriorityName = reader.GetString(reader.GetOrdinal("prioridade")),
                        CategoryId = reader.IsDBNull(reader.GetOrdinal("idcategoria")) ? 0 : reader.GetInt32(reader.GetOrdinal("idcategoria")),
                        CategoryName = reader.IsDBNull(reader.GetOrdinal("categoria")) ? null : reader.GetString(reader.GetOrdinal("categoria")),

                        UserId = reader.GetInt32(reader.GetOrdinal("idusuario")),
                        UserFullName = reader.GetString(reader.GetOrdinal("nome")),
                        UserEmail = reader.GetString(reader.GetOrdinal("email")),
                        Username = reader.GetString(reader.GetOrdinal("usuario")),

                        SupportUserId = reader.IsDBNull(reader.GetOrdinal("idusuariosuporte")) ? 0 : reader.GetInt32(reader.GetOrdinal("idusuariosuporte")),
                        SupportUserFullName = reader.IsDBNull(reader.GetOrdinal("nomeusuariosuporte")) ? null : reader.GetString(reader.GetOrdinal("nomeusuariosuporte")),
                        SupportUserEmail = reader.IsDBNull(reader.GetOrdinal("emailusuariosuporte")) ? null : reader.GetString(reader.GetOrdinal("emailusuariosuporte")),
                        SupportUsername = reader.IsDBNull(reader.GetOrdinal("usuariosuporte")) ? null : reader.GetString(reader.GetOrdinal("usuariosuporte")),

                        InsertDate = reader.IsDBNull(reader.GetOrdinal("datacriacao")) ? DateTimeOffset.Now : reader.GetFieldValue<DateTimeOffset>(reader.GetOrdinal("datacriacao")),
                        UpdateDate = reader.IsDBNull(reader.GetOrdinal("dataatualizacao")) ? DateTimeOffset.Now : reader.GetFieldValue<DateTimeOffset>(reader.GetOrdinal("dataatualizacao"))
                    });
                }
            }
            return tickets;
        }

        public static int TicketDetailCreate(SqlConnection _connection, int ticketId, String description, int statusId, int userId)
        {
            string query = @"insert into tflow_chamado_detalhe
                            (status, idchamado, descricao, idstatus, idusuario, datacriacao, dataatualizacao) 
                            values 
                            (1, @idchamado, @descricao, @idstatus, @idusuario, @datacriacao, @dataatualizacao);
                            select SCOPE_IDENTITY();";
            using (var cmd = new SqlCommand(query, _connection))
            {
                cmd.Parameters.AddWithValue("@idchamado", ticketId);
                cmd.Parameters.AddWithValue("@descricao", description);
                cmd.Parameters.AddWithValue("@idstatus", statusId);
                cmd.Parameters.AddWithValue("@idusuario", userId);
                cmd.Parameters.AddWithValue("@datacriacao", DateTime.Now);
                cmd.Parameters.AddWithValue("@dataatualizacao", DateTime.Now);
                return cmd.ExecuteNonQuery();
            }
        }

        public static List<TicketDetail> TicketDetailFindBy(SqlConnection _connection, int ticketId)
        {
            var ticketDetails = new List<TicketDetail>();
            var query = new StringBuilder();

            query.Append(@"select
                tcd.idchamado_detalhe,
                tcd.status,
                tcd.idchamado,
                tcd.idstatus,
                tcs.status_chamado,
                tcd.descricao,
                tcd.idusuario,
                tu.nome,
                tu.email,
                tu.usuario,
                tcd.datacriacao,
                tcd.dataatualizacao
                from tflow_chamado_detalhe tcd
                inner join tflow_chamado_status tcs on tcd.idstatus = tcs.idstatus 
                inner join tflow_usuario tu on tu.idusuario = tcd.idusuario 
                where
                tcd.status = 1
                and tcd.idchamado = @ticketId");

            var cmd = new SqlCommand();
            cmd.Connection = _connection;
            cmd.CommandText = query.ToString();
            cmd.Parameters.AddWithValue("@ticketId", ticketId);

            using (var reader = cmd.ExecuteReader())
            {
                while (reader.Read())
                {
                    ticketDetails.Add(new TicketDetail
                    {
                        Id = reader.GetInt32(reader.GetOrdinal("idchamado_detalhe")),
                        Status = reader.GetInt32(reader.GetOrdinal("status")),
                        TicketId = reader.GetInt32(reader.GetOrdinal("idchamado")),
                        StatusId = reader.GetInt32(reader.GetOrdinal("idstatus")),
                        StatusName = reader.GetString(reader.GetOrdinal("status_chamado")),
                        Description = reader.IsDBNull(reader.GetOrdinal("descricao")) ? "" : reader.GetString(reader.GetOrdinal("descricao")),
                        UserId = reader.GetInt32(reader.GetOrdinal("idusuario")),
                        UserFullName = reader.GetString(reader.GetOrdinal("nome")),
                        UserEmail = reader.GetString(reader.GetOrdinal("email")),
                        Username = reader.GetString(reader.GetOrdinal("usuario")),
                        InsertDate = reader.GetFieldValue<DateTimeOffset>(reader.GetOrdinal("datacriacao")),
                        UpdateDate = reader.GetFieldValue<DateTimeOffset>(reader.GetOrdinal("dataatualizacao"))
                    });
                }
            }
            return ticketDetails;
        }

    }
}
