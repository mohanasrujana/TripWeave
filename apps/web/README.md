# TripWeave web client

React and TypeScript client built with Vite.

## Install

From the repository root:

```bash
npm install --prefix apps/web
```

## Run locally

Start the Spring Boot API on port 8080, then start the client:

```bash
npm run dev --prefix apps/web
```

Open the URL printed by Vite. During development, Vite proxies `/api` requests
to `http://localhost:8080`.

## Verify

```bash
npm run check --prefix apps/web
```

This checks formatting, linting, component tests, TypeScript, and the production
build.
