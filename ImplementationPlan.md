# Implementation Plan for Form Application

This document outlines the development plan for the Form Application, breaking down the work into sprints. Each sprint is expected to last two weeks.

## Sprint 1: Project Setup & Backend Foundation

**Goal**: Establish the project structure and set up the core backend components.

*   **Tasks**:
    *   Initialize a new Spring Boot project with required dependencies (Spring Web, Spring Data JPA, PostgreSQL Driver, Spring Security).
    *   Set up the `frontend-maven-plugin` to integrate the frontend build process.
    *   Create a basic React with TypeScript project structure under `src/main/frontend`.
    *   Configure the PostgreSQL database connection.
    *   Define the JPA entities for `User`, `Form`, `FormField`, `Submission`, and `SubmissionResponse`.
    *   Create Spring Data JPA repositories for each entity.
    *   Set up basic project-level configuration (logging, properties).
    *   Create initial `.gitignore` file.

## Sprint 2: Authentication & Authorization

**Goal**: Implement a secure authentication system using OAuth 2.0.

*   **Tasks**:
    *   Set up an embedded Spring Boot Authorization Server for local development.
    *   Configure Spring Security to work with the authorization server.
    *   Implement the OAuth 2.0 login flow on the frontend.
    *   Create a simple "Login with..." button.
    *   Handle the callback from the authorization server and manage user sessions/tokens.
    *   Create a protected API endpoint and verify that only authenticated users can access it.
    *   Develop a basic user profile page on the frontend to display authenticated user information.

## Sprint 3: Core Form Functionality - Creation

**Goal**: Allow users to create and design forms.

*   **Tasks**:
    *   Implement the API endpoints for creating, retrieving, and updating forms (`POST /api/forms`, `GET /api/forms`, `PUT /api/forms/:formId`).
    *   Develop the form builder UI in React.
        *   Allow users to add a title and description.
        *   Implement functionality to add different question types (text, multiple choice, etc.).
        *   Allow users to mark questions as required.
        *   Implement saving the form structure to the backend.
    *   Create a dashboard page where users can see a list of their created forms.

## Sprint 4: Form Filling and Submission

**Goal**: Enable end-users to view and submit forms.

*   **Tasks**:
    *   Implement the public-facing API endpoint to fetch a form by its shareable URL (`GET /forms/:shareableUrl`).
    *   Develop the React component to render a form based on the fetched structure.
    *   Implement the form submission API endpoint (`POST /forms/:shareableUrl/submit`).
    *   Add frontend and backend validation for form submissions (e.g., for required fields).
    *   Display a confirmation message to the user after a successful submission.
    *   Implement logic for handling prefilled fields (e.g., automatically adding a timestamp).

## Sprint 5: Response Management & Export

**Goal**: Provide form creators with tools to manage and view responses.

*   **Tasks**:
    *   Implement the API endpoint to retrieve all submissions for a specific form (`GET /api/forms/:formId/submissions`).
    *   Develop the UI to display the collected responses in a table or list format.
    *   Implement the CSV export functionality on the backend (`GET /api/forms/:formId/submissions/csv`).
    *   Add a button to the UI to trigger the CSV download.
    *   Implement form management features like closing a form to new submissions.

## Sprint 6: Testing, Deployment & Final Touches

**Goal**: Ensure the application is well-tested, and prepare for deployment.

*   **Tasks**:
    *   Write comprehensive integration tests for the backend API.
        *   Use an in-memory database (H2) for repository tests.
        *   Use Testcontainers to run an OAuth server for testing protected endpoints.
    *   Write unit and integration tests for the frontend components.
    *   Finalize the build process, ensuring the frontend is correctly packaged into a WebJar and included in the final Spring Boot JAR.
    *   Create a `Dockerfile` for the application.
    *   Set up a CI/CD pipeline (e.g., using GitHub Actions) to automate building, testing, and deploying the application.
    *   Perform final user acceptance testing (UAT) and fix any remaining bugs.
