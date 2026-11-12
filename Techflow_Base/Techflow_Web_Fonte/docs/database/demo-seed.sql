-- Demo data for local evaluation only. All names and e-mails are fictitious.
-- Login: demo / demo123  (never use this account outside a local environment)
-- Run after the first application boot: the app creates tables, statuses,
-- priorities, categories, access rights and groups on startup.
-- Idempotent: safe to run more than once.

IF NOT EXISTS (SELECT 1 FROM tflow_usuario WHERE usuario = 'demo')
INSERT INTO tflow_usuario (status, datacriacao, email, nome, senha, usuario) VALUES
 (1, SYSDATETIMEOFFSET(), 'demo@techflow.example', 'Usuário Demo',
  LOWER(CONVERT(varchar(32), HASHBYTES('MD5', 'demo123'), 2)), 'demo');

IF NOT EXISTS (SELECT 1 FROM tflow_usuario WHERE usuario = 'analista')
INSERT INTO tflow_usuario (status, datacriacao, email, nome, senha, usuario) VALUES
 (1, SYSDATETIMEOFFSET(), 'analista@techflow.example', 'Analista Demo',
  LOWER(CONVERT(varchar(32), HASHBYTES('MD5', 'demo123'), 2)), 'analista');

DECLARE @admins int = (SELECT idgrupo FROM tflow_grupo WHERE grupo = 'Administrador');
DECLARE @analysts int = (SELECT idgrupo FROM tflow_grupo WHERE grupo = 'Analistas');
DECLARE @u int = (SELECT idusuario FROM tflow_usuario WHERE usuario = 'demo');
DECLARE @a int = (SELECT idusuario FROM tflow_usuario WHERE usuario = 'analista');

INSERT INTO tflow_grupo_acesso (idacesso, idgrupo)
SELECT ac.idacesso, @admins FROM tflow_acesso ac
WHERE NOT EXISTS (SELECT 1 FROM tflow_grupo_acesso ga WHERE ga.idacesso = ac.idacesso AND ga.idgrupo = @admins);

IF NOT EXISTS (SELECT 1 FROM tflow_grupo_usuario WHERE idgrupo = @admins AND idusuario = @u)
INSERT INTO tflow_grupo_usuario (idgrupo, idusuario) VALUES (@admins, @u);

IF @analysts IS NOT NULL AND NOT EXISTS (SELECT 1 FROM tflow_grupo_usuario WHERE idgrupo = @analysts AND idusuario = @a)
INSERT INTO tflow_grupo_usuario (idgrupo, idusuario) VALUES (@analysts, @a);

IF NOT EXISTS (SELECT 1 FROM tflow_chamado)
INSERT INTO tflow_chamado (status, datacriacao, dataatualizacao, assunto, descricao, idcategoria, idprioridade, idstatus, idusuario, idusuariosuporte) VALUES
 (1, DATEADD(day, -6, SYSDATETIMEOFFSET()), DATEADD(day, -5, SYSDATETIMEOFFSET()), 'Notebook não liga após atualização', 'Equipamento desligou durante a atualização do sistema e não inicializa mais.', (SELECT idcategoria FROM tflow_chamado_categoria WHERE categoria = 'TI'), 2, 2, @u, @a),
 (1, DATEADD(day, -4, SYSDATETIMEOFFSET()), DATEADD(day, -1, SYSDATETIMEOFFSET()), 'Erro ao gerar relatório mensal', 'O sistema financeiro retorna erro ao exportar o relatório do mês.', (SELECT idcategoria FROM tflow_chamado_categoria WHERE categoria = 'Financeiro'), 3, 4, @u, @a),
 (1, DATEADD(day, -2, SYSDATETIMEOFFSET()), DATEADD(day, -2, SYSDATETIMEOFFSET()), 'Wi-Fi instável na sala de reuniões', 'Conexão cai a cada poucos minutos durante videochamadas.', (SELECT idcategoria FROM tflow_chamado_categoria WHERE categoria = 'TI'), 2, 1, @u, NULL),
 (1, DATEADD(hour, -5, SYSDATETIMEOFFSET()), DATEADD(hour, -5, SYSDATETIMEOFFSET()), 'Acesso bloqueado ao sistema de ponto', 'Conta bloqueada após tentativas de login sem sucesso.', (SELECT idcategoria FROM tflow_chamado_categoria WHERE categoria = 'RH'), 1, 1, @u, NULL),
 (1, DATEADD(day, -9, SYSDATETIMEOFFSET()), DATEADD(day, -8, SYSDATETIMEOFFSET()), 'Divergência no saldo de estoque', 'Quantidade em sistema não confere com a contagem física do item.', (SELECT idcategoria FROM tflow_chamado_categoria WHERE categoria = 'Estoque'), 3, 2, @u, @a);
