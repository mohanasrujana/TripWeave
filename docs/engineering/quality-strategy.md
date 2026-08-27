# Quality strategy

## Test pyramid

- Unit tests for domain rules and algorithms.
- Integration tests for persistence, migrations, security, and module boundaries.
- API contract tests for externally visible behavior.
- A small set of end-to-end tests for critical user journeys.

## Required checks

The build will eventually enforce formatting, linting, static analysis, unit and
integration tests, migration validation, dependency scanning, and a production
build. Sprint 0 selects the tools and wires the first checks into CI.

## Risk-based review

Changes involving authentication, authorization, money, time zones, migrations,
concurrency, or retries receive explicit negative-path tests and a written review
note in the pull request.

