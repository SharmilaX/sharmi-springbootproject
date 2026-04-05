# AGENTS.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Structure

This is a **multi-module Maven project** with two Spring Boot microservices:

```
SharmiSpingBoot/          ← root POM (pom.xml)
├── UserProfile/          ← User/Address REST API  (port 8080)
└── ProductService/       ← Product catalog + Wishlist API  (port 8081)
```

## Build & Run

### From the root directory

```bash
# Build all modules
./mvnw package

# Build skipping tests
./mvnw package -DskipTests
```

### UserProfile (run from `UserProfile/`)

```bash
./mvnw spring-boot:run

# Run all tests
./mvnw test

# Run a single test class
./mvnw test -Dtest=UserProfileApplicationTests
```

### ProductService (run from `ProductService/`)

```bash
./mvnw spring-boot:run

# Run all tests
./mvnw test
```

- **UserProfile** starts on `http://localhost:8080`. H2 console at `/h2-console`.
- **ProductService** starts on `http://localhost:8081`. H2 console at `/h2-console`.

Both services share the same H2 file database: `jdbc:h2:file:C:/Users/562485/test;AUTO_SERVER=TRUE`

## Architecture

Standard layered architecture within each service:

```
Controller → Service Interface → Service Impl → Repository → H2 (file-based)
```

### Inter-service communication

ProductService calls UserProfile via `UserProfileClient` (Spring `RestClient`). The base URL is configured in `ProductService/src/main/resources/application.yaml`:

```yaml
userprofile:
  service:
    url: http://localhost:8080
```

`UserProfileClient.fetchUser(email)` calls `GET /api/fetchUser?email=` and throws `ResourceNotFoundException` on 4xx.

### Database

Both services use H2 in **file mode** (not in-memory), sharing the same file at `C:/Users/562485/test` with `AUTO_SERVER=TRUE`. Schema is defined in each module's `src/main/resources/schema.sql` and applied on startup via `ddl-auto: update`. No Flyway/Liquibase — DDL changes go directly in `schema.sql`. ProductService also has a `data.sql` for seed data.

## Key Patterns

**Entity design** — All entities (except `Wishlist`) extend `BaseEntity`, which provides `createdAt`, `createdBy`, `updatedAt`, `updatedBy`. Audit fields are set manually in service methods (e.g., `setCreatedAt(LocalDateTime.now())`). Entities use a mix of Lombok annotations and hand-written getters/setters — follow the existing file's style when adding new entities.

**Mappers** — Plain static utility classes (`UserMapper`, `AddressMapper`, `CategoryMapper`, etc.) with `mapToDto` / `mapToEntity` methods. No MapStruct.

**Service layer** — Each domain has an `IXxxService` interface and a `XxxServiceImpl`. The service is the only place that touches repositories.

**Exception handling** — `GlobalExceptionHandler` (`@ControllerAdvice`) in UserProfile handles `ResourceNotFoundException` (→ 404) and `UserAlreadyExistException` (→ 400). ProductService has domain-specific exception classes (`CategoryAlreadyExistException`, `ProductAlreadyExistException`, `SkuAlreadyExistException`, `SubCategoryAlreadyExistException`, `WishlistAlreadyExistException`, `ResourceNotFoundException`) but no `GlobalExceptionHandler` yet — new ones should be added there.

**Response shape** — Success responses use `ResponseDto(statusCode, message)`. Error responses use `ErrorResponseDto`. Status codes are string constants defined in `UserConstants` (UserProfile) and `ProductConstants` (ProductService).

## UserProfile Module

**Package:** `com.example.SharmiSpringBoot.UserProfile`

**Entities:** `User`, `Address` (both extend `BaseEntity`)

**Key lookup:** User/Address lookup is keyed by **email**.

**Tests:** Three test classes covering controller (`UserControllerTest`), repository (`UserRepositoryTest`), and service (`UserServiceImplTest`).

## ProductService Module

**Package:** `com.example.SharmiSpringBoot.ProductService`

**Entity hierarchy:**

```
Category → SubCategory → Product → SKU
```

Each level is a separate entity and table. `Wishlist` is an independent entity linked by `userEmail` and `productId` (no FK join to `Product`; cross-service reference by email).

**Entities:**
- `Category` — `categoryCode` (unique), `categoryName`, `description`
- `SubCategory` — FK to `Category`, `subCategoryCode` (unique), `subCategoryName`, `description`
- `Product` — FK to `SubCategory`, `productCode` (unique), `productName`, `brand`, `price`, `status`
- `SKU` — FK to `Product`, `skuCode` (unique), `color`, `size`, `stockQuantity`, `skuPrice`, `status`
- `Wishlist` — `userEmail`, `productId`, `addedAt` (does **not** extend `BaseEntity`)
- `ProductDetails` — supplementary product detail entity

**Controllers:** `CategoryController`, `SubCategoryController`, `ProductController`, `SkuController`, `WishlistController`
