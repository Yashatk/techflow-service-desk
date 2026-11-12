# Arquitetura

## Componentes

```mermaid
flowchart LR
    subgraph Clientes
        W["Web<br/>HTML · Bootstrap · Vue.js"]
        M["Android<br/>Kotlin · Compose"]
        D["Desktop<br/>C# · WinForms"]
    end
    subgraph API["API — Spring Boot (backend/)"]
        F["Filtros<br/>CorrelationId · JWT · CORS"] --> C[Controllers] --> S[Services] --> R[Repositories JPA]
    end
    W -->|REST + Bearer JWT| F
    M -->|REST| F
    R --> DB[(SQL Server)]
    D -.->|acesso direto, legado| DB
    FW[Flyway] -->|migrations| DB
```

- **backend/** — API REST e front web servido pelo próprio Spring Boot (`src/main/resources/static`).
- **mobile/** — protótipo Android; ainda não integrado à API autenticada.
- **desktop/** — cliente Windows legado que acessa o banco diretamente (ver [SECURITY.md](../../SECURITY.md)).

## Autenticação

```mermaid
sequenceDiagram
    participant U as Navegador
    participant A as API
    participant DB as SQL Server
    U->>A: POST /api/login {username, password}
    A->>DB: busca usuário ativo
    A->>A: valida BCrypt (MD5 legado → regrava em BCrypt)
    A-->>U: JWT (HS256, 24 h)
    U->>A: GET /api/... Authorization: Bearer <token>
    A->>A: JwtAuthFilter valida assinatura e expiração
    A-->>U: 200 OK · X-Correlation-Id
    Note over U,A: Sem token ou token inválido → 401 e retorno ao login
```

## Modelo de dados

```mermaid
erDiagram
    tflow_usuario ||--o{ tflow_chamado : "abre"
    tflow_usuario ||--o{ tflow_chamado : "atende"
    tflow_chamado ||--o{ tflow_chamado_detalhe : "atendimentos"
    tflow_chamado_status ||--o{ tflow_chamado : "status"
    tflow_chamado_prioridade ||--o{ tflow_chamado : "prioridade"
    tflow_chamado_categoria ||--o{ tflow_chamado : "categoria"
    tflow_grupo ||--o{ tflow_grupo_usuario : ""
    tflow_usuario ||--o{ tflow_grupo_usuario : ""
    tflow_grupo ||--o{ tflow_grupo_acesso : ""
    tflow_acesso ||--o{ tflow_grupo_acesso : ""
    tflow_chamado {
        int idchamado PK
        varchar assunto
        text descricao
        int idstatus FK
        int idprioridade FK
        int idcategoria FK
        int idusuario FK
        int idusuariosuporte FK
        datetimeoffset datacriacao
        datetimeoffset dataatualizacao
    }
    tflow_usuario {
        int idusuario PK
        varchar usuario UK
        varchar nome
        varchar email
        varchar senha "BCrypt"
    }
    tflow_historico {
        int idhistorico PK
        varchar idhistorico_tipo
        text descricao
        datetimeoffset datacriacao
    }
```

O schema é versionado em [`backend/src/main/resources/db/migration`](../../backend/src/main/resources/db/migration).

## Decisões técnicas

| Decisão | Motivo |
|---|---|
| JWT stateless no header `Authorization` | API sem sessão de servidor; CSRF não se aplica porque nenhum cookie autentica |
| BCrypt com migração no login | Elimina MD5 sem exigir troca de senha de todos os usuários |
| Flyway com baseline na V1 | Bancos já existentes (criados pelo antigo `ddl-auto`) migram sem recriar dados |
| Erros técnicos em RFC 7807; regras de negócio em `ServiceResponse` | Padroniza falhas sem quebrar o contrato que o front já usa |
| Logs JSON (ECS) com `correlationId` | Rastreio de uma requisição de ponta a ponta e ingestão direta em ferramentas de log |
| Cálculo de relatórios em classe pura (`TicketReportCalculator`) | Regra testável sem banco nem framework |
