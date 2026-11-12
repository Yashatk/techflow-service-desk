# Changelog

Todas as mudanças relevantes do projeto. Formato baseado em [Keep a Changelog](https://keepachangelog.com/pt-BR/1.1.0/), versionamento [SemVer](https://semver.org/lang/pt-BR/).

## [Unreleased]

### Alterado
- Estrutura do repositório por componente: `backend/`, `mobile/`, `desktop/` e `docs/`
- Documentação de produto e arquitetura consolidada em `docs/`
- Arquivos de padrão do repositório: licença MIT, guia de contribuição, política de segurança, templates de issue e PR

### Removido
- Ícones, folhas de estilo e scripts sem uso (cerca de 2.100 arquivos, 9 MB)

## [1.1.0] - 2026-11-12

### Segurança
- Todas as rotas da API exigem JWT no header `Authorization`
- Senhas com BCrypt, com migração automática de hashes MD5 no primeiro login
- Login via `POST` (credenciais fora da URL) e CORS restrito a origens configuradas

### Adicionado
- US09 — página de relatórios: indicadores do período e distribuição por status, prioridade e categoria
- Migrations versionadas com Flyway (o Hibernate apenas valida o schema)
- Logs JSON (ECS) com `correlationId` por requisição e erros técnicos em RFC 7807
- Testes de serviço, de segurança (MockMvc) e cobertura JaCoCo no CI; Dependabot

### Corrigido
- Filtros de período interpretados no fuso do usuário (`APP_TIMEZONE`)

## [1.0.0] - 2026-11-12

### Adicionado
- Versão consolidada do projeto acadêmico: API Spring Boot, front web, app Android e cliente desktop
- Interface responsiva com tema escuro "HUD" e alternância para claro
- Ambiente local completo com Docker Compose (SQL Server, API e dados de demonstração)
- Pipeline de build no GitHub Actions

### Segurança
- Credenciais fora do código (variáveis de ambiente) e container executando sem root

## [0.1.0] - 2025-10-01

- Entrega acadêmica da equipe Tech-Flow (UNIP – São José dos Campos), 8 sprints

[Unreleased]: https://github.com/Yashatk/techflow-service-desk/compare/v1.1.0...HEAD
[1.1.0]: https://github.com/Yashatk/techflow-service-desk/compare/v1.0.0...v1.1.0
[1.0.0]: https://github.com/Yashatk/techflow-service-desk/releases/tag/v1.0.0
