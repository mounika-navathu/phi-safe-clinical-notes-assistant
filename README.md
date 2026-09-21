# PHI-Safe Clinical Notes Assistant

A secure Spring Boot application designed to manage clinical notes while protecting sensitive patient information. The project uses PHI detection and de-identification techniques along with JWT authentication, role-based access control, audit logging, and an AI/RAG assistant.

---

## Key Features

* **PHI Detection & De-identification:** Detects sensitive information such as names, dates, phone numbers, and IDs and replaces them with safe placeholders.
* **JWT Authentication:** Secures APIs using JSON Web Tokens.
* **Role-Based Access Control:** Provides different access levels for Admin, Clinician, and Analyst users.
* **Audit Logging:** Maintains a tamper-evident hash-chained audit log of important actions.
* **AI/RAG Assistant:** Provides an AI assistant using de-identified clinical information.
* **REST APIs:** Provides APIs for authentication, clinical notes, auditing, and the assistant.
* **Docker Support:** Includes Docker configuration for easy deployment.

---

## Tech Stack

* **Backend:** Java 17, Spring Boot
* **Security:** Spring Security, JWT, BCrypt
* **AI:** RAG-based AI Assistant
* **API:** RESTful APIs
* **Testing:** JUnit 5, Mockito
* **Deployment:** Docker
* **Build Tool:** Maven

---

## Architecture

```text
Client
   |
   v
Spring Boot REST API
   |
   +---- JWT Authentication
   |
   +---- Clinical Notes
   |          |
   |          v
   |    PHI Detection
   |          |
   |          v
   |    De-identification
   |
   +---- Audit Logging
   |
   +---- AI / RAG Assistant
```

---

## Getting Started

### Prerequisites

* Java 17 or above
* Maven
* Docker (optional)
* Git

### Installation

1. Clone the repository:

```bash
git clone https://github.com/mounika-navathu/phi-safe-clinical-notes-assistant.git
cd phi-safe-clinical-notes-assistant
```

2. Build the project:

```bash
mvn clean install
```

3. Run the application:

```bash
mvn spring-boot:run
```

The application runs on:

```text
http://localhost:8080
```

---

## API Modules

* **Authentication:** Login and JWT token generation
* **Clinical Notes:** Create and access clinical notes
* **De-identification:** Retrieve notes with PHI removed
* **Audit:** Track and verify application activities
* **AI Assistant:** Ask questions using de-identified clinical information

---

## Security

The application uses:

* JWT-based authentication
* BCrypt password hashing
* Role-based authorization
* PHI detection and de-identification
* Hash-chained audit logging

The project uses **synthetic clinical data** for development and testing.

---

## Future Enhancements

* Database integration
* Advanced NLP-based PHI detection
* Production LLM integration
* Vector database for improved RAG retrieval
* Cloud deployment
* Enhanced monitoring and security
