# Current sprint: Sprint 0 — Walking skeleton

- Dates: August 27–September 13, 2026
- Capacity: 34 hours (four-day kickoff plus two 15-hour weeks)
- Goal: Establish a repeatable local development loop and prove one request can
travel from the React client through the Spring Boot API to PostgreSQL.

## Committed work

### TW-001 — Confirm product and engineering constraints

**Acceptance criteria**

- [x] Review product vision and explicitly accept or edit Phase 1 non-goals.
- [x] Confirm weekly capacity and replace placeholder work hours if needed.
- [x] Record Java, Node, Docker, Maven, and npm versions in an ADR. Maven,
  Node.js, and npm are recorded; Docker is not installed and local Java is 23
  rather than the required Java 21.
- [x] Resolve the remaining capacity decision in `project/STATUS.md`.



### TW-002 — Bootstrap Spring Boot API

**Acceptance criteria**

- [ ] Java 21 Spring Boot project exists under `services/api`.
- [ ] Health endpoint returns a documented response.
- [ ] Unit and application-context tests pass.
- [ ] Formatting/static checks run locally with one documented command.



### TW-003 — Bootstrap React TypeScript client

**Acceptance criteria**

- [ ] React TypeScript project exists under `apps/web`.
- [ ] App displays API health state with loading, success, and error behavior.
- [ ] Component tests cover the three states.
- [ ] Formatting, linting, and type checks pass.



### TW-004 — Add PostgreSQL/PostGIS local environment

**Acceptance criteria**

- [ ] Docker Compose starts a pinned PostgreSQL/PostGIS image.
- [ ] Credentials come from documented environment variables.
- [ ] The API connects and applies a versioned migration.
- [ ] A clean-volume setup is verified from the runbook.



### TW-005 — Deliver the trip walking skeleton

**Acceptance criteria**

- [ ] A minimal trip has an ID, name, date range, time zone, and creator ID.
- [ ] API can create and retrieve a trip with validation and error responses.
- [ ] UI form creates a trip and renders the saved result.
- [ ] Unit, integration, API, and UI tests cover the happy path and key failures.
- [ ] Money, voting, invitations, and authentication remain out of scope.



### TW-006 — Establish CI and developer runbook

**Acceptance criteria**

- [ ] CI runs API and web checks on pull requests.
- [ ] The local runbook contains exact setup and verification commands.
- [ ] A clean checkout can be built using only documented prerequisites.



### TW-007 — Sprint demo and retrospective

**Acceptance criteria**

- [ ] Demonstrate creating and retrieving one trip.
- [ ] Save test/CI evidence in the sprint check-in.
- [ ] Record keep/stop/try and measured hours.
- [ ] Update Sprint 1 scope based on actual velocity.



## Execution order

`TW-001 -> (TW-002 + TW-004) -> TW-003 -> TW-005 -> TW-006 -> TW-007`

Only one ticket is actively implemented at a time; the dependency notation
describes sequencing, not a reason to multitask.