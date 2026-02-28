# AGENTS.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Run

All commands run from the `UserProfile/` directory.

```bash
# Run the application
./mvnw spring-boot:run

# Build (compile + package)
./mvnw package

# Run all tests
./mvnw test

# Run a single test class
./mvnw test -Dtest=UserProfileApplicationTests

# Skip tests during build
./mvnw package -DskipTests
```

The app starts on `http://localhost:8080`. H2 console is at `/h2-console`.

## Architecture

This is a **Spring Boot 4.0.2** REST API using a standard layered architecture:

```
Controller → Service Interface → Service Impl → Repository → H2 (in-memory)
```

The database schema is defined in `src/main/resources/schema.sql` and auto-applied on startup. There is no Flyway/Liquibase — DDL changes go directly in `schema.sql`.

### Key patterns

**Entity design** — All entities extend `BaseEntity`, which provides `createdAt`, `createdBy`, `updatedAt`, `updatedBy`. Audit fields are set manually in service methods (e.g., `setCreatedAt(LocalDateTime.now())`); Spring Data auditing annotations are present but not fully wired. Entities use a mix of Lombok annotations and hand-written getters/setters — when adding new entities, follow the existing file's style.

**Mappers** — Plain static utility classes (`UserMapper`, `AddressMapper`) with `mapToDto` / `mapToEntity` methods. No MapStruct. New domain areas should follow the same pattern.

**Service layer** — Each domain has an `IXxxService` interface and a `XxxServiceImpl`. The service is the only place that touches repositories. User/address lookup is always keyed by **email**.

**Exception handling** — `GlobalExceptionHandler` (`@ControllerAdvice`) handles `ResourceNotFoundException` (→ 404) and `UserAlreadyExistException` (→ 400). New domain-specific exceptions should be registered here.

**Response shape** — Success responses use `ResponseDto(statusCode, message)`. Error responses use `ErrorResponseDto`. Status codes are string constants defined in `UserConstants` (and analogous constants files for new domains).

### Planned Product Service (`agent.md`)

`agent.md` at the repo root describes a planned **Product Service** with a four-level hierarchy: `Catalog → Category → Product → SKU`. This will live under the package `com.example.SharmiSpringBoot.UserProfile.product` and follow the same layered conventions as the existing User/Address service.
