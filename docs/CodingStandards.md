# Coding Standards

## 1. General Principles
- Write clean, readable, and maintainable code.
- Follow established conventions for each technology stack.
- Use meaningful names for variables, functions, classes, and files.
- Document code with comments where necessary, especially for complex logic.
- Avoid code duplication; use reusable components and functions.

## 2. Frontend (React, TypeScript, Vite, Bootstrap)
- Use the latest stable version of Node.js and all frontend dependencies.
- Use TypeScript for type safety and clarity.
- Use React Hook Form for form state management and Zod for schema validation.
- Follow React best practices: use functional components, hooks, and prop types.
- Organize code into logical modules and folders (components, pages, services, utils).
- Use consistent indentation (2 spaces or Prettier default).
- Use ESLint and Prettier for code linting and formatting. Configure ESLint for static analysis and Prettier for automatic formatting on save and in CI/CD pipelines.
- Write unit and integration tests using Jest and React Testing Library.
- Use Bootstrap classes for consistent styling.
- Avoid inline styles; use CSS modules or styled-components if needed.

## 3. Backend (Spring Boot, Spring Data JPA, Spring Security, Lombok)
- Use the latest stable version of JDK and Spring Boot.
- Use Java features where appropriate.
- Follow standard Java naming conventions (CamelCase for classes, methods, variables).
- Organize code into packages by feature (controller, service, repository, model, config).
- Use Lombok annotations to reduce boilerplate code.
- Write RESTful controllers with clear endpoint definitions.
- Validate all input data and handle errors gracefully.
- Use Spring Data JPA for ORM and database access.
- Write unit and integration tests using JUnit and Testcontainers.
- Use H2 for testing and production.
- Use Google Java Format for code formatting. Configure automatic formatting in IDE and CI/CD pipelines.
- Use Spring Authorization Server for OAuth 2.0 authentication and authorization.
- Secure endpoints with OAuth 2.0 and role-based access control.

## 4. OAuth Module
- Use standard OAuth 2.0 flows and libraries.
- Keep authentication logic isolated from business logic.
- Document configuration and integration steps.
- Use Docker for containerization and Testcontainers for automated tests.

## 5. Database
- Use clear, consistent naming for tables and columns (snake_case).
- Write migration scripts for schema changes.
- Use H2 for tests and local development, and production.
- Secure sensitive data (e.g., passwords, tokens) using encryption and access controls.

## 6. Documentation
- Maintain up-to-date documentation for requirements, technical specs, API endpoints, and deployment steps.
- Use Markdown for documentation files.

## 7. Version Control & CI/CD
- Use Git for version control; follow Git Flow or trunk-based development.
- Write clear, descriptive commit messages.
- Use pull requests for code review.
- Automate builds, tests, and deployments with CI/CD pipelines.
- Use GitHub Actions as the CI/CD platform for all modules.

## 8. Testing
- Write unit, integration, and end-to-end tests for all modules.
- Use coverage tools to ensure sufficient test coverage.
- Run tests automatically in CI/CD pipelines.

## 9. Security
- Validate and sanitize all user input.
- Use HTTPS for all communication.
- Store secrets securely (environment variables, secret managers).
- Regularly update dependencies and monitor for vulnerabilities.
