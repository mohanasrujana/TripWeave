# TripWeave status

- **Updated:** August 31, 2026
- **Phase:** Foundation
- **Sprint:** Sprint 0 — Walking skeleton
- **Current ticket:** TW-001 — Confirm product and engineering constraints

## Done

- Product vision and Phase 1 scope are documented.
- Phase 1 non-goals are accepted.
- Maven 3.9.10, Node.js 25.8.2, and npm 11.11.1 are available locally.
- Maven, npm, and self-hosted authentication were selected.
- The repository, backlog, sprint plan, and engineering workflow are in place.

## Next

1. Confirm the weekly capacity assumption of 15 focused hours.
2. Install or select Java 21; Java 23 is currently active.
3. Install Docker Desktop and record its Docker and Compose versions.
4. Close TW-001 and begin TW-002, the Spring Boot API bootstrap.

## Blocked

- TW-002 requires Java 21.
- TW-004 requires Docker Desktop.
- Sprint capacity cannot be finalized until the weekly-hours assumption is
  confirmed.

## Evidence

- Toolchain results are recorded in
  `docs/architecture/adr/001-build-and-authentication-foundations.md`.
- The August 31 work log is in `project/check-ins/2026-08-31.md`.

## Scope guardrail

The immediate goal is a tested request path from the React client through the
Spring Boot API to PostgreSQL. CRDTs, Kafka, Kubernetes, booking, and AI remain
out of scope for Sprint 0.
