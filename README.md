<div align="center">

<img src="docs/assets/logo.png" width="120" alt="TechFlow">

# TechFlow Service Desk

**Portal de gestão de chamados de suporte técnico — API segura, interface responsiva e relatórios de desempenho.**

[![build](https://github.com/Yashatk/techflow-service-desk/actions/workflows/build.yml/badge.svg)](https://github.com/Yashatk/techflow-service-desk/actions/workflows/build.yml)
[![license](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
![Java](https://img.shields.io/badge/Java-17-007396?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5-6DB33F?logo=springboot&logoColor=white)
![SQL Server](https://img.shields.io/badge/SQL_Server-2019-CC2927?logo=microsoftsqlserver&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Compose-2496ED?logo=docker&logoColor=white)

[Começando](#começando) · [Documentação](docs/README.md) · [Arquitetura](docs/architecture/README.md) · [Changelog](CHANGELOG.md)

<img src="docs/screenshots/02-chamados.png" width="860" alt="Lista de chamados do TechFlow">

</div>

## Sumário

- [Sobre](#sobre)
- [Destaques técnicos](#destaques-técnicos)
- [Funcionalidades](#funcionalidades)
- [Telas](#telas)
- [Stack](#stack)
- [Começando](#começando)
- [Estrutura do repositório](#estrutura-do-repositório)
- [Testes e qualidade](#testes-e-qualidade)
- [Roadmap](#roadmap)
- [Contribuindo](#contribuindo)
- [Licença e créditos](#licença-e-créditos)

## Sobre

O TechFlow centraliza a abertura, triagem e acompanhamento de chamados de suporte técnico. Usuários registram solicitações; analistas definem prioridade, atualizam status e registram atendimentos; administradores gerenciam usuários, grupos e permissões, com trilha de auditoria e relatórios de desempenho.

Nasceu em 2025 como projeto acadêmico da equipe **Tech-Flow** (Análise e Desenvolvimento de Sistemas — UNIP São José dos Campos), desenvolvido com Scrum em 8 sprints, em que atuei como **Scrum Master e desenvolvedor**. Esta é a versão consolidada que mantenho e evoluo: segurança, observabilidade, migrations e testes foram adicionados após a entrega acadêmica (ver [CHANGELOG](CHANGELOG.md)).

## Destaques técnicos

- **Segurança** — API stateless com JWT em todas as rotas, senhas em BCrypt com migração transparente de hashes MD5 legados, login via `POST`, CORS restrito e container sem root.
- **Dados** — schema versionado com Flyway; o Hibernate apenas valida. Bancos legados entram por *baseline* sem perder dados.
- **Observabilidade** — logs JSON (ECS) com `correlationId` por requisição, devolvido no header `X-Correlation-Id`; falhas técnicas em RFC 7807.
- **Qualidade** — testes unitários e de segurança (MockMvc), cobertura JaCoCo e build no GitHub Actions, Dependabot e análises com SonarQube, Semgrep e Trivy.
- **Entrega** — ambiente completo com um comando (`docker compose up`): SQL Server, migrations, API e dados de demonstração.

## Funcionalidades

| Módulo | O que oferece |
|---|---|
| Chamados | Abertura, edição, atendimentos, filtros por texto, categoria, prioridade, status e período |
| Fluxo | Aberto → Em Andamento → Resolvido / Cancelado, com prioridades Urgente, Alta, Média e Baixa |
| Relatórios | Indicadores do período, tempo médio até resolução e distribuição por status, prioridade e categoria |
| Administração | Usuários, categorias e grupos de acesso com permissões por funcionalidade |
| Auditoria | Histórico somente leitura de logins e alterações |
| Interface | Responsiva (desktop, tablet e celular), tema escuro com alternância para claro |
| Clientes | Protótipo Android (Kotlin + Compose) e cliente desktop Windows (C# + WinForms) |

## Telas

| Login | Relatórios |
|---|---|
| <img src="docs/screenshots/01-login.png" alt="Login"> | <img src="docs/screenshots/08-relatorios.png" alt="Relatórios"> |
| **Edição de chamado** | **Usuários e grupos de acesso** |
| <img src="docs/screenshots/03-editar-chamado.png" alt="Edição de chamado"> | <img src="docs/screenshots/04-usuarios.png" alt="Usuários"> |

<details>
<summary><strong>Tema claro e mobile</strong></summary>

<p align="center">
  <img src="docs/screenshots/05-tema-claro.png" width="640" alt="Tema claro"><br><br>
  <img src="docs/screenshots/06-mobile-chamados.png" width="240" alt="Chamados no celular">
  &nbsp;&nbsp;
  <img src="docs/screenshots/07-mobile-menu.png" width="240" alt="Menu no celular">
</p>

</details>

## Stack

| Camada | Tecnologias |
|---|---|
| Backend | Java 17, Spring Boot 3.5, Spring Security, Spring Data JPA / Hibernate, JJWT, Log4j2 |
| Dados | Microsoft SQL Server 2019, Flyway |
| Frontend web | HTML5, CSS3, Bootstrap 5, Vue.js |
| Mobile | Kotlin, Jetpack Compose, Retrofit |
| Desktop | C#, .NET Framework, WinForms |
| Qualidade | JUnit 5, Mockito, MockMvc, JaCoCo, SonarQube, Semgrep, Trivy |
| Infra e CI | Docker, Docker Compose, GitHub Actions, Dependabot |

## Começando

### Com Docker (recomendado)

Pré-requisito: Docker com Compose v2.

```bash
git clone https://github.com/Yashatk/techflow-service-desk.git
cd techflow-service-desk/backend
cp .env.example .env   # preencha DB_PASSWORD (senha forte) e JWT_SECRET
docker compose up --build
```

Acesse **http://localhost:8089** com o usuário de demonstração **`demo` / `demo123`**. O compose sobe o SQL Server, aplica as migrations, inicia a API e carrega dados fictícios ([`backend/db/demo-seed.sql`](backend/db/demo-seed.sql)).

### Sem Docker

Requer JDK 17+, Maven e um SQL Server acessível.

```bash
cd backend
cp .env.example .env   # ajuste DB_URL, DB_USERNAME, DB_PASSWORD e JWT_SECRET
set -a && . ./.env && set +a
mvn spring-boot:run
```

### Configuração

| Variável | Obrigatória | Descrição |
|---|---|---|
| `DB_URL` | não | JDBC do SQL Server (padrão: `localhost:1433/techflow`) |
| `DB_USERNAME` / `DB_PASSWORD` | sim | Credenciais do banco |
| `JWT_SECRET` | sim | Chave de assinatura do JWT, mínimo 32 caracteres |
| `ADMIN_DEFAULT_PASSWORD` | não | Se definida, cria o usuário `admin` no primeiro boot |
| `APP_TIMEZONE` | não | Fuso dos filtros de data (padrão: `America/Sao_Paulo`) |
| `CORS_ALLOWED_ORIGINS` | não | Origens liberadas na API, separadas por vírgula (padrão: `http://localhost:8089`) |

Nenhuma credencial é versionada: tudo vem de variáveis de ambiente (`.env` está no `.gitignore`).

## Estrutura do repositório

```
.
├── backend/                     # API Spring Boot + front web
│   ├── src/main/java/…/ws/
│   │   ├── config/              # segurança, JWT, CORS, correlationId, erros RFC 7807
│   │   ├── support/             # chamados e relatórios
│   │   └── sys/                 # usuários, grupos, acessos, histórico
│   ├── src/main/resources/
│   │   ├── static/              # páginas web
│   │   └── db/migration/        # migrations Flyway
│   ├── src/test/java/           # testes unitários e de segurança
│   ├── db/demo-seed.sql         # dados fictícios de demonstração
│   ├── Dockerfile
│   └── docker-compose.yml
├── mobile/                      # app Android (Kotlin + Compose)
├── desktop/                     # cliente Windows (C# + WinForms)
├── docs/                        # produto, arquitetura e telas
└── .github/                     # CI, Dependabot e templates
```

## Testes e qualidade

```bash
cd backend && mvn -B package     # compila, roda os testes e gera target/site/jacoco
```

| Camada testada | Cobertura |
|---|---|
| Login e migração de senha | `SysLoginServiceTest` |
| Segurança HTTP (401, token forjado, CORS, RFC 7807, correlationId) | `SecurityWebTest` |
| Regras de relatório | `TicketReportCalculatorTest` |
| Datas e fuso horário | `UtilsTest` |

O CI publica o resumo de cobertura em cada execução e o relatório completo como artefato.

## Roadmap

- [x] Segurança da API (JWT obrigatório, BCrypt, login `POST`, CORS)
- [x] Relatórios de desempenho (US09)
- [x] Flyway, logs estruturados, RFC 7807 e testes no CI
- [ ] Ampliar a cobertura dos serviços legados e adicionar testes de integração com Testcontainers
- [ ] Migrar o front para Vue 3 + Vite + TypeScript
- [ ] Integrar o app Android à API autenticada
- [ ] Cliente desktop consumindo a API em vez de acessar o banco
- [ ] Demo online e análise SonarQube no CI
- [ ] Triagem e classificação de chamados com IA (proposta original da equipe)

Backlog completo em [docs/product/backlog.md](docs/product/backlog.md).

## Contribuindo

Veja o [guia de contribuição](CONTRIBUTING.md). Vulnerabilidades devem ser reportadas conforme a [política de segurança](SECURITY.md).

## Licença e créditos

Distribuído sob a licença [MIT](LICENSE).

Produto concebido pela equipe acadêmica **Tech-Flow** — UNIP São José dos Campos:

| Nome | Papel | GitHub |
|---|---|---|
| Tainara Lais Viana Mota | Product Owner | [@TainaraViana](https://github.com/TainaraViana) |
| André Takashi Prado Takamura | Scrum Master / Desenvolvedor | [@Yashatk](https://github.com/Yashatk) |
| Fabrício Santos Cavalcante | Desenvolvedor | [@Kinnube](https://github.com/Kinnube) |
| Thiago Paulino da Silva | Desenvolvedor | [@thiagooo9](https://github.com/thiagooo9) |
