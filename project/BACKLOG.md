# Product backlog

Priorities are directional until sprint planning. Estimates are initial focused
engineering hours, not deadlines.

| ID | Priority | Item | Estimate | Target phase |
|---|---|---|---:|---|
| TW-101 | P0 | Authentication and session lifecycle | 12h | Phase 1 |
| TW-102 | P0 | Trip membership and RBAC | 12h | Phase 1 |
| TW-103 | P0 | Invitation lifecycle | 10h | Phase 1 |
| TW-104 | P0 | Participant availability entry | 10h | Phase 1 |
| TW-105 | P0 | Availability overlap calculation | 12h | Phase 1 |
| TW-106 | P0 | Destination and activity proposals | 10h | Phase 1 |
| TW-107 | P0 | Voting lifecycle and deadline | 14h | Phase 1 |
| TW-108 | P0 | Itinerary activity CRUD and ordering | 14h | Phase 1 |
| TW-109 | P0 | Expense ledger and balances | 18h | Phase 1 |
| TW-201 | P1 | WebSocket trip updates | 14h | Phase 2 |
| TW-202 | P1 | Client operation log in IndexedDB | 16h | Phase 2 |
| TW-203 | P1 | Concurrent itinerary merge model | 24h | Phase 2 |
| TW-204 | P1 | Version history and restore | 16h | Phase 2 |
| TW-301 | P1 | Time-zone-aware recurrence and overlap | 18h | Phase 3 |
| TW-302 | P1 | Ranked-choice and veto policies | 18h | Phase 3 |
| TW-303 | P1 | Constraint-based itinerary scheduler | 30h | Phase 3 |
| TW-304 | P1 | PostGIS route optimization | 24h | Phase 3 |
| TW-305 | P1 | Minimum-transfer expense settlement | 16h | Phase 3 |
| TW-401 | P2 | Transactional outbox and Kafka | 20h | Phase 4 |
| TW-402 | P2 | Idempotent notification worker | 18h | Phase 4 |
| TW-403 | P2 | Reservation workflow state machine | 24h | Phase 4 |
| TW-404 | P2 | Retry, compensation, and failure tests | 20h | Phase 4 |
| TW-501 | P2 | OpenTelemetry and service dashboards | 18h | Phase 5 |
| TW-502 | P2 | Load and failure-injection suite | 20h | Phase 5 |
| TW-503 | P2 | CI/CD and staged deployment | 20h | Phase 5 |
| TW-504 | P2 | Kubernetes deployment and runbooks | 20h | Phase 5 |

## Backlog policy

- P0 supports the next usable release; P1 differentiates the product; P2 adds
  scale or operational maturity.
- A backlog item needs user value, acceptance criteria, dependencies, and a risk
  note before entering a sprint.
- Estimates are recalibrated after every sprint using actual focused hours.

