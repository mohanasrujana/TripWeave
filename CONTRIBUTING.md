# Engineering workflow

## Ticket lifecycle

`Backlog -> Ready -> In Progress -> Review -> Done`

- A ticket is **Ready** only when its outcome and acceptance criteria are clear.
- Only one primary ticket should be **In Progress** at a time.
- **Review** means the implementation is complete and the engineer is checking
  the diff, tests, behavior, and documentation as if reviewing a teammate's PR.
- **Done** requires every item in the definition of done.

## Branches and commits

- Branch format: `codex/<ticket-id>-short-description`
- Commit format: `<type>(<area>): <imperative summary>`
- Types: `feat`, `fix`, `test`, `docs`, `refactor`, `build`, `chore`
- Prefer commits that represent one coherent, working change.

Example:

```text
feat(trips): create trip aggregate and persistence model
```

## Pull-request discipline for a solo engineer

Before merging, step away from the implementation briefly and review the diff
from the PR template. Confirm scope, failure paths, tests, security implications,
database changes, observability, and documentation. Do not merge red CI.

## Definition of done

A ticket is done when:

- All acceptance criteria pass.
- Relevant automated tests exist and pass.
- Formatting, linting, and static checks pass.
- Errors are handled deliberately; no silent failures remain.
- Logs do not expose secrets or personal data.
- API/schema changes are documented and backward compatibility considered.
- User-facing behavior has been exercised manually when applicable.
- The ticket, status page, and related docs reflect reality.
- No known blocker is hidden; follow-up work has a ticket.

## Daily check-in

At the start of a session, add a dated entry under `project/check-ins/` using
`project/check-ins/TEMPLATE.md`. At the end, record evidence: commit, test output,
screenshot, or a concise explanation of what changed.

