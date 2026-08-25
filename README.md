# Web Calendar API

## Project Overview
This project is a RESTful backend application built to manage calendar events and dates. It transitions core application architecture from manual object initialization to the **Spring Inversion of Control (IoC) container**, implementing **Spring Beans, Components, and Dependency Injection**. It handles HTTP requests, processes JSON payloads, and connects to relational database structures using Spring Data JPA.

---

## Tech Stack
*   **Language:** Java
*   **Framework:** Spring Boot (Spring Web MVC & Spring Data JPA)
*   **Build Tool:** Gradle
*   **Testing:** JUnit & Mockito
*   **Tools:** IntelliJ IDEA, Postman, Git

---

## Core Architecture & Learning Concepts
*   **Spring Container & Lifecycle:** Utilizes `@Bean`, `@Component`, Spring Stereotypes, and `ApplicationContext` to manage the application lifecycle and scopes.
*   **RESTful Web Services:** Implements Controllers to handle HTTP methods (`GET`, `POST`, `DELETE`) mapping incoming URLs, query parameters, and request bodies.
*   **Data & Validation:** Employs Bean Validation, JSON serialization/deserialization, and Java's `LocalDate` for handling temporal data.
*   **Persistence Layer:** Integrates relational data models, Object-Relational Mapping (ORM), Entities, and CRUD repositories for structured data storage.

---

## Getting Started
To run this project locally, clone the repository and launch it via your IDE or terminal:

1. Clone the repository: `git clone github.com/inaciofragalli/web-calendar.git)`
2. Open the project in IntelliJ IDEA (or your preferred IDE) and let Gradle download dependencies.
3. Run the Spring Boot application configuration.
4. Test endpoints locally using Postman or a web browser at `http://localhost:8080`.
