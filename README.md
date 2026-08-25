# Web Calendar API

## Project Overview
This project is a RESTful backend application built with Spring Boot to manage calendar events. It handles HTTP requests, processes JSON payloads, validates input data, and persists records using Spring Data JPA and an embedded H2 database.

---

## Tech Stack
* **Language:** Java 17
* **Framework:** Spring Boot 3.4 (Spring Web MVC & Spring Data JPA)
* **Build Tool:** Gradle (with pinned Wrapper)
* **Database:** H2 Database
* **Tools:** IntelliJ IDEA, Postman, Git

---

## Core Architecture & Features
* **RESTful Web Services:** Implements `EventController` to handle HTTP methods (`GET`, `POST`, `DELETE`), mapping incoming URLs, query parameters, and JSON request bodies.
* **Input Validation & Exception Handling:** 
  * Utilizes Jakarta Bean Validation (`@NotBlank`, `@NotNull`) via `EventRequest` to enforce data integrity.
  * Intercepts `MethodArgumentNotValidException` to return clean `400 Bad Request` responses.
  * Throws custom `EventNotFoundException` when a requested resource is missing.
* **Persistence Layer:** Integrates Spring Data JPA `JpaRepository` interfaces and `@Entity` models mapped to temporal `LocalDate` fields.

---

## API Endpoints
* `GET /event` — Retrieves all events or filters them using optional `start_time` and `end_time` query parameters.
* `GET /event/today` — Fetches all events scheduled for the current date.
* `GET /event/{id}` — Retrieves a specific event by its unique identifier.
* `POST /event` — Creates and validates a new calendar event.
* `DELETE /event/{id}` — Deletes an event by its ID.
* **Request Body Example (JSON):**
    ```json
    {
      "event": "Java's birthday",
      "date": "2026-08-30"
    }
    ```
---

## Getting Started
To run this project locally, clone the repository and launch it via your terminal or IDE:

1. Clone the repository: 
   ```bash
   git clone https://github.com/inaciofragalli/web-calendar.git
   
2. Navigate into the project directory:
   ```bash
   cd web-calendar

3. Run the application using the Gradle wrapper:
   ```bash
   ./gradlew bootRun
   
4. Test endpoints locally using Postman (recommended for testing `POST` and `DELETE` requests) or a web browser for `GET` requests.
