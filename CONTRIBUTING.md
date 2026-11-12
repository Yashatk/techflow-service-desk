# Como contribuir

## Ambiente

Requisitos: Docker com Compose v2 (ou JDK 17+, Maven e SQL Server para rodar sem Docker).

```bash
cd backend
cp .env.example .env   # preencha DB_PASSWORD e JWT_SECRET
docker compose up --build
```

Acesse http://localhost:8089 com `demo` / `demo123`.

## Fluxo de trabalho

1. Crie uma branch a partir de `main`: `feat/<assunto>`, `fix/<assunto>` ou `docs/<assunto>`.
2. Faça commits pequenos seguindo [Conventional Commits](https://www.conventionalcommits.org/pt-br/v1.0.0/):
   `feat: …`, `fix: …`, `refactor: …`, `test: …`, `docs: …`, `chore: …`.
3. Garanta que o build e os testes passam: `cd backend && mvn -B package`.
4. Abra um pull request preenchendo o template.

## Padrões

- Código, nomes de banco, logs e commits em inglês; textos de interface em português (com i18n quando aplicável).
- Toda mudança de schema entra como nova migration Flyway em `backend/src/main/resources/db/migration` (`V<n>__descricao.sql`). Nunca altere uma migration já publicada.
- Lógica de negócio nova vem com teste unitário; endpoints novos exigem autenticação por padrão.
- Nunca versione segredos: use `.env` (ignorado pelo git) e documente variáveis novas em `.env.example`.
