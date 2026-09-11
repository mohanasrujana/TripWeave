# Local development runbook

## Prerequisites

Install Git, Eclipse Temurin JDK 21, Node.js 26.8.1 (also recorded in
`.node-version`), npm 11.19.0, and Docker Desktop with Docker Compose v2 or newer
supporting `up --wait`. Start or resume Docker Desktop before running checks.
On Apple Silicon, Docker must support the pinned `linux/amd64` PostGIS image.
Internet access is required for the first dependency and image downloads.

Maven does not need a separate installation: `services/api/mvnw` downloads
Maven 3.9.16 as pinned in `.mvn/wrapper/maven-wrapper.properties`.

Check the installed tools:

```bash
git --version
java -version
node --version
npm --version
docker compose version
docker info
```

Maven uses `JAVA_HOME` when set, which can differ from `java` on your PATH.
Set it to your JDK 21 installation. On macOS:

```bash
export JAVA_HOME="$(/usr/libexec/java_home -v 21)"
export PATH="$JAVA_HOME/bin:$PATH"
```

On Linux, set `JAVA_HOME` to the installed JDK 21 directory and prepend its
`bin` directory to `PATH`. Apply this in each API terminal or your shell setup.
After cloning, `./services/api/mvnw --version` must report Java 21.

If necessary, select npm after installing the documented Node version:

```bash
npm install --global npm@11.19.0
```

## Set up a checkout

The commands below use Bash or Zsh. Clone once, then run setup from the root:

```bash
git clone https://github.com/mohanasrujana/TripWeave.git
cd TripWeave
cp .env.example .env
```

Edit `.env` to choose a local database password before first startup. Keep it
untracked. `DB_HOST=localhost` connects the host API to Docker; `DB_PORT=5432`
is the host port. Choose another free port if it is already occupied.
Changing credentials after a volume is initialized does not change existing
PostgreSQL users; keep the original credentials or use a new isolated volume.

Start PostgreSQL/PostGIS and wait for readiness:

```bash
docker compose up -d --wait --wait-timeout 120 database
docker compose ps database
```

Expect `healthy`. Load the database settings into the current terminal:

```bash
set -a
source .env
set +a
```

Install the locked web dependencies:

```bash
(cd apps/web && npm ci)
```

## Verify before committing

From the repository root, with `.env` loaded and the database healthy:

```bash
(cd services/api && ./mvnw --batch-mode --no-transfer-progress verify)
(cd apps/web && npm run check)
git diff --check
```

Expect Maven `BUILD SUCCESS`, 14 passing tests, Spotless, and Checkstyle.
Expect web formatting, lint, 5 Vitest tests, TypeScript, and the Vite build to
pass. These counts describe the TW-006 baseline; update them when adding tests.
The API checks apply Flyway migrations to the configured database. The web
checks use mocked API responses and do not require a running API server.

Confirm both migrations succeeded:

```bash
docker compose exec -T database sh -c 'psql -U "$POSTGRES_USER" -d "$POSTGRES_DB" -c "SELECT version, description, success FROM flyway_schema_history ORDER BY installed_rank;"'
```

Expect versions `1` and `2`, each with success `t`.

## Run the application

In terminal 1, from the repository root:

```bash
set -a
source .env
set +a
cd services/api
./mvnw spring-boot:run
```

In terminal 2, from the repository root:

```bash
cd apps/web
npm run dev -- --port 5173 --strictPort
```

Open http://localhost:5173. Vite proxies `/api` requests to the API on port
8080. The API health indicator should succeed. Fill in the trip form with a
name, ordered dates, an IANA time zone such as `America/New_York`, and a UUID
creator ID such as `10000000-0000-0000-0000-000000000001`. Submit and expect
the saved trip, including its generated ID, to appear.

In another terminal, check health and retrieve the saved ID:

```bash
curl --fail-with-body http://localhost:8080/api/health
# Replace TRIP_ID with the ID rendered by the form.
curl --fail-with-body http://localhost:8080/api/trips/TRIP_ID
```

Stop the API and web processes with Ctrl-C. From the repository root, stop
Docker resources while retaining database data:

```bash
docker compose down
```

## Verify a clean checkout without touching development data

Clone into a new directory and repeat the prerequisite and setup steps above.
Before starting the database, set an unused port (for example `55432`) in that
checkout's `.env`, then run the following in the verification terminal:

```bash
export COMPOSE_PROJECT_NAME=tripweave-clean-check
```

This gives the checkout its own containers and volume. Run the database-start,
environment-loading, dependency-installation, and verification commands above.
A fresh volume must successfully apply migrations `1` and `2`; no copied `.env`,
`node_modules`, or `target` directory is needed from your development checkout.
Normal package-manager download caches may be reused.

After verification, remove only this disposable database from the same
terminal and checkout. This deletes its data:

```bash
docker compose -p tripweave-clean-check down --volumes
unset COMPOSE_PROJECT_NAME
```

## CI

`.github/workflows/ci.yml` runs separate API and web jobs for pushes to `master`
and manual dispatch. API checks use the same digest-pinned
PostGIS image and Maven Wrapper as local development. Web checks install from
the lockfile with the documented Node/npm versions. CI database credentials
are disposable job-local values; repository secrets are not required.

After pushing directly to `master`, check **Actions → CI** for
both jobs. Local verification does not prove a hosted Actions run succeeded.

## Troubleshooting

- Docker connection errors or tests stalled at `HikariPool` startup: start or
  resume Docker Desktop, then rerun the database-start command.
- Database readiness timeout: inspect `docker compose logs database`; check
  the configured port and credentials. Do not delete your development volume
  to troubleshoot a disposable verification checkout.
- Missing `DB_*` variables: load `.env` in the same terminal as Maven.
- Java compilation errors: verify `java -version` and `JAVA_HOME` select JDK 21.
- Browser API failures: confirm the API runs on 8080 and open the Vite URL,
  rather than the built HTML file directly.
- Web dependency errors: check Node/npm versions and rerun `npm ci`.
