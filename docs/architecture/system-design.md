# System architecture

## Starting architecture

TripWeave begins as a modular monolith with a separate web client. This keeps
local development, transactions, and deployments simple while preserving clear
domain boundaries.

```text
React + TypeScript
        |
   REST / WebSocket (WebSocket added in Phase 2)
        |
Java Spring Boot API
  |-- identity and access
  |-- trips and membership
  |-- availability
  |-- proposals and voting
  |-- itinerary
  |-- expenses
        |
PostgreSQL + PostGIS
```

Redis, Kafka, worker processes, and object storage are introduced only when a
scheduled feature needs them. Docker Compose is the local runtime boundary.

## Design principles

- Domain modules own their data and rules; cross-module access uses explicit
  application services rather than direct repository access.
- PostgreSQL is the source of truth. Cache and event infrastructure must be
  disposable or recoverable.
- Money uses decimal types and explicit ISO currency codes, never floating point.
- Time is stored as UTC instants plus the relevant IANA time-zone identifier.
- State transitions are explicit, validated, and idempotent where retries occur.
- Every external side effect has a stable operation identifier.
- Authorization is enforced server-side at the use-case boundary.

## Early quality attributes

1. Correctness of membership, voting state, dates, and expense balances.
2. Ease of local setup and automated testing.
3. Clear module boundaries that permit later evolution.
4. Observable failures with structured, privacy-safe logs.

## Planned evolution

- **Phase 2:** WebSockets, local operation log, synchronization, version history.
- **Phase 3:** constraint solver, interval algorithms, PostGIS route planning.
- **Phase 4:** Kafka, workers, outbox pattern, durable reservation orchestration.
- **Phase 5:** OpenTelemetry, load/failure tests, CI/CD, Kubernetes deployment.

Major architectural choices must be recorded in `docs/architecture/adr/`.

