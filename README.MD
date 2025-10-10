# Sistema de Gestão de Pedidos API

![Java](https://img.shields.io/badge/Java-17+-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0-green)
![License](https://img.shields.io/badge/License-MIT-yellow)

Uma API RESTful desenvolvida em Spring Boot...

Sistema de Gestão de Pedidos API

Uma API RESTful desenvolvida em Spring Boot para gerenciamento de clientes, produtos e pedidos.
🚀 Tecnologias Utilizadas

    Java 17+

    Spring Boot 3.x

    Spring Security + JWT

    Spring Data JPA

    MariaDB/MySQL

    Maven

📋 Pré-requisitos

    Java 17 ou superior

    Maven 3.6+

    MariaDB/MySQL

    Postman (para testes)

⚙️ Configuração do Projeto
1. Clone o repositório

git clone https://github.com/seu-usuario/sistema-pedidos-api.git
cd sistema-pedidos-api

2. Configure o banco de dados
-- Crie o banco de dados
CREATE DATABASE rest_spring_db;

-- Ou execute o dump SQL fornecido
mysql -u root -p rest_spring_db < dump.sql


2.1 Detalhamento do banco de dados rest_spring_db


``` 
classDiagram
    direction LR

    class Clientes {
        +Long id
        +String nome
        +String email
        +String telefone
        +LocalDateTime dataCadastro
        +Set~Pedido~ pedidos
    }

    class Pedido {
        +Long id
        +String endereco
        +LocalDateTime dataPedido
        +String status
        +Set~ItemPedido~ itens
        +Clientes cliente
        +BigDecimal getTotal()
        +adicionarItem(Produto, Integer)
        +removerItem(ItemPedido)
    }

    class ItemPedido {
        +Long id
        +Pedido pedido
        +Produto produto
        +Integer quantidade
        +BigDecimal precoUnitario
        +BigDecimal getSubtotal()
    }

    class Produto {
        +Long id
        +String nome
        +Double preco
        +String descricao
    }

    class User {
        +Long id
        +String name
        +String email
        +String password
        +UserRole role
        +getAuthorities() Collection~GrantedAuthority~
    }

    class UserRole {
        <<enumeration>>
        ADMIN
        USER
        SUPERVISOR
        INFRA
    }

    %% Relacionamentos
    Clientes "1" -- "*" Pedido : possui
    Pedido "1" -- "*" ItemPedido : contém
    ItemPedido "1" -- "1" Produto : referencia


``` 


2.2 Descrição das Entidades e Relacionamentos:
Clientes

    Entidade principal que representa os clientes do sistema

    Possui relacionamento One-to-Many com Pedido

    Atributos: dados pessoais e data de cadastro

Pedido

    Representa os pedidos realizados pelos clientes

    Relacionamento Many-to-One com Clientes

    Relacionamento One-to-Many com ItemPedido

    Métodos utilitários para cálculo de total e gestão de itens

ItemPedido

    Tabela de junção entre Pedido e Produto

    Armazena quantidade e preço unitário no momento da compra

    Possui métodos para cálculo de subtotal

Produto

    Catálogo de produtos disponíveis para venda

    Relacionamento One-to-Many com ItemPedido

User

    Sistema de autenticação e autorização

    Implementa UserDetails do Spring Security

    Suporte a diferentes roles de usuário

UserRole

    Enumeração que define os papéis de usuário no sistema

 

3. Configure a aplicação

3.1. Copie `application-example.properties` para `application.properties`
3.2. Preencha com suas configurações:

Edite o application.properties:
# Banco de dados
spring.datasource.url=jdbc:mysql://localhost:3306/rest_spring_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# JWT (altere para uma chave segura)
api.security.token.secret=sua-chave-secreta-jwt-aqui




4. Execute a aplicação

# Compilar e executar
mvn spring-boot:run

# Ou compile primeiro
mvn clean package
java -jar target/api-0.0.1-SNAPSHOT.jar

A aplicação estará disponível em: http://localhost:8080

🔐 Autenticação
Registrar Usuário

POST /auth/registrar

{
  "name": "usuario",
  "email": "usuario@email.com",
  "password": "senha123",
  "role": "USER"
}

Login

POST /auth/logar

{
  "email": "usuario@email.com",
  "password": "senha123"
}

Resposta:

{
  "token": "jwt-token-aqui",
  "type": "Bearer"
}

⚠️ Importante: Inclua o token no header das requisições:

Authorization: Bearer seu-jwt-token

📊 Endpoints Principais
👥 Clientes

Listar todos os clientes

GET /cliente/busca-todos

Buscar cliente por ID

GET /cliente/{id}

Criar cliente

POST /cliente/save

{
  "nome": "João Silva",
  "email": "joao@email.com",
  "telefone": "(11) 99999-1234"
}

📦 Produtos

Listar todos os produtos

GET /produto/busca-todos

Criar produto

POST /produto/save

{
  "nome": "Arroz Integral",
  "preco": 8.50,
  "descricao": "Arroz integral tipo 1, pacote 5kg"
}

🛒 Pedidos

Listar todos os pedidos

GET /pedido/busca-todos

Criar pedido

POST /pedido/save

{
  "endereco": "Rua das Flores, 123 - São Paulo/SP",
  "status": "PENDENTE",
  "clienteId": 1,
  "itens": [
    {
      "produtoId": 2,
      "quantidade": 2
    },
    {
      "produtoId": 1,
      "quantidade": 1
    }
  ]
}

Atualizar pedido

PATCH /pedido/{id}

{
  "endereco": "Novo Endereço, 456 - Rio de Janeiro/RJ",
  "status": "PROCESSANDO",
  "clienteId": 1,
  "itens": [
    {
      "produtoId": 3,
      "quantidade": 3
    }
  ]
}


🧪 Testes no Postman
1. Configuração da Collection

    Crie uma nova Collection

    Adicione variáveis de ambiente:

        baseUrl: http://localhost:8080

        token: (será preenchido automaticamente)

2. Fluxo de Teste

a) Autenticação:

// Script do teste de login em javascript
pm.test("Token recebido", function() {
    var jsonData = pm.response.json();
    pm.expect(jsonData.token).to.not.be.undefined;
    pm.collectionVariables.set("token", jsonData.token);
});

b) Headers automáticos:
Adicione este header em todas as requisições:

    Key: Authorization

    Value: Bearer {{token}}


3. Exemplos de Requisições

Sequência recomendada:

    POST /auth/registrar → Criar usuário

    POST /auth/logar → Obter token

    POST /cliente/save → Criar cliente

    POST /produto/save → Criar produtos

    POST /pedido/save → Criar pedido

    GET /pedido/busca-todos → Listar pedidos

🗃️ Estrutura do Banco

    clientes - Cadastro de clientes

    produto - Catálogo de produtos

    pedido - Pedidos realizados

    pedido_produtos - Itens dos pedidos (com quantidade)

    users - Usuários do sistema


🔧 Desenvolvimento

# Executar em modo desenvolvimento
mvn spring-boot:run

# Executar testes
mvn test

# Gerar jar executável
mvn clean package


📞 Suporte

Em caso de dúvidas ou problemas:

    Verifique se o banco de dados está rodando

    Confirme as credenciais no application.properties

    Verifique se a porta 8080 está disponível




