# Desafio Backend - FarmTech

API RESTful desenvolvida para o gerenciamento de produtos agrícolas, implementando operações de CRUD completas conforme os requisitos do teste técnico.

## Tecnologias

- Java 17 (LTS)
- Spring Boot 3.3.5
- Spring Data JPA
- H2 Database (Banco em memória)
- Jakarta Bean Validation
- SpringDoc OpenAPI (Swagger UI)

## Instruções de Execução

### Pré-requisitos
- Java Development Kit (JDK) 17 instalado.
- Porta 8080 disponível.

### Como rodar a aplicação
1. Clone o repositório para sua máquina local.
2. No diretório raiz do projeto, abra o terminal e execute o comando:
   
   ./mvnw spring-boot:run

   (No Windows, utilize: mvnw.cmd spring-boot:run)

3. A aplicação será iniciada na porta 8080.

## Documentação da API

A documentação interativa dos endpoints pode ser acessada através do Swagger UI:
http://localhost:8080/swagger-ui/index.html

## Funcionalidades

O sistema disponibiliza os seguintes endpoints para gerenciamento de produtos:

- POST /products: Criação de um novo produto. Inclui validação para impedir nomes duplicados e garantir a integridade dos dados.
- GET /products: Listagem de todos os produtos cadastrados.
- GET /products/{id}: Busca detalhada de um produto específico.
- PUT /products/{id}: Atualização de dados de um produto existente, mantendo as regras de validação.

## Decisões de Projeto

- Arquitetura: O projeto segue a arquitetura em camadas (Controller, Service, Repository) para garantir a separação de responsabilidades.
- Banco de Dados: Utilizado H2 Database em memória para facilitar a execução e testes, conforme padrão para desafios técnicos.
- Tratamento de Erros: Implementado tratamento global de exceções para retornar respostas HTTP adequadas (400 Bad Request, 409 Conflict) com mensagens claras em JSON.
