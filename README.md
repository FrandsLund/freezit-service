# freezit-service

**freezit-service** is a backend REST API service for managing household freezer inventories. It lets users track what food items are stored across one or more freezers, which shelf each item lives on, and how much of each item remains.

## What it does

A user can own one or more **Freezers**. Each Freezer is divided into a configurable number of **Shelves**. Each Shelf holds any number of **Freezer Items** — food products described by a name, an optional description, a date-added timestamp, and a current quantity.

The service exposes the following capabilities via a REST API:

| Operation | Description |
|-----------|-------------|
| **Create a freezer** | Create a new named freezer for a user, specifying how many shelves it has |
| **Get a freezer** | Retrieve a freezer (and all its contents) by its ID, or by user ID + freezer name |
| **Add an item** | Add a named food item to a specific shelf, including an initial quantity |
| **Increase quantity** | Increase the stored quantity of an existing item (e.g. after restocking) |

Domain events are emitted for every significant state change (freezer created, item added, quantity increased), making the service event-ready for future integrations.

## Architecture

The project is structured using **Hexagonal Architecture** (Ports & Adapters), split into four Maven modules:

```
domain/          – Core business entities, value objects, domain events and exceptions
application/     – Use-case interfaces (inbound ports), outbound ports, and service implementations
infrastructure/  – REST adapter (inbound), in-memory and JPA/MySQL persistence adapters (outbound)
bootstrap/       – Quarkus application entry-point and wiring
```

The domain and application layers have **no framework dependencies**, keeping the business logic clean and independently testable.

## Tech stack

- **Java 21** with [Quarkus 3](https://quarkus.io/) as the runtime framework
- **Jakarta REST** for the HTTP API
- **Hibernate ORM / Panache** for JPA persistence
- **MySQL** (via Docker Compose) for production persistence; an **in-memory** adapter is also provided for tests

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/freezit-service-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Provided Code

### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
