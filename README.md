# Football Manager REST API (Spring Boot)

University coursework, 2019. A REST API for managing football teams and players, with a Thymeleaf-rendered admin UI. Runnable end-to-end via Docker Compose (Spring Boot + PostgreSQL).

> **Status:** archived as a learning artifact, kept ak a reference for Spring Boot + JPA + Thymeleaf patterns I worked through during my MSc.

## Stack

| Layer | Technology |
|---|---|
| Framework | Spring Boot 2.1 |
| Persistence | Spring Data JPA · PostgreSQL |
| View | Thymeleaf (server-rendered admin) |
| Build | Maven |
| Container | Docker · Docker Compose |

## Domain

REST endpoints for football teams and players — CRUD operations backed by PostgreSQL, plus a Thymeleaf admin page.

## Run

```bash
mvn package
docker-compose up
```

Spring Boot starts on `:8080`; PostgreSQL is provisioned as a sidecar container by `docker-compose.yml`.

## Architecture

```
client ──HTTP──▶ Spring Boot REST API ──JPA──▶ PostgreSQL
                       │
                       └──▶ Thymeleaf admin UI
```

## What I learned from this project

- Designing REST endpoints with Spring Web (`@RestController`, request mapping)
- JPA entity relationships and Spring Data repository abstractions
- Combining a REST API with a server-rendered admin (Thymeleaf alongside JSON endpoints)
- Multi-container local dev with `docker-compose` (app + Postgres on the same network)
- Maven build → Docker image pipeline
