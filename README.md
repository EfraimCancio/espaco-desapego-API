# Espaço Desapego - REST API

<div align="center">

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=flat-square&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.2-6DB33F?style=flat-square&logo=spring-boot)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?style=flat-square&logo=apache-maven)
![MySQL](https://img.shields.io/badge/MySQL-Database-4479A1?style=flat-square&logo=mysql)
![JWT](https://img.shields.io/badge/JWT-Authentication-000000?style=flat-square)
![License](https://img.shields.io/badge/License-Open-green?style=flat-square)

**REST API para gerenciamento da loja de brechó "Espaço Desapego"**

[Documentação Swagger](#documentação-swagger) • [Instalação](#instalação) • [Endpoints](#endpoints-da-api) • [Autenticação](#autenticação--segurança)

</div>

---

## 📋 Sobre o Projeto

A **Espaço Desapego API** é uma REST API robusta desenvolvida para gerenciar a aplicação de administração da loja de brechó "Espaço Desapego". A API fornece endpoints para gerenciamento completo de:

- 👥 **Usuários** - Cadastro e autenticação
- 🛍️ **Produtos** - Gerenciamento do catálogo
- 📁 **Categorias** - Organização de produtos
- 🏷️ **Marcas** - Classificação por marca
- 📏 **Tamanhos** - Dimensões de roupas/itens
- 🌍 **Origem** - Procedência dos itens
- 🖼️ **Imagens** - Gerenciamento de fotos de produtos

A aplicação utiliza **Spring Boot 3.2.2**, **Java 17**, autenticação **JWT**, e banco de dados **MySQL** com migrations automáticas via **Flyway**.

---

## 🚀 Características Principais

✅ **Autenticação Segura** - Implementação JWT com tokens com expiração configurável  
✅ **Validação de Dados** - Validações robustas com Jakarta Validation  
✅ **Paginação** - Suporte a paginação em todos os endpoints de listagem  
✅ **Soft Delete** - Inativação lógica de registros (não remove do banco)  
✅ **Documentação Interativa** - Swagger UI integrado (OpenAPI 3.0)  
✅ **Migrations Automáticas** - Versionamento de banco com Flyway  
✅ **CORS Preparado** - Pronto para integração com frontend  
✅ **Tratamento de Erros** - Stack trace removido em respostas de erro (segurança)

---

## 📋 Pré-requisitos

Antes de começar, você precisará ter instalado:

- **Java JDK 17+** - [Download](https://www.oracle.com/java/technologies/downloads/#java17)
- **Maven 3.6+** - [Download](https://maven.apache.org/download.cgi)
- **MySQL 8.0+** - [Download](https://dev.mysql.com/downloads/mysql/)
- **Git** - [Download](https://git-scm.com/downloads)

---

## 🔧 Instalação e Configuração

### 1. Clone o Repositório

```bash
git clone https://github.com/EfraimCancio/espaco-desapego-API.git
cd espaco-desapego-API
```

### 2. Configure o Banco de Dados

Crie um banco de dados MySQL:

```sql
CREATE DATABASE espaco_desapego_brecho;
CREATE USER 'root'@'localhost' IDENTIFIED BY 'admin';
GRANT ALL PRIVILEGES ON espaco_desapego_brecho.* TO 'root'@'localhost';
FLUSH PRIVILEGES;
```

### 3. Configure as Variáveis de Ambiente

Crie ou edite o arquivo `src/main/resources/application.properties`:

```properties
# Configurações do Banco de Dados
spring.datasource.url=jdbc:mysql://localhost:3306/espaco_desapego_brecho
spring.datasource.username=root
spring.datasource.password=admin

# Configurações Flyway
spring.flyway.baseline-on-migrate=true

# Configurações JPA/Hibernate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Segurança - JWT Secret
api.security.token.secret=${JWT_SECRET:sua_chave_secreta_aqui}

# Tratamento de Erros
server.error.include-stacktrace=never
```

**Variáveis de Ambiente (Recomendado para Produção):**

```bash
export JWT_SECRET=sua_chave_secreta_super_segura
export SPRING_DATASOURCE_URL=jdbc:mysql://seu-host/espaco_desapego_brecho
export SPRING_DATASOURCE_USERNAME=seu_usuario
export SPRING_DATASOURCE_PASSWORD=sua_senha
```

### 4. Instale as Dependências

```bash
mvn clean install
```

### 5. Execute a Aplicação

```bash
# Via Maven
mvn spring-boot:run

# Ou via Java direto
java -jar target/api-0.0.1-SNAPSHOT.jar
```

A API estará disponível em: **`http://localhost:8080`**

---

## 📚 Endpoints da API

### 🔐 Autenticação

#### Fazer Login
```http
POST /login
Content-Type: application/json

{
  "login": "usuario",
  "password": "senha123"
}
```

**Resposta (200 OK):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

---

### 👥 Usuários

#### Listar Usuários (com paginação)
```http
GET /user?page=0&size=15&sort=id
Authorization: Bearer {token}
```

**Resposta (200 OK):**
```json
{
  "content": [
    {
      "id": 1,
      "login": "usuario@example.com",
      "name": "João Silva"
    }
  ],
  "pageable": {"pageNumber": 0, "pageSize": 15},
  "totalElements": 1,
  "last": true
}
```

#### Registrar Novo Usuário
```http
POST /user
Content-Type: application/json
Authorization: Bearer {token}

{
  "login": "novo_usuario",
  "password": "senha123",
  "name": "Novo Usuário"
}
```

**Resposta (201 Created):**
```json
{
  "id": 1,
  "login": "novo_usuario",
  "name": "Novo Usuário"
}
```

---

### 🛍️ Produtos

#### Listar Todos os Produtos
```http
GET /produtos?page=0&size=15&sort=id
Authorization: Bearer {token}
```

#### Listar Apenas Produtos Ativos
```http
GET /produtos/ativos?page=0&size=15
Authorization: Bearer {token}
```

#### Obter Detalhes de um Produto
```http
GET /produtos/{id}
Authorization: Bearer {token}
```

#### Registrar Novo Produto
```http
POST /produtos
Content-Type: application/json
Authorization: Bearer {token}

{
  "nome": "Camiseta",
  "descricao": "Camiseta de algodão",
  "preco": 29.90,
  "categoriaId": 1,
  "marcaId": 1,
  "tamanhoId": 1,
  "origemId": 1,
  "quantidade": 10
}
```

#### Atualizar Produto
```http
PUT /produtos
Content-Type: application/json
Authorization: Bearer {token}

{
  "id": 1,
  "nome": "Camiseta Atualizada",
  "preco": 39.90,
  "quantidade": 8
}
```

#### Deletar (Inativar) Produto
```http
DELETE /produtos/{id}
Authorization: Bearer {token}
```

---

### 📁 Categorias

#### Listar Categorias
```http
GET /categorias?page=0&size=15
Authorization: Bearer {token}
```

#### Listar Categorias Ativas
```http
GET /categorias/ativos
Authorization: Bearer {token}
```

#### Registrar Categoria
```http
POST /categorias
Content-Type: application/json
Authorization: Bearer {token}

{
  "nome": "Camisetas",
  "descricao": "Camisetas variadas"
}
```

#### Atualizar Categoria
```http
PUT /categorias
Content-Type: application/json
Authorization: Bearer {token}

{
  "id": 1,
  "nome": "Camisetas Premium"
}
```

#### Deletar (Inativar) Categoria
```http
DELETE /categorias/{id}
Authorization: Bearer {token}
```

---

### 🏷️ Marcas

#### Listar Marcas
```http
GET /marcas?page=0&size=15
Authorization: Bearer {token}
```

#### Listar Marcas Ativas
```http
GET /marcas/ativos
Authorization: Bearer {token}
```

#### Registrar Marca
```http
POST /marcas
Content-Type: application/json
Authorization: Bearer {token}

{
  "nome": "Nike",
  "descricao": "Marca de esportes"
}
```

#### Atualizar Marca
```http
PUT /marcas
Content-Type: application/json
Authorization: Bearer {token}

{
  "id": 1,
  "nome": "Nike Premium"
}
```

#### Deletar (Inativar) Marca
```http
DELETE /marcas/{id}
Authorization: Bearer {token}
```

---

### 📏 Tamanhos

#### Listar Tamanhos
```http
GET /tamanho?page=0&size=15
Authorization: Bearer {token}
```

#### Listar Tamanhos Ativos
```http
GET /tamanho/ativos
Authorization: Bearer {token}
```

#### Registrar Tamanho
```http
POST /tamanho
Content-Type: application/json
Authorization: Bearer {token}

{
  "nome": "P",
  "descricao": "Pequeno"
}
```

#### Atualizar Tamanho
```http
PUT /tamanho
Content-Type: application/json
Authorization: Bearer {token}

{
  "id": 1,
  "nome": "P",
  "descricao": "Pequeno/Extra Pequeno"
}
```

#### Deletar (Inativar) Tamanho
```http
DELETE /tamanho/{id}
Authorization: Bearer {token}
```

---

### 🌍 Origem

#### Listar Origens
```http
GET /origem?page=0&size=15
Authorization: Bearer {token}
```

#### Listar Origens Ativas
```http
GET /origem/ativos
Authorization: Bearer {token}
```

#### Registrar Origem
```http
POST /origem
Content-Type: application/json
Authorization: Bearer {token}

{
  "nome": "São Paulo",
  "descricao": "Produtos de São Paulo"
}
```

#### Atualizar Origem
```http
PUT /origem
Content-Type: application/json
Authorization: Bearer {token}

{
  "id": 1,
  "nome": "São Paulo - SP"
}
```

#### Deletar (Inativar) Origem
```http
DELETE /origem/{id}
Authorization: Bearer {token}
```

---

### 🖼️ Imagens

#### Upload de Imagem para Produto
```http
POST /imagem?produtoId=1
Content-Type: multipart/form-data

[arquivo de imagem]
```

#### Deletar Imagem
```http
DELETE /imagem?produtoId=1&imagemId=1
Authorization: Bearer {token}
```

---

## 🔐 Autenticação & Segurança

### Fluxo de Autenticação

```
1. Usuário faz login com credenciais
   ↓
2. API valida as credenciais com Spring Security
   ↓
3. Token JWT é gerado (válido por 4 horas)
   ↓
4. Cliente armazena o token
   ↓
5. Requisições subsequentes incluem o token no header
   ↓
6. SecurityFilter valida o token em cada requisição
```

### Headers de Autenticação

Todas as requisições (exceto `/login`) requerem o header:

```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

### Configurações de Segurança

- **Algoritmo**: HMAC256 (HS256)
- **Duração do Token**: 4 horas
- **Zona Horária**: São Paulo (GMT-3)
- **Criptografia de Senha**: BCrypt
- **CSRF Protection**: Desabilitada (API stateless)
- **Session Policy**: STATELESS (sem estado)

### Endpoints Públicos

Os seguintes endpoints não requerem autenticação:

- `POST /login` - Fazer login
- `GET /v3/api-docs/**` - Documentação OpenAPI
- `GET /swagger-ui/**` - Interface Swagger UI
- `GET /swagger-ui.html` - Página Swagger

---

## 📖 Documentação Swagger

A documentação interativa da API está disponível em:

**`http://localhost:8080/swagger-ui.html`**

Acesse via navegador e interaja com os endpoints em tempo real!

**URLs da Documentação OpenAPI:**
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`
- OpenAPI YAML: `http://localhost:8080/v3/api-docs.yaml`

---

## 🗂️ Estrutura do Projeto

```
espaco-desapego-API/
├── src/
│   ├── main/
│   │   ├── java/desapego/brecho/api/
│   │   │   ├── controller/           # Controladores REST (endpoints)
│   │   │   │   ├── AuthenticationController.java
│   │   │   │   ├── UserController.java
│   │   │   │   ├── ProductsController.java
│   │   │   │   ├── CategoryController.java
│   │   │   │   ├── BrandsController.java
│   │   │   │   ├── SizeController.java
│   │   │   │   ├── OriginController.java
│   │   │   │   └── ImageController.java
│   │   │   ├── domain/               # Entidades e DTOs
│   │   │   │   ├── user/
│   │   │   │   ├── product/
│   │   │   │   ├── category/
│   │   │   │   ├── brand/
│   │   │   │   ├── size/
│   │   │   │   ├── origin/
│   │   │   │   └── image/
│   │   │   └── infra/                # Configurações de infraestrutura
│   │   │       └── security/         # Segurança JWT
│   │   │           ├── TokenService.java
│   │   │           ├── SecurityFilter.java
│   │   │           └── SecurityConfigurations.java
│   │   └── resources/
│   │       ├── application.properties # Configurações
│   │       └── db/migration/         # Scripts Flyway
│   └── test/
│       └── java/                     # Testes (JUnit + Spring Test)
├── .mvn/                             # Maven Wrapper
├── mvnw                              # Maven Wrapper Script
├── mvnw.cmd                          # Maven Wrapper Script (Windows)
├── pom.xml                           # Dependências Maven
├── .gitignore
└── README.md                         # Este arquivo
```

---

## 🧪 Testes

### Executar Todos os Testes

```bash
mvn test
```

### Executar Testes de Uma Classe Específica

```bash
mvn test -Dtest=UserControllerTest
```

### Gerar Relatório de Cobertura

```bash
mvn test jacoco:report
```

---

## 📦 Dependências Principais

| Dependência | Versão | Propósito |
|-------------|--------|----------|
| `spring-boot-starter-web` | 3.2.2 | Framework web REST |
| `spring-boot-starter-data-jpa` | 3.2.2 | ORM e acesso a dados |
| `spring-boot-starter-security` | 3.2.2 | Autenticação e autorização |
| `spring-boot-starter-validation` | 3.2.2 | Validações com Jakarta Validation |
| `mysql-connector-j` | Latest | Driver MySQL |
| `flyway-core` | Latest | Migrations de banco |
| `java-jwt` | 4.4.0 | Geração e validação JWT |
| `springdoc-openapi-starter-webmvc-ui` | 2.4.0 | Swagger UI (OpenAPI 3.0) |
| `lombok` | Latest | Redução de boilerplate |
| `spring-boot-devtools` | 3.2.2 | Hot reload em desenvolvimento |

---

## 🚀 Deploy

### Deploy no Vercel

O projeto pode ser deployado no Vercel. Acesse: **https://espaco-desapego-api.vercel.app**

### Deploy Local

```bash
# Build
mvn clean package

# Executar JAR
java -jar target/api-0.0.1-SNAPSHOT.jar
```

---

## 📊 Exemplos de Uso

### Exemplo Completo: Criar e Listar Produtos

```bash
# 1. Fazer Login
curl -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{"login":"user","password":"pass"}'

# Resposta:
# {"token":"eyJhbGciOiJIUzI1NiIs..."}

# 2. Usar o token
TOKEN="eyJhbGciOiJIUzI1NiIs..."

# 3. Criar um produto
curl -X POST http://localhost:8080/produtos \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "nome":"Camiseta Premium",
    "descricao":"Camiseta de alta qualidade",
    "preco":49.90,
    "categoriaId":1,
    "marcaId":1,
    "tamanhoId":1,
    "origemId":1,
    "quantidade":20
  }'

# 4. Listar produtos
curl -X GET "http://localhost:8080/produtos?page=0&size=10" \
  -H "Authorization: Bearer $TOKEN"

# 5. Obter detalhes de um produto
curl -X GET http://localhost:8080/produtos/1 \
  -H "Authorization: Bearer $TOKEN"
```

---

## 🐛 Troubleshooting

### Erro: "Connection refused" ao conectar ao MySQL

**Solução:**
```bash
# Verifique se MySQL está rodando
mysql -u root -p

# Se não estiver rodando, inicie o serviço
# Windows
net start MySQL80

# Linux
sudo systemctl start mysql

# macOS
brew services start mysql
```

### Erro: "Access denied for user 'root'@'localhost'"

**Solução:**
Verifique as credenciais no `application.properties`:

```properties
spring.datasource.username=root
spring.datasource.password=admin  # Ajuste conforme sua senha
```

### Erro: "Token JWT inválido ou expirado"

**Solução:**
- Gere um novo token via `/login`
- Verifique se `JWT_SECRET` está configurado corretamente
- Certifique-se de que o token não expirou (válido por 4 horas)

### Porta 8080 já está em uso

**Solução:**
```bash
# Alterar porta no application.properties
server.port=8081

# Ou via linha de comando
java -Dserver.port=8081 -jar target/api-0.0.1-SNAPSHOT.jar
```

---

## 🔄 Versionamento

Atual: **v0.0.1-SNAPSHOT**

O projeto segue [Semantic Versioning](https://semver.org/).

---

## 🤝 Contribuindo

Contribuições são bem-vindas! Para contribuir:

1. Fork o repositório
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

---

## 📝 Licença

Este projeto é de código aberto e disponível sob a licença open source.

---

## 📞 Contato e Suporte

- **Desenvolvedor**: Efraim Cancio
- **GitHub**: [@EfraimCancio](https://github.com/EfraimCancio)
- **Issues**: [GitHub Issues](https://github.com/EfraimCancio/espaco-desapego-API/issues)

Para reportar bugs ou sugerir features, abra uma [issue no GitHub](https://github.com/EfraimCancio/espaco-desapego-API/issues/new).

---

## 🙏 Agradecimentos

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Security](https://spring.io/projects/spring-security)
- [JWT](https://jwt.io/)
- [Flyway](https://flywaydb.org/)
- [Swagger/OpenAPI](https://swagger.io/)
- [MySQL](https://www.mysql.com/)

---

<div align="center">

**Made with ❤️ by [Efraim Cancio](https://github.com/EfraimCancio)**

[⬆ Voltar ao Topo](#espaço-desapego---rest-api)

</div>
