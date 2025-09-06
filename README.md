# 🎬 API de Filmes Favoritos

Uma API REST desenvolvida em Spring Boot para catalogar e gerenciar filmes favoritos.

## 📋 Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Pré-requisitos](#pré-requisitos)
- [Como Executar](#como-executar)
- [Endpoints da API](#endpoints-da-api)
- [Exemplos de Uso](#exemplos-de-uso)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Contribuição](#contribuição)

## 🎯 Sobre o Projeto

Esta API permite que você:
- Cadastre seus filmes favoritos
- Consulte filmes por ID ou lista completa
- Atualize informações dos filmes
- Remova filmes da sua coleção
- Faça buscas por título, diretor, gênero ou nota
- Visualize seus filmes ordenados por nota

## 🛠 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.1.0**
- **Spring Data JPA** - Para persistência de dados
- **Spring Validation** - Para validação de dados
- **H2 Database** - Banco de dados em memória (desenvolvimento)
- **Maven** - Gerenciador de dependências

## ⚙️ Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:

- **Java 17 ou superior**
- **Maven 3.6 ou superior**
- **IDE de sua preferência** (IntelliJ IDEA, Eclipse, VS Code)
- **Postman** (para testar a API)

## 🚀 Como Executar

### 1. Clone o repositório
```bash
git clone <url-do-repositorio>
cd FilmesAPI
```

### 2. Execute o projeto
```bash
# Via Maven
mvn spring-boot:run

# Ou via IDE
# Execute a classe FilmesApiApplication.java
```

### 3. Acesse a aplicação
- **API Base URL:** `http://localhost:8080/api/filmes`
- **Console H2:** `http://localhost:8080/h2-console`
  - JDBC URL: `jdbc:h2:mem:filmes`
  - Username: `sa`
  - Password: *(deixe em branco)*

> **Nota:** As configurações estão no arquivo `application.yml` com `ddl-auto: update` para persistir dados entre reinicializações.

## 🔗 Endpoints da API

### Endpoints Principais (CRUD)

| Método | Endpoint | Descrição | Status Code |
|--------|----------|-----------|-------------|
| `GET` | `/api/filmes` | Lista todos os filmes | 200 |
| `GET` | `/api/filmes/{id}` | Busca filme por ID | 200, 404 |
| `POST` | `/api/filmes` | Cria novo filme | 201, 400 |
| `PUT` | `/api/filmes/{id}` | Atualiza filme completo | 200, 404, 400 |
| `PATCH` | `/api/filmes/{id}` | Atualiza filme parcial | 200, 404 |
| `DELETE` | `/api/filmes/{id}` | Remove filme | 204, 404 |

### Endpoints de Busca

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/filmes/buscar/titulo?titulo=nome` | Busca por título |
| `GET` | `/api/filmes/buscar/diretor?diretor=nome` | Busca por diretor |
| `GET` | `/api/filmes/buscar/genero?genero=nome` | Busca por gênero |
| `GET` | `/api/filmes/buscar/nota?nota=8` | Busca por nota mínima |
| `GET` | `/api/filmes/top` | Lista ordenado por nota |

## 📝 Exemplos de Uso

### Exemplo 1: Cadastrar um filme
```bash
POST http://localhost:8080/api/filmes
Content-Type: application/json

{
  "titulo": "O Poderoso Chefão",
  "diretor": "Francis Ford Coppola",
  "ano": 1972,
  "genero": "Drama",
  "nota": 10,
  "descricao": "A saga da família Corleone e seu império no mundo do crime organizado."
}
```

**Resposta (201 Created):**
```json
{
  "id": 1,
  "titulo": "O Poderoso Chefão",
  "diretor": "Francis Ford Coppola",
  "ano": 1972,
  "genero": "Drama",
  "nota": 10,
  "descricao": "A saga da família Corleone e seu império no mundo do crime organizado.",
  "dataAdicao": "2024-01-15T10:30:45"
}
```

### Exemplo 2: Listar todos os filmes
```bash
GET http://localhost:8080/api/filmes
```

**Resposta (200 OK):**
```json
[
  {
    "id": 1,
    "titulo": "O Poderoso Chefão",
    "diretor": "Francis Ford Coppola",
    "ano": 1972,
    "genero": "Drama",
    "nota": 10,
    "descricao": "A saga da família Corleone e seu império no mundo do crime organizado.",
    "dataAdicao": "2024-01-15T10:30:45"
  },
  {
    "id": 2,
    "titulo": "Pulp Fiction",
    "diretor": "Quentin Tarantino",
    "ano": 1994,
    "genero": "Crime",
    "nota": 9,
    "descricao": "Histórias entrelaçadas de crime e redenção em Los Angeles.",
    "dataAdicao": "2024-01-15T11:15:22"
  }
]
```

### Exemplo 3: Buscar filme por ID
```bash
GET http://localhost:8080/api/filmes/1
```

### Exemplo 4: Atualizar nota de um filme
```bash
PATCH http://localhost:8080/api/filmes/1
Content-Type: application/json

{
  "nota": 9
}
```

### Exemplo 5: Buscar filmes por gênero
```bash
GET http://localhost:8080/api/filmes/buscar/genero?genero=Drama
```

### Exemplo 6: Buscar filmes com nota mínima
```bash
GET http://localhost:8080/api/filmes/buscar/nota?nota=9
```

### Exemplo 7: Listar top filmes (ordenado por nota)
```bash
GET http://localhost:8080/api/filmes/top
```

### Exemplo 8: Remover um filme
```bash
DELETE http://localhost:8080/api/filmes/1
```

**Resposta:** `204 No Content`

## 🏗 Estrutura do Projeto

```
FilmesAPI/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── FilmesAPI/
│   │   │               ├── FilmesApiApplication.java      # Classe principal
│   │   │               ├── controller/
│   │   │               │   └── FilmeController.java       # Endpoints REST
│   │   │               ├── service/
│   │   │               │   └── FilmeService.java          # Lógica de negócio
│   │   │               ├── repository/
│   │   │               │   └── FilmeRepository.java       # Acesso aos dados
│   │   │               ├── model/
│   │   │               │   └── Filme.java                 # Entidade JPA
│   │   │               └── exception/
│   │   │                   └── GlobalExceptionHandler.java # Tratamento de erros
│   │   └── resources/
│   │       └── application.yml                            # Configurações
│   └── test/
├── target/                                                # Arquivos compilados
├── pom.xml                                               # Dependências Maven
└── README.md                                             # Documentação
```

### 📂 Descrição dos Componentes

**🎯 Controller (`FilmeController.java`)**
- Responsável pelos endpoints REST
- Mapeia URLs para métodos Java
- Gerencia status HTTP e validações

**🧠 Service (`FilmeService.java`)**
- Contém a lógica de negócio
- Intermediário entre Controller e Repository
- Valida regras específicas da aplicação

**💾 Repository (`FilmeRepository.java`)**
- Interface para acesso ao banco de dados
- Herda métodos do JpaRepository
- Contém queries personalizadas

**📊 Model (`Filme.java`)**
- Entidade JPA que representa a tabela no banco
- Define estrutura e validações dos dados
- Mapeamento objeto-relacional

**⚠️ Exception Handler (`GlobalExceptionHandler.java`)**
- Tratamento centralizado de exceções
- Padroniza respostas de erro
- Melhora experiência do usuário da API

**⚙️ Configuration (`application.yml`)**
- Configurações do Spring Boot
- Conexão com banco de dados H2
- Propriedades do servidor

### 🔄 Fluxo de Requisição

```
📱 Cliente (Postman)
    ↓
🌐 Controller (recebe requisição)
    ↓
🧠 Service (processa lógica)
    ↓
💾 Repository (acessa banco)
    ↓
🗃️ Banco H2 (armazena dados)
    ↓
📤 Resposta JSON
```


## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

## 📞 Contato

Desenvolvido com ☕ por Geiziane Silva - geiziane.souza16@gmail.com

Link do projeto: [https://github.com/geizii/filmes-api](https://github.com/geizii/filmes-api)


