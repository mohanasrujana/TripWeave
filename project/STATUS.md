# TripWeave status

- **Updated:** September 8, 2026
- **Phase:** Foundation
- **Sprint:** Sprint 0 — Walking skeleton
- **Current ticket:** TW-005 — Deliver the trip walking skeleton

## Done

- Product vision and Phase 1 scope are documented.
- Phase 1 non-goals are accepted.
- Maven 3.9.10, Node.js 26.8.1, and npm 11.19.0 are available locally.
- Maven, npm, and self-hosted authentication were selected.
- The repository, backlog, sprint plan, and engineering workflow are in place.
- Weekly capacity is confirmed at 15 focused hours.
- Java 21.0.12.1 is installed and active.
- Docker Desktop 4.89.0, Docker 29.7.2, and Compose v5.5.0 are operational.
- TW-001 is closed.
- TW-002 delivered the Java 21 Spring Boot 4.1.1 API.
- `GET /api/health` is documented and tested.
- Unit, MVC slice, and application-context tests pass.
- `./mvnw verify` runs tests, Spotless, and Checkstyle.
- TW-002 is closed.
- The React 19 and TypeScript client is scaffolded under `apps/web`.
- The client renders loading, success, and error states for `GET /api/health`.
- Vite proxies `/api` requests to the Spring Boot service on port 8080.
- Component tests cover all three health states.
- `npm run check` passes Prettier, Oxlint, 3 Vitest tests, TypeScript, and the
  Vite production build.
- TW-003 is closed in commit `6d1d3bc`.
- Docker Compose starts a digest-pinned PostgreSQL 17/PostGIS 3.5 database.
- Database settings come from the documented `.env.example` variables.
- The Spring Boot API connects through JDBC and uses Flyway for migrations.
- Flyway migration `V1__enable_postgis.sql` manages the PostGIS extension.
- A clean database volume was recreated and migrated successfully from the
  local-development runbook.
- `./mvnw verify` passes with the database environment configured.
- TW-004 is closed in commit `776f9a9`.
- Per-ticket implementation notes are maintained under `project/tickets/`.

## Next

1. Define the minimal trip database model and migration.
2. Implement validated create and retrieve API operations.
3. Connect the React form to the API and render the saved trip.
4. Add unit, integration, API, and UI coverage for the walking skeleton.

## Blocked

- None.

## Evidence

- Toolchain results are recorded in
  `docs/architecture/adr/001-build-and-authentication-foundations.md`.
- The August 31 work log is in `project/check-ins/2026-08-31.md`.
- TW-002 evidence is recorded in `project/check-ins/2026-09-01.md`.
- TW-003's automated check passed on September 2, 2026: formatting and linting
  passed, all 3 component tests passed, and the production build succeeded.
- Manual browser verification covered the real API success response and the
  unavailable-API error response.
- TW-004 uses the pinned image
  `postgis/postgis:17-3.5@sha256:01a6a70e41e6c4467c8f55f6063555ed72db2d6662cd0d571040d42eadaeb6f6`.
- Docker reported the database as healthy and PostGIS reported version 3.5.
- The clean-volume API verification passed and Flyway version `1` was reported
  as successfully applied.
- Detailed ticket records are indexed in `project/tickets/README.md`.

## Scope guardrail

The immediate goal is a tested request path from the React client through the
Spring Boot API to PostgreSQL. CRDTs, Kafka, Kubernetes, booking, and AI remain
out of scope for Sprint 0.
