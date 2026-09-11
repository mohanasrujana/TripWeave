# TripWeave status

- **Updated:** September 11, 2026
- **Phase:** Foundation
- **Sprint:** Sprint 0 — Walking skeleton
- **Current ticket:** TW-006 — Establish CI and developer runbook

## Done

- TW-001 confirmed the product, toolchain, and engineering constraints.
- TW-002 delivered the Java 21 Spring Boot API and health endpoint.
- TW-003 delivered the React and TypeScript client with API health states.
- TW-004 added the digest-pinned PostgreSQL/PostGIS environment and Flyway.
- TW-005 added the minimal trip schema and validated create/retrieve API.
- The React form creates a trip and renders the saved database result.
- Backend unit, MVC, application-context, and PostgreSQL integration tests pass.
- Frontend component tests, formatting, linting, TypeScript, and build pass.
- A live browser check proved the React → Spring Boot → PostgreSQL path.
- TW-005 backend and frontend are committed as `4acd04d` and `0b903fb`.

- TW-006 CI workflow and developer runbook are implemented locally.
- A temporary checkout passed API/web checks with a fresh isolated database.

## Next

1. Review and commit TW-006 CI and runbook changes.
2. Push to `master` and verify both hosted CI jobs.
3. Record hosted CI evidence before closing TW-006 and starting TW-007.

## Blocked

- No local blockers; hosted CI remains pending the first push-to-master run.

## Evidence

- Flyway reports schema version `2` with both migrations successful.
- `./mvnw verify` passed 14 tests, Spotless, and Checkstyle.
- `npm run check` passed Prettier, Oxlint, 5 tests, TypeScript, and Vite build.
- Browser-created trip `08884d40-98b3-45fb-abfe-60b675c62e02` was returned by
  the GET API and confirmed directly in PostgreSQL.
- Detailed implementation notes are in `project/tickets/TW-005.md`.

- TW-006: clean Maven verification on Temurin 21 passed 14 tests and static checks.
- TW-006: clean `npm ci` and web checks passed 5 tests and the production build.
- TW-006: actionlint 1.7.12 passed; fresh Flyway migrations 1 and 2 succeeded.
- TW-006 changes are uncommitted; see `project/tickets/TW-006.md`.

## Scope guardrail

Money, voting, invitations, authentication, CRDTs, Kafka, Kubernetes, booking,
and AI remain outside the implemented Sprint 0 walking skeleton.
