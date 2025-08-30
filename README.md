<div align="center">

<h1 align="center">💸 Rachadinha API 💸</h1>

<p align="center">
  <strong>Uma API RESTful para simplificar a divisão de despesas em grupo.</strong>
  <br />
  <br />
  <img src="https://img.shields.io/badge/Java-21+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 21+">
  <img src="https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="Spring Boot 3.x">
  <img src="https://img.shields.io/badge/PostgreSQL-16-316192?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL 16">
  <img src="https://img.shields.io/badge/Maven-4-red?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven">
</p>
</div>

---

## 🎯 Sobre o Projeto

Este projeto é uma API RESTful completa desenvolvida com as tecnologias mais modernas do ecossistema Java e Spring. O objetivo é resolver o problema universal de gerenciar e dividir despesas em grupo, seja entre amigos, em uma viagem ou nas contas de casa.

O núcleo da aplicação é a sua robusta camada de serviço, que implementa a lógica de negócio para calcular balanços financeiros de forma precisa, garantindo que ao final de um evento ou de um mês, todos saibam exatamente quem deve o quê para quem.

---
## 🛠️ Tecnologias e Conceitos Aplicados

Esta API foi construída utilizando um conjunto de ferramentas e conceitos de ponta, focando em boas práticas e código limpo:

* **Linguagem:** `Java 21+`
* **Framework Principal:** `Spring Boot 3`
* **Persistência de Dados:** `Spring Data JPA` / `Hibernate`
* **Banco de Dados:** `PostgreSQL`
* **Documentação da API:** `SpringDoc OpenAPI (Swagger)`
* **Gerenciador de Dependências:** `Maven`
* **Arquitetura:** `API RESTful` em `Arquitetura de 3 Camadas` (Controller, Service, Repository)
* **Princípios:** `Injeção de Dependências`, `Inversão de Controle (IoC)`, `SOLID`

---

## 🚀 Como Executar o Projeto

Para executar este projeto localmente, siga os passos abaixo:

1.  **Clone o repositório:**
    ```sh
    git clone [https://github.com/gustavorodriDEV/rachadinha-api.git](https://github.com/gustavorodriDEV/rachadinha-api.git)
    ```
    *(**Atenção:** Lembre-se de trocar a URL pela URL real do seu repositório)*

2.  **Configure o Banco de Dados:**
    * Crie um banco de dados no seu PostgreSQL.
    * Copie o arquivo `src/main/resources/application.properties.example` e renomeie a cópia para `application.properties`.
    * Altere as propriedades `spring.datasource.url`, `spring.datasource.username` e `spring.datasource.password` com as suas credenciais.

3.  **Execute a Aplicação:**
    * Abra o projeto na sua IDE (IntelliJ).
    * Execute a classe principal `RachadinhaApiApplication.java`.
    * *Ou, pelo terminal, na raiz do projeto, execute:*
        ```sh
        mvn spring-boot:run
        ```
4.  **Acesse a API:**
    * A aplicação estará rodando em `http://localhost:8080`.
    * A documentação interativa **Swagger UI** estará disponível em `http://localhost:8080/swagger-ui.html` para testar todos os endpoints.

---

## Endpoints da API

A URL base para todos os endpoints é `/api`.

### Recurso: Grupos (`/grupos`)

| Verbo HTTP | Endpoint               | Descrição                              |
| :--------- | :--------------------- | :------------------------------------- |
| `POST`     | `/grupos`              | Cria um novo grupo.                    |
| `GET`      | `/grupos`              | Lista todos os grupos existentes.      |
| `GET`      | `/grupos/{id}`         | Busca um grupo específico pelo seu ID. |
| `PUT`      | `/grupos/{id}`         | Atualiza um grupo existente.           |
| `DELETE`   | `/grupos/{id}`         | Deleta um grupo específico.            |

*(Mais recursos, como `/participantes` e `/despesas`..., serão adicionados futuramente)*

---
<div align="center">
  Feito com ❤️ por Gustavo Rodrigues.
</div>