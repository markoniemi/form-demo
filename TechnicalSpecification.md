# Technical Specification for Form Application

## 1. Introduction

This document provides a technical overview of the Form Application. It details the technology stack, architecture, database schema, and API endpoints that will be used to implement the features outlined in the `RequirementsSpecification.md`.

## 2. Technology Stack

*   **Frontend**: React.js with TypeScript and Bootstrap for styling.
*   **Backend**: Java with Spring Boot framework.
*   **Database**: PostgreSQL.
*   **Authentication**: JWT (JSON Web Tokens) for securing API endpoints.

## 3. System Architecture

A monolithic architecture will be used for simplicity and ease of initial development and deployment. The system will consist of a single-page application (SPA) frontend that communicates with a backend server via a RESTful API.

*   **Frontend (Client)**: A React application that handles the user interface for form creation, filling, and management.
*   **Backend (Server)**: A Spring Boot application that manages business logic, user authentication, and database interactions.

## 4. Database Schema

The database will be designed with a relational schema using PostgreSQL.

### `users` table
```sql
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
```

### `forms` table
```sql
CREATE TABLE forms (
    id SERIAL PRIMARY KEY,
    creator_id INTEGER REFERENCES users(id) ON DELETE CASCADE,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    published BOOLEAN DEFAULT false,
    shareable_url VARCHAR(255) UNIQUE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    closed BOOLEAN DEFAULT false
);
```

### `form_fields` table
```sql
CREATE TABLE form_fields (
    id SERIAL PRIMARY KEY,
    form_id INTEGER REFERENCES forms(id) ON DELETE CASCADE,
    type VARCHAR(50) NOT NULL, -- 'text', 'textarea', 'multiple-choice', etc.
    label VARCHAR(255) NOT NULL,
    options TEXT, -- For multiple-choice, checkboxes, dropdown (e.g., JSON array)
    required BOOLEAN DEFAULT false,
    prefilled BOOLEAN DEFAULT false,
    prefill_type VARCHAR(50) -- 'timestamp', 'filler-info'
);
```

### `submissions` table
```sql
CREATE TABLE submissions (
    id SERIAL PRIMARY KEY,
    form_id INTEGER REFERENCES forms(id) ON DELETE CASCADE,
    submitted_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    filler_info JSONB -- Optional: Information about the filler
);
```

### `submission_responses` table
```sql
CREATE TABLE submission_responses (
    id SERIAL PRIMARY KEY,
    submission_id INTEGER REFERENCES submissions(id) ON DELETE CASCADE,
    field_id INTEGER REFERENCES form_fields(id),
    value TEXT
);
```

## 5. API Endpoints

The following RESTful API endpoints will be exposed by the backend.

### User Management
*   `GET /api/auth/login`: Initiates the OAuth 2.0 login flow (e.g., redirects to Google/GitHub).
*   `GET /api/auth/callback`: Callback URL for the OAuth provider to redirect to after successful authentication.
*   `POST /api/auth/logout`: Log out the user.

### Form Management
*   `POST /api/forms`: Create a new form (Authentication required).
*   `GET /api/forms`: Get all forms for the logged-in creator (Authentication required).
*   `GET /api/forms/:formId`: Get a specific form by its ID.
*   `PUT /api/forms/:formId`: Update a form (Authentication required).
*   `DELETE /api/forms/:formId`: Delete a form (Authentication required).
*   `POST /api/forms/:formId/publish`: Publish a form and generate a shareable URL (Authentication required).
*   `POST /api/forms/:formId/close`: Close a form to new submissions (Authentication required).

### Form Filling & Submissions
*   `GET /forms/:shareableUrl`: Get a form by its shareable URL for filling.
*   `POST /forms/:shareableUrl/submit`: Submit a form.
*   `GET /api/forms/:formId/submissions`: Get all submissions for a form (Authentication required).
*   `GET /api/forms/:formId/submissions/csv`: Export submissions to a CSV file (Authentication required).

## 6. Testing

The application will be thoroughly tested to ensure quality and correctness.

*   **Unit Testing**: Backend logic will be tested using JUnit 5 and Mockito to isolate components.
*   **Integration Testing**: Spring Boot's testing framework will be used for integration tests. An in-memory database (e.g., H2) will be used for database testing. Testcontainers will be used to spin up a dedicated OAuth 2.0 Authorization Server (e.g., a Keycloak container) to run tests in an environment that closely resembles production.
*   **Frontend Testing**: The frontend will have its own suite of unit and integration tests using frameworks like Jest and React Testing Library.

## 7. Deployment

The application will be packaged as a single executable JAR file (uber-jar). This JAR will contain the Spring Boot backend and all the static frontend assets.

*   **Deployment Unit**: A single JAR file containing both backend and frontend.
*   **Hosting**: The JAR can be deployed on any platform that runs Java, such as Heroku, AWS Elastic Beanstalk, or as a Docker container.
*   **Database**: A managed PostgreSQL service like Amazon RDS or Heroku Postgres will be used for the database.
*   **Authentication**: For development, the embedded Spring Boot Authorization Server will be used. In a production environment, a dedicated identity provider (e.g., Keycloak, Okta, or a separate Spring Boot Authorization Server instance) is recommended.
*   **CI/CD**: A continuous integration and deployment pipeline will be set up (e.g., using GitHub Actions) to automate the build, testing, and deployment of the single JAR file.
