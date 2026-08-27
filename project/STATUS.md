# Project status

- Updated: August 27, 2026
- Phase: 0 — Foundation
- Sprint: 0 — Walking skeleton
- Sprint health: Not started
- Current ticket: TW-001
- Next checkpoint: Kickoff review, August 28, 2026

## Completed

- Defined the product vision and Phase 1 boundary.
- Selected the starting architecture and planned evolution.
- Created roadmap, backlog, sprint, schedule, and engineering workflow.

## In progress

- TW-001 — Confirm product and engineering constraints.

## Blockers

- None.

## Open decisions for kickoff

1. Confirm or replace the assumed 15 focused hours per week.

## Decisions made

- Java build: Maven.
- Web package manager: npm.
- Authentication: self-hosted initially, behind a replaceable application
  boundary. The walking skeleton will not implement authentication yet.

## Manager assessment

The main early risk is over-engineering before validating the core user journey.
Phase 1 intentionally excludes CRDTs, Kafka, Kubernetes, live booking, and AI.
The immediate standard is a boring, tested, reproducible walking skeleton.
