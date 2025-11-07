# Pagamentos API (Desafio SEFA)

**Stack:** Java 17 · Spring Boot 3.5.7 · H2 · JPA

## Como rodar

    ./mvnw spring-boot:run

- Saúde: http://localhost:8080/ping  
- Console H2: http://localhost:8080/h2  
  - **JDBC URL:** `jdbc:h2:mem:pagamentosdb`

## Escopo do desafio
- Receber pagamento (status inicial **PENDENTE_PROCESSAMENTO**).
- Atualizar status (PENDENTE → SUCESSO/FALHA; FALHA → PENDENTE; SUCESSO = imutável).
- Listar pagamentos com filtros (código do débito, CPF/CNPJ, status).
- Exclusão lógica (somente quando **PENDENTE_PROCESSAMENTO**).

## Decisões de implementação
- Projeto mínimo, sem bibliotecas além do necessário.
- Banco em memória (**H2**) para facilitar execução local.
- JSON em todas as entradas/saídas.
- Validações no DTO; regras de domínio no service.

## Endpoints (WIP)
- `GET /ping` – saúde (**implementado**)
- `POST /api/pagamentos` – criar pagamento (**em desenvolvimento**)
- `PATCH /api/pagamentos/{id}/status` – atualizar status (**planejado**)
- `GET /api/pagamentos` – listar + filtros (**planejado**)
- `DELETE /api/pagamentos/{id}` – exclusão lógica (**planejado**)
