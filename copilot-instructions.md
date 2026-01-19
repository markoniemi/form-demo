# GitHub Copilot Instructions for the Form Application

This document provides guidance for GitHub Copilot to assist in the development of this project, ensuring that generated code aligns with the architecture and technologies outlined in the `TechnicalSpecification.md`.

## General Principles

*   **Monolithic Architecture**: Remember that this is a monolithic application. The Spring Boot backend serves both the REST API and the React frontend. The frontend is packaged as a WebJar and included as a dependency in the backend.
*   **Follow Existing Patterns**: When generating code, please analyze the existing files to match the coding style, naming conventions, and architectural patterns.
*   **Adhere to Specifications**: All generated code should align with the `TechnicalSpecification.md` and `RequirementsSpecification.md`.

## Coding Conventions

### Java (Backend)
*   **Style Guide**: Follow the [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html).
*   **Naming**: Use clear and descriptive names for classes, methods, and variables (e.g., `FormService`, `getFormById`).
*   **Comments**: Add Javadoc comments to all public methods and classes to explain their purpose, parameters, and return values.
*   **Immutability**: Prefer immutability where possible, especially for DTOs and configuration properties.

### TypeScript/React (Frontend)
*   **Style Guide**: Follow the [Airbnb JavaScript Style Guide](https://github.com/airbnb/javascript) and the official TypeScript guidelines.
*   **Naming**:
    *   Use `PascalCase` for React components (e.g., `FormBuilderPage`).
    *   Use `camelCase` for variables, functions, and hooks (e.g., `useForm`, `fetchFormData`).
*   **Components**: Always use functional components with hooks. Avoid class components.
*   **Variables**: Use `const` by default; use `let` only when a variable needs to be reassigned.

## Backend (Java & Spring Boot)

The backend is a standard Spring Boot application.

*   **Stack**:
    *   **Language**: Java
    *   **Framework**: Spring Boot (using Spring Web, Spring Data JPA, Spring Security)
    *   **Database**: PostgreSQL
    *   **Authentication**: OAuth 2.0 with Spring Security and a development-time Spring Boot Authorization Server.

*   **Instructions**:
    *   **Entities**: When creating JPA entities, place them in the `com.example.formdemo.model` package. They should correspond to the tables defined in the `TechnicalSpecification.md` (`users`, `forms`, `form_fields`, `submissions`, `submission_responses`). Use standard JPA annotations (`@Entity`, `@Table`, `@Id`, `@ManyToOne`, etc.).
    *   **Repositories**: For each entity, create a corresponding Spring Data JPA repository interface in the `com.example.formdemo.repository` package. It should extend `JpaRepository`.
    *   **Services**: Business logic should be placed in service classes in the `com.example.formdemo.service` package. Use `@Service` and inject repositories.
    *   **Controllers**: Create REST controllers in the `com.example.formdemo.controller` package to expose API endpoints as defined in the technical specification. Use `@RestController` and `@RequestMapping("/api/...")`. Secure endpoints using Spring Security annotations (`@PreAuthorize`).
    *   **Testing**:
        *   For unit tests, use JUnit 5 and Mockito.
        *   For integration tests involving the database, use Spring Boot's test slices (`@DataJpaTest`) with an in-memory H2 database.
        *   For integration tests involving security, use `@SpringBootTest` and Testcontainers to provide an OAuth 2.0 authorization server.

## Frontend (TypeScript & React)

The frontend is a single-page application built with React and TypeScript.

*   **Stack**:
    *   **Language**: TypeScript
    *   **Framework**: React
    *   **Styling**: Bootstrap (via `react-bootstrap` components)
    *   **Build**: The project is located in the `frontend` module and is built by the `frontend-maven-plugin`.

*   **Instructions**:
    *   **Directory Structure**: Follow a standard React application structure within `frontend/src/`:
        *   `components/`: Reusable UI components.
            *   `common/`: Generic components like buttons, inputs, etc.
            *   `layout/`: Components that define the page structure (e.g., Header, Footer).
        *   `pages/`: Top-level components that correspond to a page/route (e.g., `HomePage`, `FormBuilderPage`).
        *   `hooks/`: Custom React hooks.
        *   `services/`: Functions for interacting with the backend API.
        *   `types/`: Shared TypeScript types and interfaces.
        *   `styles/`: Global styles or theme files.
    *   **Components**: Create functional components using React Hooks. Use `.tsx` for files containing JSX.
    *   **Styling**: Use `react-bootstrap` components (e.g., `<Button>`, `<Form>`, `<Container>`) for the UI. Avoid writing custom CSS where a Bootstrap component or utility class can be used.
    *   **State Management**: For simple component state, use `useState`. For more complex global state, consider using `useContext` with `useReducer`.
    *   **API Interaction**: Place API interaction logic in the `services/` directory. Use a library like `axios` for making requests.
    *   **Authentication**: The frontend should handle the OAuth 2.0 redirect flow. When making authenticated API calls, ensure the request includes the necessary credentials.
