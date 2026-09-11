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

## Next

1. Add CI for API and web checks on pull requests.
2. Finish exact clean-checkout setup and verification instructions.
3. Verify the documented workflow from a clean checkout.

## Blocked

- None.

## Evidence

- Flyway reports schema version `2` with both migrations successful.
- `./mvnw verify` passed 14 tests, Spotless, and Checkstyle.
- `npm run check` passed Prettier, Oxlint, 5 tests, TypeScript, and Vite build.
- Browser-created trip `08884d40-98b3-45fb-abfe-60b675c62e02` was returned by
  the GET API and confirmed directly in PostgreSQL.
- Detailed implementation notes are in `project/tickets/TW-005.md`.

## Scope guardrail

Money, voting, invitations, authentication, CRDTs, Kafka, Kubernetes, booking,
and AI remain outside the implemented Sprint 0 walking skeleton.
