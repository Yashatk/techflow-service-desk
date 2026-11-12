# Política de segurança

## Versões suportadas

| Versão | Suporte |
|---|---|
| 1.1.x | ✅ |
| < 1.1 | ❌ |

## Como reportar uma vulnerabilidade

Não abra uma issue pública. Use o recurso **Report a vulnerability** da aba *Security* do repositório (GitHub Private Vulnerability Reporting), descrevendo:

- o componente afetado (`backend`, `mobile` ou `desktop`) e a versão;
- os passos para reproduzir e o impacto esperado;
- se possível, uma sugestão de correção.

O retorno inicial ocorre em até 7 dias.

> O canal privado do GitHub fica disponível quando o repositório é público. Enquanto ele for privado, colaboradores devem relatar a falha diretamente ao mantenedor ([@Yashatk](https://github.com/Yashatk)), sem publicar detalhes em issues.

## Práticas adotadas

- Autenticação stateless com JWT (HMAC-SHA256) em todas as rotas da API
- Senhas com BCrypt; nenhuma credencial versionada (tudo via variáveis de ambiente)
- CORS restrito a origens explícitas; container sem root
- Dependências monitoradas pelo Dependabot e analisadas com SonarQube, Semgrep e Trivy

## Limitações conhecidas

- O cliente desktop (`desktop/`) ainda acessa o banco diretamente: use um usuário de banco com privilégios mínimos, nunca `sa`.
