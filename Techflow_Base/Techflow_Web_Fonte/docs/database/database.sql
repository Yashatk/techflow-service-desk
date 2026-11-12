-- DROP SCHEMA dbo;

CREATE SCHEMA dbo;
-- techflow.dbo.tflow_acesso definição

-- Drop table

-- DROP TABLE techflow.dbo.tflow_acesso;

CREATE TABLE techflow.dbo.tflow_acesso (
	idacesso varchar(30) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	status int NOT NULL,
	descricao varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CONSTRAINT PK__tflow_ac__C72765B0A9152696 PRIMARY KEY (idacesso)
);


-- techflow.dbo.tflow_chamado_categoria definição

-- Drop table

-- DROP TABLE techflow.dbo.tflow_chamado_categoria;

CREATE TABLE techflow.dbo.tflow_chamado_categoria (
	idcategoria int NOT NULL,
	status int NOT NULL,
	datacriacao datetimeoffset(6) NULL,
	dataatualizacao datetimeoffset(6) NULL,
	categoria varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	CONSTRAINT PK__tflow_ch__140587C7B335C967 PRIMARY KEY (idcategoria)
);


-- techflow.dbo.tflow_chamado_prioridade definição

-- Drop table

-- DROP TABLE techflow.dbo.tflow_chamado_prioridade;

CREATE TABLE techflow.dbo.tflow_chamado_prioridade (
	idprioridade int NOT NULL,
	status int NOT NULL,
	prioridade varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	CONSTRAINT PK__tflow_ch__D378D8B7476764BA PRIMARY KEY (idprioridade)
);


-- techflow.dbo.tflow_chamado_status definição

-- Drop table

-- DROP TABLE techflow.dbo.tflow_chamado_status;

CREATE TABLE techflow.dbo.tflow_chamado_status (
	idstatus int NOT NULL,
	status int NOT NULL,
	status_chamado varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	CONSTRAINT PK__tflow_ch__EBF77C2DBAACCB93 PRIMARY KEY (idstatus)
);


-- techflow.dbo.tflow_grupo definição

-- Drop table

-- DROP TABLE techflow.dbo.tflow_grupo;

CREATE TABLE techflow.dbo.tflow_grupo (
	idgrupo int NOT NULL,
	status int NOT NULL,
	datacriacao datetimeoffset(6) NULL,
	dataatualizacao datetimeoffset(6) NULL,
	descricao varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	grupo varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	CONSTRAINT PK__tflow_gr__F8D5E6CE7C54999B PRIMARY KEY (idgrupo)
);


-- techflow.dbo.tflow_historico definição

-- Drop table

-- DROP TABLE techflow.dbo.tflow_historico;

CREATE TABLE techflow.dbo.tflow_historico (
	idhistorico int NOT NULL,
	status int NOT NULL,
	datacriacao datetimeoffset(6) NULL,
	dataatualizacao datetimeoffset(6) NULL,
	descricao text COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	idhistorico_tipo varchar(30) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CONSTRAINT PK__tflow_hi__E931C024F48C92A9 PRIMARY KEY (idhistorico)
);


-- techflow.dbo.tflow_usuario definição

-- Drop table

-- DROP TABLE techflow.dbo.tflow_usuario;

CREATE TABLE techflow.dbo.tflow_usuario (
	idusuario int NOT NULL,
	status int NOT NULL,
	datacriacao datetimeoffset(6) NULL,
	dataatualizacao datetimeoffset(6) NULL,
	email varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	nome varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	senha varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	sid varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	usuario varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	CONSTRAINT PK__tflow_us__080A9743D91EFAD2 PRIMARY KEY (idusuario)
);


-- techflow.dbo.tflow_chamado definição

-- Drop table

-- DROP TABLE techflow.dbo.tflow_chamado;

CREATE TABLE techflow.dbo.tflow_chamado (
	idchamado int IDENTITY(1,1) NOT NULL,
	status int NOT NULL,
	datacriacao datetimeoffset(6) NULL,
	dataatualizacao datetimeoffset(6) NULL,
	descricao text COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	assunto varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	idcategoria int NOT NULL,
	idprioridade int NULL,
	idstatus int NOT NULL,
	idusuario int NOT NULL,
	CONSTRAINT PK__tflow_ch__B559BE14DBDF8DC0 PRIMARY KEY (idchamado),
	CONSTRAINT FKji61nicvpfm8051dui5o7n29c FOREIGN KEY (idstatus) REFERENCES techflow.dbo.tflow_chamado_status(idstatus),
	CONSTRAINT fktflow_chamado_categoria FOREIGN KEY (idcategoria) REFERENCES techflow.dbo.tflow_chamado_categoria(idcategoria),
	CONSTRAINT fktflow_chamado_prioridade FOREIGN KEY (idprioridade) REFERENCES techflow.dbo.tflow_chamado_prioridade(idprioridade),
	CONSTRAINT fktflow_chamado_usuario FOREIGN KEY (idusuario) REFERENCES techflow.dbo.tflow_usuario(idusuario)
);


-- techflow.dbo.tflow_chamado_detalhe definição

-- Drop table

-- DROP TABLE techflow.dbo.tflow_chamado_detalhe;

CREATE TABLE techflow.dbo.tflow_chamado_detalhe (
	idchamado_detalhe int NOT NULL,
	status int NOT NULL,
	datacriacao datetimeoffset(6) NULL,
	dataatualizacao datetimeoffset(6) NULL,
	descricao text COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	idstatus int NOT NULL,
	idchamado int NOT NULL,
	idusuario int NOT NULL,
	CONSTRAINT PK__tflow_ch__8660E5BCED658560 PRIMARY KEY (idchamado_detalhe),
	CONSTRAINT fktflow_chamado_detalhe_chamado FOREIGN KEY (idchamado) REFERENCES techflow.dbo.tflow_chamado(idchamado),
	CONSTRAINT fktflow_chamado_detalhe_status FOREIGN KEY (idstatus) REFERENCES techflow.dbo.tflow_chamado_status(idstatus),
	CONSTRAINT fktflow_chamado_detalhe_usuario FOREIGN KEY (idusuario) REFERENCES techflow.dbo.tflow_usuario(idusuario)
);


-- techflow.dbo.tflow_grupo_acesso definição

-- Drop table

-- DROP TABLE techflow.dbo.tflow_grupo_acesso;

CREATE TABLE techflow.dbo.tflow_grupo_acesso (
	idgrupo int NOT NULL,
	idacesso varchar(30) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	CONSTRAINT PK__tflow_gr__38AA3BDCD9F93899 PRIMARY KEY (idacesso,idgrupo),
	CONSTRAINT fktflow_grupo_acesso_acesso FOREIGN KEY (idacesso) REFERENCES techflow.dbo.tflow_acesso(idacesso),
	CONSTRAINT fktflow_grupo_acesso_grupo FOREIGN KEY (idgrupo) REFERENCES techflow.dbo.tflow_grupo(idgrupo)
);


-- techflow.dbo.tflow_grupo_usuario definição

-- Drop table

-- DROP TABLE techflow.dbo.tflow_grupo_usuario;

CREATE TABLE techflow.dbo.tflow_grupo_usuario (
	idusuario int NOT NULL,
	idgrupo int NOT NULL,
	CONSTRAINT PK__tflow_gr__D8554FBA012B0CFC PRIMARY KEY (idgrupo,idusuario),
	CONSTRAINT fktflow_grupo_usuario_grupo FOREIGN KEY (idgrupo) REFERENCES techflow.dbo.tflow_grupo(idgrupo),
	CONSTRAINT fktflow_grupo_usuario_usuario FOREIGN KEY (idusuario) REFERENCES techflow.dbo.tflow_usuario(idusuario)
);


