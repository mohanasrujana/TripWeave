# Product vision

## Problem

Group trips are coordinated across chats, polls, calendars, spreadsheets, maps,
booking sites, and payment apps. Decisions become hard to trace, itinerary
changes overwrite one another, and one organizer becomes the bottleneck.

## Product promise

TripWeave gives a group one reliable place to decide **when to travel, where to
go, what to do, and who owes what**, while retaining a clear history of how the
plan changed.

## Initial users

Groups of 3–10 friends planning a multi-day leisure trip. The first version is
optimized for one organizer and invited participants, not travel agencies or
public trip discovery.

## Phase 1 user journey

1. An organizer creates a trip and invites participants.
2. Participants submit date availability and destination proposals.
3. The group votes before a deadline.
4. The organizer converts chosen ideas into a day-by-day itinerary.
5. Participants record shared expenses and see net balances.

## Phase 1 success criteria

- A new user can create a trip and invite a participant in under five minutes.
- Multiple participants can propose and vote without ambiguous final state.
- The itinerary has an auditable owner, dates, activities, and ordering.
- Expenses preserve exact amounts and produce correct balances.
- The core journey is covered by automated tests and works from a clean setup.

## Non-goals for Phase 1

- Booking or charging real money
- AI-generated travel recommendations
- Offline synchronization or CRDTs
- Kafka, Kubernetes, or independently deployed microservices
- Full route optimization

Those are roadmap items, not requirements for the first usable product.

