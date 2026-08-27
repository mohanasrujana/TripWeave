# ADR 001: Build tools and initial authentication ownership

- Status: Accepted
- Date: 2026-08-27
- Owners: Satya

## Context

TripWeave needs predictable build tooling for a Java Spring Boot API and React
TypeScript client. It will also need authentication, but the initial walking
skeleton should remain focused on proving the UI-to-database development loop.

## Decision

- Use Maven for the Java 21 Spring Boot build.
- Use npm for the React TypeScript workspace.
- Implement authentication in-house during Phase 1.
- Keep authentication behind an application-facing identity boundary so a
  managed provider can be introduced later without coupling domain modules to a
  specific authentication implementation.
- Defer authentication implementation until after the Sprint 0 walking skeleton.

## Alternatives considered

- Gradle: capable and flexible, but Maven's explicit lifecycle and broad Spring
  conventions are sufficient for this project's current needs.
- pnpm or Yarn: useful for larger monorepos, but npm minimizes setup and already
  supports the planned workspace structure.
- Managed identity provider: reduces security-sensitive implementation work, but
  self-hosting provides deliberate experience with authentication and
  authorization. Migration remains possible through the identity boundary.

## Consequences

- Repository commands and CI will standardize on Maven Wrapper and npm.
- The team owns password storage, session/token security, account recovery,
  rate limiting, auditability, and dependency maintenance.
- Authentication scope must be kept narrow and based on established libraries;
  custom cryptography is prohibited.
- A later managed-provider migration should primarily affect the identity adapter,
  not trip, voting, itinerary, or expense-domain code.

## Validation

- Sprint 0 produces reproducible Maven and npm builds in local development and CI.
- Phase 1 security tests cover authentication and authorization failure paths.
- The architecture review confirms domain modules do not depend directly on the
  authentication mechanism.
