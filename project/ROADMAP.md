# Product roadmap

The schedule uses two-week sprints at an assumed **15 focused hours per week**.
Dates and scope change when evidence changes; quality gates do not.

## Phase 0 — Foundation

**August 27–September 13, 2026**

- Establish product boundaries, architecture, repository, local environment,
  CI, data model, and a walking-skeleton request from UI to database.

Exit gate: a new contributor can follow the runbook and see a tested health/trip
flow locally and in CI.

## Phase 1 — Usable product

**September 14–November 8, 2026 (four sprints)**

- Authentication and authorization
- Trip creation, invitations, and membership roles
- Availability collection and overlap calculation
- Destination/activity proposals and voting
- Itinerary CRUD and ordering
- Shared expenses and balances

Exit gate: one group can complete the initial user journey with durable data,
server-side authorization, and end-to-end coverage.

## Phase 2 — Advanced collaboration

**November 9, 2026–January 3, 2027 (four sprints, including holiday buffer)**

- WebSocket updates
- Concurrent itinerary editing
- IndexedDB offline storage and operation log
- Synchronization/conflict handling
- Version history and restore

Exit gate: two clients can edit, disconnect, reconnect, converge, and inspect the
change history without losing accepted changes.

## Phase 3 — Algorithms and geospatial planning

**January 4–February 28, 2027 (four sprints)**

- Date-overlap and recurrence handling
- Ranked-choice voting and veto policies
- Constraint-based scheduling
- PostGIS-backed route optimization
- Expense-settlement minimization

Exit gate: benchmark fixtures demonstrate correctness, determinism, and stated
performance bounds for each algorithm.

## Phase 4 — Distributed reliability

**March 1–April 25, 2027 (four sprints)**

- Transactional outbox, Kafka, notification workers
- Idempotency, retry policies, and dead-letter handling
- Durable reservation state machine and Saga-style compensation
- Failure recovery tests

Exit gate: injected failures do not duplicate user-visible effects or corrupt
workflow state.

## Phase 5 — Production quality

**April 26–June 20, 2027 (four sprints)**

- OpenTelemetry traces, metrics, logs, and dashboards
- Load, security, and failure-injection tests
- CI/CD and staged deployment
- Kubernetes manifests and operational runbooks

Exit gate: a tagged release can be deployed, observed, rolled back, and restored
using documented procedures.

