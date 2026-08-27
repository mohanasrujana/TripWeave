# Work schedule

## Capacity assumption

This plan reserves **15 focused hours per week**. Change the time blocks if your
real availability differs, but keep the review and demo cadence.

| Day | Time | Purpose |
|---|---:|---|
| Monday | 7:00–9:00 PM | Plan the week's outcome; begin the primary ticket |
| Tuesday | 7:00–9:00 PM | Implementation |
| Wednesday | 7:00–9:00 PM | Implementation and tests |
| Thursday | 7:00–9:00 PM | Integration, edge cases, documentation |
| Friday | 6:30–7:30 PM | Demo, status update, and retrospective |
| Saturday | 10:00 AM–2:00 PM | Deep-work block for the sprint goal |
| Sunday | 5:00–7:00 PM | Buffer, learning, or next-ticket refinement |

## Session protocol

### Start (5 minutes)

1. Open `project/SPRINT.md` and `project/STATUS.md`.
2. Create or update today's check-in.
3. State one concrete session outcome and its ticket ID.
4. Confirm the verification command or evidence needed to finish.

### Work

- Work on one primary ticket.
- Commit a coherent change before switching contexts.
- Record newly discovered work in the backlog; do not silently expand scope.

### End (10 minutes)

1. Run the relevant checks.
2. Record completed work and evidence.
3. Name the exact next action so the next session starts quickly.
4. Update risks or blockers while they are fresh.

## Management cadence

- **Monday planning:** commit to one weekly outcome, not a list of activities.
- **Daily check-in:** report `Done / Next / Blocked / Evidence`.
- **Friday demo:** show working software or a verifiable engineering artifact.
- **Friday retrospective:** one thing to keep, stop, and try.
- **Sprint review every second Friday:** accept or reject tickets against their
  criteria, update velocity, and re-plan from observed capacity.

## Accountability rules

- A missed session is rescheduled within 48 hours or capacity is formally reduced.
- A ticket that carries over twice must be split or re-estimated before more work.
- “Almost done” is not done; acceptance criteria and evidence decide status.
- Blockers lasting more than one session are escalated during the next check-in.
- Scope may shrink to protect the sprint goal; tests and correctness may not.

