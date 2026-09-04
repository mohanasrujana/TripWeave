# Local development runbook

This runbook becomes executable during Sprint 0. The target developer experience
is:

1. Clone the repository.
2. Copy documented example environment files.
3. Start PostgreSQL/PostGIS with Docker Compose.
4. Run the API and web client with one documented command each.
5. Run all required checks before opening a pull request.

No secret values belong in this repository. Any prerequisite or non-obvious
recovery step discovered during development must be added here immediately.

## PostgreSQL/PostGIS

From the repository root, create the ignored local environment file:

```bash
cp .env.example .env
```

Start the database:

```bash
docker compose up -d database
```

Confirm that it is ready:

```bash
docker compose ps database
```

The database is ready when its status contains `healthy`.

Load the environment variables and verify the API:

```bash
set -a
source .env
set +a
cd services/api
./mvnw verify
cd ../..
```

Confirm that Flyway applied the migration:

```bash
docker compose exec database psql -U tripweave -d tripweave -c "SELECT installed_rank, version, description, success FROM flyway_schema_history ORDER BY installed_rank;"
```

Stop the database without deleting its data:

```bash
docker compose down
```

### Clean-volume verification

Warning: the following command permanently deletes the local TripWeave database
volume and all data stored in it. Do not use it for a database containing data
you need to retain.

From the repository root, delete the local containers and volume:

```bash
docker compose down --volumes
```

Recreate the database from scratch:

```bash
docker compose up -d database
```

Wait until `docker compose ps database` reports `healthy`, then load `.env` and
run `./mvnw verify` from `services/api`. Finally, query
`flyway_schema_history` as shown above and confirm migration version `1`
succeeded.
