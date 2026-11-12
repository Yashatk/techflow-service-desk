-- Baseline schema (generated from the JPA mappings, SQL Server).
-- Existing databases created by the former ddl-auto=update are baselined at version 1 and skip this script.

    create table tflow_acesso (
        status int not null,
        idacesso varchar(30) not null,
        descricao varchar(255),
        primary key (idacesso)
    );

    create table tflow_chamado (
        idcategoria int not null,
        idchamado int identity not null,
        idprioridade int,
        idstatus int not null,
        idusuario int not null,
        idusuariosuporte int,
        status int not null,
        dataatualizacao datetimeoffset(6),
        datacriacao datetimeoffset(6),
        assunto varchar(100) not null,
        descricao TEXT,
        primary key (idchamado)
    );

    create table tflow_chamado_categoria (
        idcategoria int identity not null,
        status int not null,
        dataatualizacao datetimeoffset(6),
        datacriacao datetimeoffset(6),
        categoria varchar(100) not null,
        primary key (idcategoria)
    );

    create table tflow_chamado_detalhe (
        idchamado int not null,
        idchamado_detalhe int identity not null,
        idstatus int not null,
        idusuario int not null,
        status int not null,
        dataatualizacao datetimeoffset(6),
        datacriacao datetimeoffset(6),
        descricao TEXT,
        primary key (idchamado_detalhe)
    );

    create table tflow_chamado_prioridade (
        idprioridade int not null,
        status int not null,
        prioridade varchar(50) not null,
        primary key (idprioridade)
    );

    create table tflow_chamado_status (
        idstatus int not null,
        status int not null,
        status_chamado varchar(50) not null,
        primary key (idstatus)
    );

    create table tflow_grupo (
        idgrupo int identity not null,
        status int not null,
        dataatualizacao datetimeoffset(6),
        datacriacao datetimeoffset(6),
        grupo varchar(100) not null,
        descricao varchar(255),
        primary key (idgrupo)
    );

    create table tflow_grupo_acesso (
        idgrupo int not null,
        idacesso varchar(30) not null,
        primary key (idgrupo, idacesso)
    );

    create table tflow_grupo_usuario (
        idgrupo int not null,
        idusuario int not null,
        primary key (idgrupo, idusuario)
    );

    create table tflow_historico (
        idhistorico int identity not null,
        status int not null,
        dataatualizacao datetimeoffset(6),
        datacriacao datetimeoffset(6),
        idhistorico_tipo varchar(30),
        descricao TEXT,
        primary key (idhistorico)
    );

    create table tflow_usuario (
        idusuario int identity not null,
        status int not null,
        dataatualizacao datetimeoffset(6),
        datacriacao datetimeoffset(6),
        usuario varchar(50) not null,
        email varchar(100) not null,
        nome varchar(100) not null,
        senha varchar(100) not null,
        sid varchar(100),
        primary key (idusuario)
    );

    alter table tflow_grupo 
       add constraint uk_tflow_grupo_grupo unique (grupo);

    alter table tflow_usuario 
       add constraint uk_tflow_usuario_usuario unique (usuario);

    alter table tflow_chamado 
       add constraint fktflow_chamado_categoria 
       foreign key (idcategoria) 
       references tflow_chamado_categoria;

    alter table tflow_chamado 
       add constraint fktflow_chamado_prioridade 
       foreign key (idprioridade) 
       references tflow_chamado_prioridade;

    alter table tflow_chamado 
       add constraint fktflow_chamado_status 
       foreign key (idstatus) 
       references tflow_chamado_status;

    alter table tflow_chamado 
       add constraint fktflow_chamado_usuario_suporte 
       foreign key (idusuariosuporte) 
       references tflow_usuario;

    alter table tflow_chamado 
       add constraint fktflow_chamado_usuario 
       foreign key (idusuario) 
       references tflow_usuario;

    alter table tflow_chamado_detalhe 
       add constraint fktflow_chamado_detalhe_status 
       foreign key (idstatus) 
       references tflow_chamado_status;

    alter table tflow_chamado_detalhe 
       add constraint fktflow_chamado_detalhe_chamado 
       foreign key (idchamado) 
       references tflow_chamado;

    alter table tflow_chamado_detalhe 
       add constraint fktflow_chamado_detalhe_usuario 
       foreign key (idusuario) 
       references tflow_usuario;

    alter table tflow_grupo_acesso 
       add constraint fktflow_grupo_acesso_grupo 
       foreign key (idgrupo) 
       references tflow_grupo;

    alter table tflow_grupo_acesso 
       add constraint fktflow_grupo_acesso_acesso 
       foreign key (idacesso) 
       references tflow_acesso;

    alter table tflow_grupo_usuario 
       add constraint fktflow_grupo_usuario_usuario 
       foreign key (idusuario) 
       references tflow_usuario;

    alter table tflow_grupo_usuario 
       add constraint fktflow_grupo_usuario_grupo 
       foreign key (idgrupo) 
       references tflow_grupo;
