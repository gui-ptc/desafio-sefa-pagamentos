# Pagamentos API (Desafio SEFA)

**Stack:** Java 17 · Spring Boot 3.5.7 · H2 · JPA

## Como rodar

```bash
./mvnw spring-boot:run
```
Saúde: http://localhost:8080/ping

Console H2: http://localhost:8080/h2

JDBC URL: jdbc:h2:mem:pagamentosdb

Escopo (resumo do desafio)
Criar pagamento (status inicial PENDENTE_PROCESSAMENTO)

Atualizar status com regras de transição

Listar com filtros (código do débito, CPF/CNPJ, status)

Exclusão lógica (somente quando pendente)

Decisões de implementação
Projeto mínimo, sem bibliotecas além do necessário.

Banco em memória (H2) para facilitar execução local.

Validações básicas no DTO; regras de domínio no service.

Logs/SQL visíveis apenas em dev.

Endpoints (WIP)
GET /ping – saúde

POST /api/pagamentos – cria pagamento
