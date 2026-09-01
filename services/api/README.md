# TripWeave API

Java 21 Spring Boot API for TripWeave.

## Run locally

```bash
./mvnw spring-boot:run
```

The API starts at `http://localhost:8080`.

## Health endpoint

Request:

```http
GET /api/health
```

Response:

```json
{
  "status": "UP"
}
```

Verify from another terminal:

```bash
curl --fail --show-error http://localhost:8080/api/health
```

## Tests

Run the complete test suite:

```bash
./mvnw test
```

## Quality checks

Run compilation, tests, packaging, formatting verification, and static analysis:

```bash
./mvnw verify
```

Apply automatic Java formatting when needed:

```bash
./mvnw spotless:apply
```
