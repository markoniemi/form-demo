# Technical Specification

## 1. Overview
This document describes the technical architecture and implementation details for the form creation and filling application. The system is designed as a multi-module application with clear separation of concerns between frontend, backend, and authentication modules.

## 2. Architecture
- **Frontend Module**: Built with React, Vite, TypeScript, Bootstrap. Packaged as a JAR file for integration with the backend.
- **Backend Module**: Built with Spring Boot, Spring Data JPA, Spring Security, Lombok. Exposes RESTful APIs for frontend communication and handles business logic and data persistence.
- **OAuth Module**: Handles authentication and authorization using OAuth 2.0. Packaged as a Docker container for use in testing with the backend via the Testcontainers library.
- **Database**: PostgreSQL is used for persistent data storage. H2 is used for testing.
- **Local Development**: LocalDevelopmentApplication provides an in-memory OAuth server and H2 database for local development.

## 3. Module Details
### 3.1 Frontend
- Frameworks/Libraries: React, Vite, TypeScript, Bootstrap
- Communication: Uses fetch API to interact with backend RESTful endpoints
- Authentication: Integrates with OAuth 2.0 flow for login/logout
- Packaging: Built and packaged as a JAR file for deployment with backend

### 3.2 Backend
- Frameworks/Libraries: Spring Boot, Spring Data JPA, Spring Security, Lombok
- API: Exposes RESTful endpoints for all business operations (form management, response submission, user management)
- Security: Uses Spring Security to enforce OAuth 2.0 authentication and authorization
- Data Access: Uses Spring Data JPA for ORM and PostgreSQL for storage in production, H2 for testing
- Packaging: Main application JAR includes frontend JAR
- LocalDevelopmentApplication: Used for local development with in-memory OAuth server and H2 database

### 3.3 OAuth Module
- Provides OAuth 2.0 authentication and authorization for both form creators and fillers
- Used as a Docker container during testing
- Integrated with backend using Testcontainers for automated tests
- In-memory OAuth server available for local development

### 3.4 Database
- PostgreSQL schema includes tables for users, forms, fields, responses, and OAuth tokens
- H2 is used as an in-memory database for automated tests and local development

## 4. Communication
- All communication between frontend and backend is via RESTful APIs (JSON over HTTP)
- Authentication tokens (OAuth 2.0) are sent in HTTP headers

## 5. Security
- OAuth 2.0 is used for authentication and authorization
- All sensitive data is encrypted in transit (HTTPS)
- Backend validates all input and enforces authorization rules

## 6. Testing
- Backend tests use Testcontainers to spin up the OAuth Docker container and PostgreSQL database
- H2 is used for backend tests and local development
- Frontend tests use Jest and React Testing Library

## 7. Deployment
- Backend and OAuth modules are containerized for deployment
- Frontend is packaged as a JAR and served by the backend
- Database is deployed as a managed PostgreSQL instance or container

## 8. Technologies Used
- Frontend: React, Vite, TypeScript, Bootstrap, fetch
- Backend: Spring Boot, Spring Data JPA, Spring Security, Lombok
- Database: PostgreSQL (production), H2 (testing, local development)
- OAuth: OAuth 2.0, Docker, Testcontainers

## 9. Packaging
- Frontend: JAR file
- Backend: JAR file (includes frontend)
- OAuth: Docker container

## 10. Diagrams
- (Add architecture and sequence diagrams as needed)
