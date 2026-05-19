# Task Management API

A Spring Boot backend application for managing users and role-based task assignment. The project demonstrates REST API development, JWT authentication, MySQL persistence, request validation, global exception handling, and API documentation with Swagger/OpenAPI.

## Features

- User registration and login
- JWT-based authentication
- Manager and employee roles
- Manager-created task assignment
- Task status tracking
- Task priority support
- Due date and completion date handling
- Soft delete support for completed tasks
- Request validation using Jakarta Validation
- Consistent JSON error responses through global exception handling
- Swagger/OpenAPI documentation

## Tech Stack

- Java 17
- Spring Boot 3
- Spring Web
- Spring Security
- Spring Data JPA
- MySQL
- JWT
- Maven
- Swagger/OpenAPI

## API Overview

### Authentication

| Method | Endpoint | Description |
| --- | --- | --- |
| POST | `/api/auth/login` | Login and generate JWT token |

### Users

| Method | Endpoint | Description |
| --- | --- | --- |
| POST | `/api/users` | Create/register a user |
| GET | `/api/users` | Get all users |
| GET | `/api/users/{id}` | Get user by id |
| GET | `/api/users/email/{email}` | Get user by email |
| PUT | `/api/users/{id}` | Update user details |
| DELETE | `/api/users/{id}` | Delete user |

### Tasks

| Method | Endpoint | Description |
| --- | --- | --- |
| POST | `/api/tasks` | Create a task |
| PUT | `/api/tasks/{taskId}/complete` | Mark task as completed |
| GET | `/api/tasks/employee/{employeeId}` | Get tasks assigned to an employee |
| GET | `/api/tasks/manager/{managerId}` | Get tasks created by a manager |
| DELETE | `/api/tasks/{taskId}/manager/{managerId}` | Delete a task by manager |

## Task Priority Values

```text
LOW
MEDIUM
HIGH
URGENT
```

## Task Status Values

```text
PENDING
IN_PROGRESS
COMPLETED_ON_TIME
COMPLETED_LATE
OVERDUE
```

## Setup

1. Clone the repository.

```bash
git clone https://github.com/SiddhiDawkhar/Task_Management_Module.git
cd Task_Management_Module
```

2. Create a MySQL database.

```sql
CREATE DATABASE task_management;
```

3. Update database credentials in `src/main/resources/application.properties`.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/task_management?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=springuser
spring.datasource.password=springpass
```

4. Run the application.

```bash
mvn spring-boot:run
```

The application runs on:

```text
http://localhost:8081
```

## Swagger UI

After starting the application, open:

```text
http://localhost:8081/swagger-ui.html
```

Use the login API to generate a JWT token, then authorize requests in Swagger using:

```text
Bearer <your-token>
```

## Sample Create Task Request

```json
{
  "title": "Prepare sprint report",
  "description": "Create a summary of completed and pending sprint tasks.",
  "dueDate": "2026-06-01",
  "priority": "HIGH",
  "managerId": 1,
  "employeeId": 2
}
```

## Portfolio Highlights

This project shows backend development skills useful for remote Java/Spring Boot work:

- Designing REST APIs with layered architecture
- Implementing JWT authentication with Spring Security
- Using DTOs for request and response payloads
- Validating client input at the API boundary
- Returning consistent error responses
- Managing relational data with Spring Data JPA and MySQL
- Documenting APIs with Swagger/OpenAPI

## Current Improvement Scope

- Add role-based endpoint authorization with `@PreAuthorize`
- Use logged-in user context instead of passing manager/employee ids directly
- Add pagination and filtering for task lists
- Add Docker support
- Add unit and integration tests
