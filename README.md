# Checkpoint 5 - Parte 2: Spring Security MVC (Mercado Express)

## Integrantes do Grupo

* **Nome:** Arthur Bispo de Lima - **RM:** 557568
* **Nome:** João Paulo Moreira dos Santos - **RM:** 557808

---

## Links do Projeto

* **Link do Repositório GitHub:** `https://github.com/ArthurBispo00/cp5-part2-spring-security`
* **Link do Deploy:** `[COLE AQUI O LINK DO SEU DEPLOY NO RENDER]`
* **Plataforma de Deploy:** `Render.com`

---

## IDE Utilizada

O projeto foi desenvolvido utilizando a IDE **IntelliJ IDEA Community Edition**.

---

## Sobre o Projeto

Este projeto é uma aplicação web completa para o **Mercado Express**, um sistema de gerenciamento de produtos (CRUD) desenvolvido com Spring Boot. A aplicação implementa um sistema de segurança robusto com autenticação e autorização, atendendo a todos os requisitos do Checkpoint.

As principais funcionalidades incluem:
* Cadastro (Sign Up) e Login de usuários.
* Dois níveis de acesso: `ADMIN` e `USER`.
* Gerenciamento completo (CRUD) de Produtos, restrito a administradores.
* Visualização de produtos para usuários comuns.

---

## Tecnologias Utilizadas

* **Back-end:** Java 17, Spring Boot 3.5.6, Spring Security, Spring Data JPA
* **Front-end:** Thymeleaf, HTML5, Bootstrap 5
* **Banco de Dados:** PostgreSQL
* **Build:** Maven
* **Dependências Principais:** `Spring Web`, `Spring Security`, `Spring Data JPA`, `PostgreSQL Driver`, `Lombok`, `Thymeleaf`, `thymeleaf-extras-springsecurity6`

---

## Telas e Funcionalidades

### 1. Página Inicial (Landing Page)

A `index.html` é a página base pública da aplicação, servindo como portal de entrada para o login ou registro.

### 2. Tela de Login e Registro (Sign Up)

Foi implementada uma tela de login personalizada, substituindo a padrão do Spring Security. Caso o usuário não possua cadastro, ele pode se registrar (Sign Up), e seus dados são salvos no banco PostgreSQL com a senha criptografada (BCrypt).

### 3. Sistema de Segurança (2 Tipos de Usuários)

A aplicação implementa 2 níveis de acesso, conforme solicitado:

#### 3.1. Visão `ROLE_USER` (Usuário Comum)

* **Login de Teste:** `user@email.com` / **Senha:** `user123`
* **Permissões:** Este usuário pode apenas **visualizar** a lista de produtos. Os botões de "Novo Produto", "Editar" e "Deletar" são ocultados. O acesso às rotas de manipulação (ex: `/produtos/novo`) é bloqueado no back-end, resultando em um erro 403 (Acesso Negado).

#### 3.2. Visão `ROLE_ADMIN` (Administrador)

* **Login de Teste:** `admin@email.com` / **Senha:** `admin123`
* **Permissões:** Este usuário tem acesso total ao CRUD de produtos. Todos os botões de gerenciamento são visíveis e funcionais.

### 4. CRUD de Produtos (Visão ADMIN)

O administrador tem acesso ao formulário para criar e editar produtos, que são persistidos no banco de dados.

**Formulário de Produto (`/produtos/novo`):**

---

## Print do Spring Initializr

Abaixo, o print da configuração inicial do projeto com suas respectivas dependências.

![Print do Spring Initializr](https://raw.githubusercontent.com/ArthurBispo00/cp5-part2-spring-security/main/mercado-express/Tela%20Spring%20Initializr.png)