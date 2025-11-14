# Changelog

Todas as mudanças notáveis neste projeto serão documentadas neste arquivo.

O formato é baseado em [Keep a Changelog](https://keepachangelog.com/pt-BR/1.0.0/),
e este projeto adere ao [Versionamento Semântico](https://semver.org/lang/pt-BR/).

## [2.1.0] - 2025-11-13 (EM DESENVOLVIMENTO)

### 🎨 Redesign Minimalista + Formulários de Criação

#### ✨ Adicionado
- **Design minimalista** implementado
  - Cor primária alterada para #87dff9 (cyan/turquoise)
  - Cor de fundo: #f5f5f5 (cinza claro)
  - Emojis removidos do navbar e botões
  - Sombras suavizadas para visual flat
  - Tipografia refinada (tamanhos, espaçamentos, letter-spacing)
  - Navbar com indicador cyan ao invés de emoji
  
- **CSS completamente redesenhado** (~670 linhas)
  - Variáveis CSS atualizadas com nova paleta
  - Botões flat com hover suave (sem transform)
  - Cards com bordas laterais coloridas (não mais gradientes)
  - Tabelas com design limpo e bordas sutis
  - Badges arredondados e discretos
  - Modais e alertas com visual refinado
  
- **4 Páginas de Formulários de Criação**
  - `novo-paciente.html`: Cadastro completo de paciente
  - `novo-profissional.html`: Cadastro de profissional de saúde
  - `nova-consulta.html`: Agendamento com dropdowns dinâmicos
  - `novo-registro.html`: Registro diário de bem-estar
  
- **8 Novos Métodos no ViewController**
  - 4 `@GetMapping` para exibir formulários
  - 4 `@PostMapping` para processar criação
  - Redirecionamento após cadastro (redirect pattern)
  - Injeção de listas auxiliares para dropdowns
  
- **Navegação aprimorada**
  - Botões "Novo" em todas as páginas de listagem
  - Links atualizados no dashboard para novas rotas

#### 🔧 Alterado
- Navbar: emoji 🧠 removido, design mais limpo
- Todas as variáveis de cor no CSS atualizadas
- Stat cards: design flat com borda esquerda colorida
- Botões: uniformizados com btn-primary (cyan)

#### 🚧 Pendente
- [ ] Formulários de edição (UPDATE) para as 4 entidades
- [ ] Páginas de confirmação e exclusão (DELETE)
- [ ] Sistema de mensagens flash (feedback visual)
- [ ] Validações e erros no frontend
- [ ] Breadcrumbs nas páginas de formulário
- [ ] Ícones nos botões de ação (opcional)

Ver `TAREFAS_PENDENTES.md` para planejamento completo.

---

## [2.0.0] - 2025-11-13

### 🔄 Migração para Thymeleaf

**Breaking Change**: Frontend completamente reformulado usando Server-Side Rendering com Thymeleaf.

#### ✨ Adicionado
- Dependência `spring-boot-starter-thymeleaf`
- **ViewController** (`presentation/controller/ViewController.java`)
  - Métodos Spring MVC para servir páginas: `index()`, `profissionais()`, `pacientes()`, `registros()`, `consultas()`
  - Carregamento de dados dos Services e adição ao Model
  - Atributo `activePage` para highlight da navbar
- **Templates Thymeleaf** em `src/main/resources/templates/`
  - `index.html`: Dashboard com estatísticas e listagens
  - `profissionais.html`: Listagem de profissionais com badges
  - `pacientes.html`: Listagem de pacientes
  - `registros.html`: Visualização de registros diários
  - `consultas.html`: Visualização de consultas
- **Fragments Thymeleaf** em `src/main/resources/templates/fragments/`
  - `head.html`: Meta tags e imports CSS reutilizáveis
  - `navbar.html`: Menu de navegação com highlight de página ativa
- Documentação completa da migração em `THYMELEAF.md`

#### 🔧 Alterado
- Frontend agora usa **Server-Side Rendering** ao invés de client-side JavaScript
- Páginas movidas de `/static/` para `/templates/`
- Renderização de dados no servidor antes de enviar HTML
- Rotas mudaram de `/pages/*.html` para `/*` (Spring MVC)

#### ❌ Removido
- Arquivo `api.js` (~450 linhas de JavaScript) - não mais necessário
- Chamadas AJAX com Fetch API
- Manipulação do DOM com JavaScript
- Diretório `/static/pages/` 
- Diretório `/static/js/`
- Fragment `scripts.html` (não mais necessário)

#### 📈 Melhorias
- Performance inicial mais rápida (sem necessidade de carregar/executar JS)
- SEO melhorado (conteúdo renderizado no servidor)
- Manutenção simplificada (~450 linhas de código removidas)
- Integração nativa com Spring MVC
- Type-safety nos templates

---

## [1.0.0] - 2025-11-13

### 🎉 Lançamento Inicial

Primeira versão completa da API Mental Health Platform para Global Solution 2025 FIAP.

### ✨ Adicionado

#### Estrutura do Projeto
- Configuração inicial do projeto Spring Boot 3.2.0 com Java 17
- Estrutura de pacotes seguindo Domain Driven Design Lite
- Configuração do Maven com todas as dependências necessárias
- Arquivos de configuração para H2 Database e MySQL

#### Domain Layer (Camada de Domínio)
- **Entidades JPA criadas:**
  - `ProfissionalSaude`: Gerenciamento de profissionais de saúde mental
  - `Paciente`: Cadastro e acompanhamento de pacientes
  - `RegistroDiario`: Monitoramento diário de humor e bem-estar
  - `Consulta`: Agendamento e gestão de consultas

- **Enumerações implementadas:**
  - `StatusConsulta`: Estados do ciclo de vida de uma consulta
  - `NivelHumor`: Escala de 5 níveis para humor diário
  - `NivelAnsiedade`: Escala de 5 níveis para ansiedade
  - `TipoProfissional`: Categorização de profissionais de saúde

- **Relacionamentos JPA:**
  - OneToMany entre ProfissionalSaude e Consulta
  - OneToMany entre Paciente e RegistroDiario
  - OneToMany entre Paciente e Consulta
  - ManyToOne inversos configurados

#### Repository Layer (Camada de Repositório)
- Repositories com Spring Data JPA para todas as entidades
- **Consultas customizadas implementadas:**
  - Busca de profissionais por tipo e especialidade
  - Busca de pacientes por nome (case-insensitive)
  - Consulta de registros diários por período
  - Listagem de consultas por status e período
  - Próximas consultas de profissionais

#### Application Layer (Camada de Aplicação)
- **DTOs (Data Transfer Objects):**
  - Request DTOs para criação/atualização
  - Response DTOs para retorno de dados
  - Separação clara entre entrada e saída

- **Validações (Bean Validation):**
  - Campos obrigatórios com @NotBlank e @NotNull
  - Validação de email com @Email
  - Validação de CPF com regex pattern
  - Validação de datas com @Past e @Future
  - Limites de tamanho com @Size
  - Validações numéricas com @Min e @Max

- **Mappers:**
  - Conversão entre Entity e DTO
  - Métodos para create, update e toResponse
  - Lógica de transformação centralizada

- **Services:**
  - Regras de negócio implementadas
  - Validação de duplicidade (email, CPF, CRP)
  - Validação de registro diário único por data
  - Transações com @Transactional
  - Soft delete com flag ativo

- **Exceções Customizadas:**
  - `ResourceNotFoundException`: Para recursos não encontrados (404)
  - `DuplicateResourceException`: Para recursos duplicados (409)
  - `BusinessException`: Para violações de regras de negócio (400)
  - `GlobalExceptionHandler`: Tratamento centralizado com @RestControllerAdvice

#### Presentation Layer (Camada de Apresentação)
- **Controllers RESTful completos:**
  - `ProfissionalSaudeController`: 8 endpoints
  - `PacienteController`: 8 endpoints
  - `RegistroDiarioController`: 7 endpoints
  - `ConsultaController`: 10 endpoints

- **Operações CRUD:**
  - GET all: Listar todos os recursos
  - GET by ID: Buscar por identificador
  - POST: Criar novo recurso
  - PUT: Atualizar recurso existente
  - DELETE: Remover recurso
  - PATCH: Operações parciais (inativar, mudar status)

- **Endpoints adicionais:**
  - Filtros por status, tipo, nome
  - Busca de registros recentes
  - Listagem de recursos ativos
  - Próximas consultas de profissionais

#### Infrastructure Layer (Camada de Infraestrutura)
- **Configuração de Banco de Dados:**
  - H2 in-memory para desenvolvimento
  - Suporte a MySQL para produção
  - DDL automático (create-drop)
  - Logs SQL habilitados

- **Data Seeder:**
  - 3 profissionais de saúde (Psicóloga, Psiquiatra, Terapeuta)
  - 3 pacientes com dados completos
  - 3 registros diários de exemplo
  - 4 consultas em diferentes estados
  - Execução automática na inicialização

#### Documentação
- README.md completo com:
  - Descrição do projeto
  - Instruções de execução
  - Documentação de endpoints
  - Exemplos de requisições
  - Estrutura de pacotes
  - Lista de tarefas concluídas

- CHANGELOG.md seguindo padrão Keep a Changelog

### 🔧 Configurações
- Porta padrão: 8080
- H2 Console habilitado em /h2-console
- Logs em nível DEBUG para desenvolvimento
- Format SQL habilitado para debug
- DevTools configurado

### 🎨 Boas Práticas Aplicadas
- Domain Driven Design Lite
- Arquitetura em camadas clara
- Separação de responsabilidades
- Injeção de dependências com Constructor Injection
- Uso de Lombok para reduzir boilerplate
- Validações em múltiplas camadas
- Tratamento de erros centralizado
- Auditoria com timestamps
- Soft delete preservando histórico
- Nomenclatura clara e em português

### 📊 Métricas do Projeto
- 4 entidades principais
- 4 enumerações
- 4 repositories
- 8 DTOs request
- 8 DTOs response
- 4 mappers
- 4 services
- 4 controllers
- 3 exceções customizadas
- 1 handler global
- 33 endpoints REST
- Dados seed para 13 registros

### 🔐 Segurança
- Validação de entrada em todos os endpoints
- Sanitização de CPF removendo caracteres especiais
- Validação de unicidade para email, CPF e CRP
- Prevenção de duplicação de registros diários

### 🚀 Performance
- Lazy loading nos relacionamentos JPA
- Queries otimizadas com @Query
- Transações read-only onde apropriado
- Índices automáticos em campos unique

---

## Notas de Desenvolvimento

Este changelog documenta a primeira versão completa da API desenvolvida para a Global Solution 2025 da FIAP. O projeto implementa todos os requisitos obrigatórios e diversos diferenciais, seguindo as melhores práticas de desenvolvimento Spring Boot.

Para sugestões de melhorias ou reporte de bugs, utilize as issues do repositório.
