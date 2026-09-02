# TripWeave status

- **Updated:** September 2, 2026
- **Phase:** Foundation
- **Sprint:** Sprint 0 — Walking skeleton
- **Current ticket:** TW-003 — Bootstrap React TypeScript client

## Done

- Product vision and Phase 1 scope are documented.
- Phase 1 non-goals are accepted.
- Maven 3.9.10, Node.js 25.8.2, and npm 11.11.1 are available locally.
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
- Per-ticket implementation notes are maintained under `project/tickets/`.

## Next

1. Review and commit the remaining TW-003 test and quality changes.
2. Replace the generic Vite README with TripWeave-specific web instructions.
3. Run the API and web client together and manually verify the health states.
4. Close TW-003 after the Definition of Done review.

## Blocked

- None.

## Evidence

- Toolchain results are recorded in
  `docs/architecture/adr/001-build-and-authentication-foundations.md`.
- The August 31 work log is in `project/check-ins/2026-08-31.md`.
- TW-002 evidence is recorded in `project/check-ins/2026-09-01.md`.
- TW-003's automated check passed on September 2, 2026: formatting and linting
  passed, all 3 component tests passed, and the production build succeeded.
- Detailed ticket records are indexed in `project/tickets/README.md`.

## Scope guardrail

The immediate goal is a tested request path from the React client through the
Spring Boot API to PostgreSQL. CRDTs, Kafka, Kubernetes, booking, and AI remain
out of scope for Sprint 0.
