# Simple API - Spring Boot Kotlin JWT Example

This project is a simple RESTful API built with Spring Boot, Kotlin, and JWT authentication. It provides user registration, login, and user management endpoints, using MySQL as the database.

## Features

- User registration and login with JWT authentication
- Secure endpoints with role-based access
- OpenAPI/Swagger documentation
- MySQL database integration

## Prerequisites

- [JDK 21+](https://openjdk.org/)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/download/)
- [Docker](https://www.docker.com/) (optional)
- [Gradle](https://gradle.org/) (wrapper included)

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/simple-api.git
cd simple-api
```

### 2. Configure Database

- Copy `compose.yaml` to your local machine.
- Start MySQL using Docker Compose:

```bash
docker compose up -d
```

- Update `src/main/resources/application.properties` with your database credentials:

```
spring.datasource.url=jdbc:mysql://localhost:3306/mydatabase
spring.datasource.username=myuser
spring.datasource.password=secret
```

### 3. Open in IntelliJ IDEA

1. Open IntelliJ IDEA.
2. Select **Open** and choose the project root folder.
3. IntelliJ will automatically detect the Gradle project and import dependencies.
4. Make sure your JDK is set to version 21 or above (File > Project Structure > Project SDK).

### 4. Build and Run

- To build and run the application, use the Gradle tasks in IntelliJ IDEA or run:

```bash
./gradlew bootRun
```

- The API will be available at `http://localhost:8080`.

### 5. API Documentation

- Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Usage

- Register a new user: `POST /api/v1/auth/register`
- Login: `POST /api/v1/auth/login`
- Access user endpoints: `GET /api/v1/users` (requires JWT token)

## Project Structure

- `src/main/kotlin/org/example/simpleapi` - Main application code
- `users` - User-related controllers, services, entities, and DTOs
- `common` - Common utilities, JWT, and security configuration

## License

MIT License

