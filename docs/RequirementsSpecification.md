# Requirements Specification

## 1. Introduction
The application enables form creators to create, manage, and fill out custom forms. It is intended for use cases such as surveys, registrations, and feedback collection.

## 1.1 User Roles
- **Form Creator**: Can design, edit, and manage forms; view and export responses.
- **Form Filler**: Can access forms, fill them out, and submit responses. Must log in using OAuth to submit responses.

## 2. Functional Requirements
- Form creators can create new forms with customizable fields (text, number, date, choice).
- Form creators can edit and delete existing forms.
- Form fillers can fill out forms and submit responses.
- Form creators can view and export submitted responses.
- Authentication for form creators and respondents using OAuth 2.0 (e.g., Google, Microsoft, GitHub). Form fillers must log in to submit responses.

## 3. Non-Functional Requirements
- Responsive user interface for desktop and mobile.
- Secure storage of form data and responses.
- Fast performance for form creation and submission.
- Support for multiple languages.

## 4. Constraints
- Must run on modern web browsers.
- Data must be stored securely and comply with privacy regulations.
- OAuth 2.0 providers must be supported and properly configured.
- Use RESTful APIs for communication between frontend and backend.
- Use React (with Vite, Bootstrap, TypeScript) for frontend.
- Use Spring Boot (with Spring Data JPA, Spring Security, Lombok) for backend.
- Use PostgreSQL for data storage in production, H2 for testing.
- Package frontend as a JAR file and use it in the backend module.
- Create OAuth module for handling authentication and authorization during testing, packaged as a Docker container and used in backend tests via Testcontainers.
