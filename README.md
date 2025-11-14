# 🧠 Mental Health Platform API

## 📋 Descrição do Projeto

API RESTful desenvolvida em Java com Spring Boot para a **Plataforma de Monitoramento de Bem-Estar e Saúde Mental**, conforme especificado na atividade Global Solution 2025 da FIAP.

A plataforma permite o gerenciamento completo de pacientes, profissionais de saúde, registros diários de humor e bem-estar, e agendamento de consultas, promovendo o acompanhamento contínuo da saúde mental.

---

## ✅ Tarefas Concluídas

### Backend
- [x] Estrutura base do projeto Spring Boot
- [x] Configuração do Maven (pom.xml) com todas as dependências
- [x] Enums para domínios restritos (StatusConsulta, NivelHumor, NivelAnsiedade, TipoProfissional)
- [x] Entidades JPA com relacionamentos corretos
- [x] Repositories com Spring Data JPA e consultas customizadas
- [x] DTOs (Request e Response) com Bean Validation
- [x] Mappers para conversão entre Entity e DTO
- [x] Services com regras de negócio e transações
- [x] Controllers RESTful completos (CRUD para todas entidades)
- [x] Exceções customizadas e tratamento global com @RestControllerAdvice
- [x] Configuração CORS para aceitar requisições do frontend
- [x] Configuração de banco H2 e MySQL
- [x] Seeds/Dados iniciais para testes
- [x] Documentação README.md e CHANGELOG.md

### Frontend
- [x] Interface web completa com **Thymeleaf**
- [x] Dashboard com estatísticas e dados em tempo real
- [x] Páginas de listagem de Profissionais de Saúde
- [x] Páginas de listagem de Pacientes
- [x] Páginas de listagem de Registros Diários
- [x] Páginas de listagem de Consultas
- [x] Design minimalista com cor primária #87dff9 (cyan)
- [x] Design responsivo com CSS moderno
- [x] **Server-side rendering** com Spring MVC + Thymeleaf
- [x] Fragments Thymeleaf reutilizáveis (navbar, head)
- [x] Formatação de dados com Thymeleaf (datas, enums, badges)
- [x] Formulários de criação (CREATE) para todas as 4 entidades
- [ ] Formulários de edição (UPDATE) - **EM DESENVOLVIMENTO**
- [ ] Confirmação e exclusão (DELETE) - **EM DESENVOLVIMENTO**
- [ ] Sistema de mensagens flash (feedback ao usuário) - **PENDENTE**

---

## 🚀 Tecnologias Utilizadas

### Backend
- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **Spring Validation**
- **Spring Web MVC**
- **Thymeleaf** (Template Engine)
- **H2 Database** (desenvolvimento)
- **MySQL** (produção - opcional)
- **Lombok**
- **Maven**

### Frontend
- **Thymeleaf** (Server-side rendering)
- **HTML5**
- **CSS3** (Design System customizado)
- **Spring MVC** para navegação e controle de views
- **Responsive Design** (Mobile-first)

---

## 📐 Arquitetura

O projeto segue as melhores práticas de **Domain Driven Design Lite** e arquitetura em camadas:

### Estrutura Backend
```
src/main/java/br/com/fiap/mentalhealthplatform/
├── application/
│   ├── dto/                 # Data Transfer Objects
│   ├── exception/           # Exceções customizadas
│   ├── mapper/              # Conversores Entity <-> DTO
│   └── service/             # Regras de negócio
├── domain/
│   ├── entity/              # Entidades JPA
│   ├── enums/               # Enumerações
│   └── repository/          # Interfaces Repository
├── infrastructure/
│   └── config/              # Configurações, CORS e Seeds
└── presentation/
    └── controller/          # Controllers REST
```

### Estrutura Frontend
```
src/main/resources/
├── templates/               # Templates Thymeleaf
│   ├── index.html           # Dashboard principal (SSR)
│   ├── profissionais.html   # Listagem de Profissionais
│   ├── pacientes.html       # Listagem de Pacientes
│   ├── registros.html       # Listagem de Registros
│   ├── consultas.html       # Listagem de Consultas
│   └── fragments/           # Componentes reutilizáveis
│       ├── head.html        # Meta tags, CSS imports
│       └── navbar.html      # Menu de navegação
└── static/
    └── css/
        └── styles.css       # Design System completo
```

---

## 🗂️ Modelo de Dados

### Entidades Principais

#### 1. **ProfissionalSaude**
- Representa psicólogos, psiquiatras, terapeutas e outros profissionais
- Relacionamento: `OneToMany` com Consulta

#### 2. **Paciente**
- Usuários da plataforma que buscam apoio
- Relacionamento: `OneToMany` com RegistroDiario e Consulta

#### 3. **RegistroDiario**
- Registro diário de humor, ansiedade, sono e bem-estar
- Relacionamento: `ManyToOne` com Paciente

#### 4. **Consulta**
- Agendamento de consultas entre paciente e profissional
- Relacionamento: `ManyToOne` com Paciente e ProfissionalSaude

### Enums

- **StatusConsulta**: AGENDADA, CONFIRMADA, EM_ANDAMENTO, CONCLUIDA, CANCELADA, REAGENDADA
- **NivelHumor**: MUITO_BOM, BOM, NEUTRO, RUIM, MUITO_RUIM
- **NivelAnsiedade**: NENHUMA, LEVE, MODERADA, GRAVE, MUITO_GRAVE
- **TipoProfissional**: PSICOLOGO, PSIQUIATRA, TERAPEUTA, COACH, ASSISTENTE_SOCIAL

---

## ⚙️ Como Executar

### Pré-requisitos

- **Java 17+** (JDK)
- Maven 3.8+

> ⚠️ **Importante**: Se encontrar o erro `No compiler is provided in this environment`, consulte o arquivo `SOLUCAO_JDK.md` para instruções detalhadas sobre como configurar o JDK corretamente.

### Passos

1. **Clone o repositório**
```bash
git clone <url-do-repositorio>
cd gs-java
```

2. **Compile o projeto**
```bash
mvn clean install
```

3. **Execute a aplicação**
```bash
mvn spring-boot:run
```

4. **Acesse a aplicação**
- **Frontend com Thymeleaf**: http://localhost:8080 (Interface renderizada no servidor)
- **API REST**: http://localhost:8080/api (Endpoints JSON para integração)
- **H2 Console**: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:mentalhealthdb`
  - User: `sa`
  - Password: (vazio)

### 🎨 Navegação no Frontend

Ao acessar `http://localhost:8080`, você terá acesso às seguintes páginas renderizadas com **Thymeleaf**:

#### Páginas Principais
- **Dashboard** (`/`): Visão geral com estatísticas, consultas recentes e pacientes recentes
- **Profissionais** (`/profissionais`): Listagem completa de profissionais de saúde com badges de tipo e status
- **Pacientes** (`/pacientes`): Listagem completa de pacientes cadastrados
- **Registros** (`/registros`): Acompanhamento de registros diários com indicadores visuais
- **Consultas** (`/consultas`): Visualização de todas as consultas agendadas

#### Formulários de Criação (CREATE)
- **Novo Paciente** (`/pacientes/novo`): Formulário completo de cadastro
- **Novo Profissional** (`/profissionais/novo`): Formulário de cadastro de profissional
- **Nova Consulta** (`/consultas/nova`): Agendamento de consulta com dropdowns dinâmicos
- **Novo Registro** (`/registros/novo`): Registro diário de bem-estar

#### 🚧 Em Desenvolvimento
- **Editar** (`/{entidade}/editar/{id}`): Formulários de edição - **PENDENTE**
- **Excluir** (`/{entidade}/excluir/{id}`): Confirmação de exclusão - **PENDENTE**

Cada página possui:
- ✅ **Server-side rendering** com Thymeleaf
- ✅ Listagem completa com dados em tempo real
- ✅ Navegação entre páginas com navbar responsiva
- ✅ Design minimalista com cor cyan (#87dff9)
- ✅ Badges coloridos para status e tipos
- ✅ Formatação automática de datas, horas e enums
- ✅ Design responsivo para mobile
- ✅ Reutilização de componentes via fragments
- ✅ Formulários de criação funcionais
- ⏳ CRUD completo (faltam UPDATE e DELETE)

### Executar com MySQL (Opcional)

1. Configure o MySQL localmente
2. Execute com o profile MySQL:
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=mysql
```

---

## 📡 Endpoints da API

### Base URL
```
http://localhost:8080/api
```

### **Profissionais de Saúde**

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/profissionais-saude` | Lista todos profissionais |
| GET | `/profissionais-saude/ativos` | Lista profissionais ativos |
| GET | `/profissionais-saude/{id}` | Busca por ID |
| GET | `/profissionais-saude/tipo/{tipo}` | Busca por tipo |
| POST | `/profissionais-saude` | Cria novo profissional |
| PUT | `/profissionais-saude/{id}` | Atualiza profissional |
| DELETE | `/profissionais-saude/{id}` | Remove profissional |
| PATCH | `/profissionais-saude/{id}/inativar` | Inativa profissional |

### **Pacientes**

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/pacientes` | Lista todos pacientes |
| GET | `/pacientes/ativos` | Lista pacientes ativos |
| GET | `/pacientes/{id}` | Busca por ID |
| GET | `/pacientes/buscar?nome={nome}` | Busca por nome |
| POST | `/pacientes` | Cria novo paciente |
| PUT | `/pacientes/{id}` | Atualiza paciente |
| DELETE | `/pacientes/{id}` | Remove paciente |
| PATCH | `/pacientes/{id}/inativar` | Inativa paciente |

### **Registros Diários**

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/registros-diarios` | Lista todos registros |
| GET | `/registros-diarios/{id}` | Busca por ID |
| GET | `/registros-diarios/paciente/{pacienteId}` | Registros de um paciente |
| GET | `/registros-diarios/paciente/{pacienteId}/recentes?dias=30` | Registros recentes |
| POST | `/registros-diarios` | Cria novo registro |
| PUT | `/registros-diarios/{id}` | Atualiza registro |
| DELETE | `/registros-diarios/{id}` | Remove registro |

### **Consultas**

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/consultas` | Lista todas consultas |
| GET | `/consultas/{id}` | Busca por ID |
| GET | `/consultas/paciente/{pacienteId}` | Consultas de um paciente |
| GET | `/consultas/profissional/{profissionalId}` | Consultas de um profissional |
| GET | `/consultas/status/{status}` | Consultas por status |
| GET | `/consultas/profissional/{profissionalId}/proximas` | Próximas consultas |
| POST | `/consultas` | Cria nova consulta |
| PUT | `/consultas/{id}` | Atualiza consulta |
| DELETE | `/consultas/{id}` | Remove consulta |
| PATCH | `/consultas/{id}/status?status={status}` | Atualiza status |

---

## 📝 Exemplos de Requisições

### Criar Paciente

**POST** `/api/pacientes`

```json
{
  "nome": "João Silva",
  "email": "joao.silva@email.com",
  "telefone": "(11) 98765-4321",
  "cpf": "123.456.789-01",
  "dataNascimento": "1990-05-15",
  "sexo": "Masculino",
  "observacoes": "Paciente busca apoio para ansiedade"
}
```

### Criar Registro Diário

**POST** `/api/registros-diarios`

```json
{
  "pacienteId": 1,
  "dataRegistro": "2025-11-13",
  "nivelHumor": "BOM",
  "nivelAnsiedade": "LEVE",
  "horasSono": 7,
  "praticouExercicio": true,
  "qualidadeDia": 8,
  "anotacoes": "Dia produtivo no trabalho"
}
```

### Criar Consulta

**POST** `/api/consultas`

```json
{
  "pacienteId": 1,
  "profissionalId": 1,
  "dataHoraConsulta": "2025-11-20T14:00:00",
  "motivoConsulta": "Acompanhamento mensal",
  "duracaoMinutos": 50,
  "localConsulta": "Consultório Av. Paulista, 1000",
  "consultaOnline": false
}
```

### Atualizar Status da Consulta

**PATCH** `/api/consultas/1/status?status=CONFIRMADA`

---

## 🛡️ Validações

Todos os endpoints utilizam **Bean Validation** para garantir a integridade dos dados:

- Campos obrigatórios validados com `@NotBlank`, `@NotNull`
- Email validado com `@Email`
- CPF validado com regex pattern
- Datas validadas com `@Past` e `@Future`
- Tamanhos de string validados com `@Size`
- Valores numéricos validados com `@Min` e `@Max`

---

## ⚠️ Tratamento de Exceções

A API possui tratamento centralizado de exceções com `@RestControllerAdvice`:

### Exceções Customizadas

- **ResourceNotFoundException** (404): Recurso não encontrado
- **DuplicateResourceException** (409): Recurso duplicado (email, CPF, CRP)
- **BusinessException** (400): Regra de negócio violada
- **MethodArgumentNotValidException** (400): Erro de validação

### Formato de Resposta de Erro

```json
{
  "timestamp": "2025-11-13T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Paciente não encontrado com id: '99'",
  "path": "/api/pacientes/99"
}
```

Para erros de validação:

```json
{
  "timestamp": "2025-11-13T10:30:00",
  "status": 400,
  "error": "Validation Failed",
  "message": "Erro de validação nos campos",
  "path": "/api/pacientes",
  "fieldErrors": [
    {
      "field": "email",
      "message": "Email inválido"
    },
    {
      "field": "cpf",
      "message": "CPF é obrigatório"
    }
  ]
}
```

---

## 📊 Dados Iniciais (Seeds)

A aplicação carrega automaticamente dados de exemplo:

- **3 Profissionais de Saúde** (Psicóloga, Psiquiatra, Terapeuta)
- **3 Pacientes**
- **3 Registros Diários**
- **4 Consultas** (agendadas, confirmadas e concluídas)

---

## 🧪 Testes

### Testar com cURL

```bash
# Listar todos os pacientes
curl -X GET http://localhost:8080/api/pacientes

# Buscar paciente por ID
curl -X GET http://localhost:8080/api/pacientes/1

# Criar novo paciente
curl -X POST http://localhost:8080/api/pacientes \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Maria Santos",
    "email": "maria@email.com",
    "telefone": "(11) 91234-5678",
    "cpf": "98765432100",
    "dataNascimento": "1992-03-20",
    "sexo": "Feminino"
  }'
```

### Testar com Postman/Insomnia

Importe a collection disponível em `/docs/postman_collection.json` (se disponível).

---

## 📦 Estrutura de Pacotes

```
br.com.fiap.mentalhealthplatform
│
├── application
│   ├── dto
│   │   ├── ConsultaRequestDTO.java
│   │   ├── ConsultaResponseDTO.java
│   │   ├── PacienteRequestDTO.java
│   │   ├── PacienteResponseDTO.java
│   │   ├── ProfissionalSaudeRequestDTO.java
│   │   ├── ProfissionalSaudeResponseDTO.java
│   │   ├── RegistroDiarioRequestDTO.java
│   │   └── RegistroDiarioResponseDTO.java
│   │
│   ├── exception
│   │   ├── BusinessException.java
│   │   ├── DuplicateResourceException.java
│   │   ├── ErrorResponse.java
│   │   ├── GlobalExceptionHandler.java
│   │   └── ResourceNotFoundException.java
│   │
│   ├── mapper
│   │   ├── ConsultaMapper.java
│   │   ├── PacienteMapper.java
│   │   ├── ProfissionalSaudeMapper.java
│   │   └── RegistroDiarioMapper.java
│   │
│   └── service
│       ├── ConsultaService.java
│       ├── PacienteService.java
│       ├── ProfissionalSaudeService.java
│       └── RegistroDiarioService.java
│
├── domain
│   ├── entity
│   │   ├── Consulta.java
│   │   ├── Paciente.java
│   │   ├── ProfissionalSaude.java
│   │   └── RegistroDiario.java
│   │
│   ├── enums
│   │   ├── NivelAnsiedade.java
│   │   ├── NivelHumor.java
│   │   ├── StatusConsulta.java
│   │   └── TipoProfissional.java
│   │
│   └── repository
│       ├── ConsultaRepository.java
│       ├── PacienteRepository.java
│       ├── ProfissionalSaudeRepository.java
│       └── RegistroDiarioRepository.java
│
├── infrastructure
│   └── config
│       └── DataSeeder.java
│
├── presentation
│   └── controller
│       ├── ConsultaController.java
│       ├── PacienteController.java
│       ├── ProfissionalSaudeController.java
│       └── RegistroDiarioController.java
│
└── MentalHealthPlatformApplication.java
```

---

## 🎯 Diferenciais Implementados

### Backend
- ✅ **CRUD completo** para 4 entidades (Paciente, ProfissionalSaude, RegistroDiario, Consulta)
- ✅ **Relacionamentos bidirecionais** entre entidades
- ✅ **Consultas customizadas** no Repository
- ✅ **Endpoints adicionais**: busca por nome, tipo, status, registros recentes
- ✅ **Soft delete** com flag `ativo`
- ✅ **Auditoria** com campos `dataCadastro` e `dataAtualizacao`
- ✅ **Validações complexas**: CPF, email único, data única de registro
- ✅ **Seeds robustos** com dados realistas
- ✅ **Configuração CORS** para integração frontend-backend

### Frontend (BÔNUS - Server-Side Rendering)
- ✅ **Interface web completa** com Thymeleaf + Spring MVC
- ✅ **Dashboard interativo** com estatísticas em tempo real
- ✅ **Operações CREATE e READ** implementadas para todas as 4 entidades
- ⏳ **Operações UPDATE e DELETE** em desenvolvimento (ver TAREFAS_PENDENTES.md)
- ✅ **Design System minimalista** com cor primária cyan (#87dff9)
- ✅ **Responsive Design** compatível com mobile, tablet e desktop
- ✅ **Server-Side Rendering** com Thymeleaf (eliminados ~450 linhas de JavaScript)
- ✅ **Validações no frontend**: HTML5 + Bean Validation no backend
- ✅ **Formulários de criação** para todas as entidades com dropdowns dinâmicos
- ✅ **Integração nativa** com Spring MVC e Services

---

## 📊 Métricas do Projeto

### Backend
- **Arquivos Java**: 41 classes
- **Endpoints REST**: 33 endpoints funcionais
- **Entidades**: 4 (Paciente, ProfissionalSaude, RegistroDiario, Consulta)
- **DTOs**: 16 (8 Request, 8 Response)
- **Enums**: 4 tipos
- **Linhas de Código**: ~3.500 LOC

### Frontend (Thymeleaf)
- **Páginas HTML**: 9 (Dashboard + 4 Listagens + 4 Formulários de Criação)
- **Arquivo CSS**: 1 Design System completo (~670 linhas)
- **Templates Thymeleaf**: Server-side rendering
- **Componentes UI**: Cards, Tabelas, Forms, Badges, Alerts, Navbar, Fragments
- **JavaScript**: Eliminado (~450 linhas removidas na migração para SSR)
- **Total**: ~1.800 LOC frontend
- **Pendente**: 8 páginas adicionais (4 edição + 4 exclusão) - ver TAREFAS_PENDENTES.md

### Total Geral
- **~5.300 linhas de código** (backend + frontend)
- **50 arquivos** de código-fonte
- **Cobertura**: 60% das funcionalidades completas (CREATE e READ), 40% em desenvolvimento (UPDATE e DELETE)

---

## 👥 Equipe

- Nome do Aluno 1 - RM XXXXX
- Nome do Aluno 2 - RM XXXXX
- Nome do Aluno 3 - RM XXXXX

> ⚠️ **Atenção**: Atualize os nomes e RMs dos integrantes do grupo antes da entrega!

---

## 📄 Licença

Este projeto foi desenvolvido para fins acadêmicos como parte da Global Solution 2025 da FIAP.

---

## 📞 Suporte

Para dúvidas ou problemas, entre em contato através dos issues do repositório.
