# TripWeave

TripWeave is a collaborative group-trip planner designed as a production-grade
software engineering project. Groups will be able to coordinate availability,
vote on destinations and activities, build an itinerary, and track shared
expenses. Later phases add offline-first collaboration, optimization,
geospatial routing, and durable event-driven workflows.

## Current status

**Phase 0 — Foundation.** The repository and engineering process are ready;
application implementation has not started.

- [Product vision](docs/product/vision.md)
- [System architecture](docs/architecture/system-design.md)
- [Roadmap](project/ROADMAP.md)
- [Work schedule](project/SCHEDULE.md)
- [Current sprint](project/SPRINT.md)
- [Backlog](project/BACKLOG.md)
- [Project status](project/STATUS.md)

## Planned stack

- React and TypeScript web client
- Java 21 and Spring Boot modular backend
- PostgreSQL with PostGIS
- Redis and Kafka when their use cases enter scope
- Docker Compose locally; Kubernetes only after the core product works
- OpenTelemetry for production observability

The project deliberately starts as a modular monolith. Services will be split
only when operational evidence justifies the added complexity.

## Repository layout

```text
apps/web/                 React + TypeScript client
services/api/             Spring Boot API and domain modules
services/worker/          Asynchronous workers (introduced later)
packages/contracts/       Shared API/event contracts
packages/config/          Shared development configuration
infrastructure/docker/    Local development infrastructure
infrastructure/kubernetes/ Production deployment manifests (later)
docs/                     Product, architecture, ADRs, and runbooks
project/                  Roadmap, sprint, backlog, status, and check-ins
```

## Working agreement

1. Start work from a ticket with acceptance criteria.
2. Keep one primary ticket in progress at a time.
3. Deliver through small, reviewable commits and pull requests.
4. Tests and documentation are part of the work, not follow-up tasks.
5. Update `project/STATUS.md` after each work session.
6. End each week with a demo and retrospective—even if the demo is small.

See [CONTRIBUTING.md](CONTRIBUTING.md) for the complete workflow.

