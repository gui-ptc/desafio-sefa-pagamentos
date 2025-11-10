# Pagamentos API (Desafio SEFA)

**Stack:** Java 17 · Spring Boot 3.5.7 · Spring Web · H2 · Spring Data JPA

## Como rodar

    ./mvnw spring-boot:run

- Saúde: http://localhost:8080/ping  
- Console H2: http://localhost:8080/h2  
  - **JDBC URL:** `jdbc:h2:mem:pagamentosdb`
  - User: sa · Password: (vazio)

## Escopo do desafio
- Receber pagamento (status inicial **PENDENTE_PROCESSAMENTO**).
- Atualizar status via rota (aceita PROCESSADO_SUCESSO ou PROCESSADO_FALHA).
- Listar pagamentos com filtros (código do débito, CPF/CNPJ, status).
- Exclusão lógica (somente quando **PENDENTE_PROCESSAMENTO**).

## Decisões de implementação
- API REST com JSON em todas as entradas/saídas.
- Banco H2 em memória para facilitar execução local.
- Controllers enxutos, regras de negócio no service (POST/PUT/DELETE), persistência e filtros via repository/Specification.
- Validações em DTO's e entidade (Bean Validation/JPA).
- Sem restrição de transições de status além da validação do enum.

## Endpoints (WIP)
- `GET /ping` – saúde.
- `POST /api/pagamentos` – criar pagamento.
- `PUT /api/pagamentos/{id}/status` – atualizar status.
- `GET /api/pagamentos` – listar + filtros.
- `DELETE /api/pagamentos/{id}` – exclusão lógica.