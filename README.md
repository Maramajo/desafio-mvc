# 💼 Desafio MVC - Full Stack com Kafka, Docker e PostgreSQL

Este projeto foi desenvolvido como parte de um **teste técnico**. A proposta consiste em criar uma aplicação **full stack** integrada, com backend em Java (Spring Boot), frontend em Angular, persistência com PostgreSQL e comunicação assíncrona com Kafka.

---

## 🔧 Tecnologias Utilizadas

- ✅ **Backend**: Java 11, Spring Boot, Spring Data JPA, Apache Kafka
- ✅ **Frontend**: Angular 12, Node 14, TypeScript
- ✅ **Banco de Dados**: PostgreSQL 15
- ✅ **Mensageria**: Apache Kafka + Zookeeper
- ✅ **Containerização**: Docker + Docker Compose

---

## 🖥️ Como executar localmente

### 1️⃣ Instalar o Docker Desktop

- Acesse: [https://www.docker.com/products/docker-desktop](https://www.docker.com/products/docker-desktop)
- Baixe a versão para o seu sistema operacional (Windows, Mac ou Linux)
- Instale e **verifique se o Docker Engine está rodando** (`docker info` no terminal)

> 💡 No Windows, ative o WSL, se necessário.

---

### 2️⃣ Clonar o repositório

```bash
git clone https://github.com/Maramajo/desafio-mvc.git
cd desafio-mvc
```

### 3️⃣ Criar e subir os containers

```bash
docker-compose up --build
```

Isso irá construir e subir todos os serviços definidos no docker-compose.yml:

- Kafka (kafka:29092)
- Zookeeper (zookeeper:2181)
- PostgreSQL (postgres:5432)
- Backend (http://localhost:8081)
- Frontend (http://localhost)

---

## 🧭 Fluxo de Interação entre os Componentes

```
[Navegador do Usuário]
         │
         ▼
[ Front-end Angular ]
         │ HTTP
         ▼
[ Back-end Spring Boot ]
         │ REST + Kafka + RDB
         ▼
[ Apache Kafka ]
         │
         ▼
[ Outros consumidores ou logs futuros ]
```

---

## 📁 Estrutura do Projeto

```
desafio-mvc/
├── back-end/               # API Spring Boot/kafka/PostgreSQL
│   ├── Dockerfile
│   └── src/...
├── front-end/              # Aplicação Angular
│   ├── Dockerfile
│   └── src/...
├── db/
│   └── init.sql            # Script SQL para inicializar o banco
├── docker-compose.yml      # Orquestração de todos os serviços
└── README.md
```

---

## 🧪 Scripts úteis

### Compilar o backend (manual)

```bash
cd back-end
mvn clean package
docker build -t desafio-backend:latest .
```

### Build do frontend (manual)

```bash
cd front-end
npm install
ng build --configuration production
docker build -t desafio-frontend:latest .
```

---

## ✅ Funcionalidades

- 📌 Cadastro e consulta de créditos
- 📌 Envio de eventos de crédito para Kafka
- 📌 Interface web limpa e funcional
- 📌 Integração completa via Docker

---

## 📬 Contato

José Maria Costa Teixeira  
🔗 https://www.linkedin.com/in/jose-teixeira-672ba3b/  
📧 Email: [teixeira.j.mc@hotmail.com]

---

⚠️ Este projeto é voltado para demonstração técnica e pode ser ajustado conforme os requisitos da vaga.
