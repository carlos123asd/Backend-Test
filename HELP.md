# Backend Similar Products API

Spring Boot reactive application that exposes a REST API to retrieve detailed information about similar products.

The application consumes two external APIs:

- Similar product IDs API
- Product detail API

and aggregates the results into a single response optimized for concurrency and resilience.

---

# Technologies

- Java 21
- Spring Boot 3
- Spring WebFlux
- WebClient
- Reactor (Mono / Flux)
- Maven
- Docker
- k6
- Grafana
- InfluxDB

---

# Architecture

The application follows a layered architecture:

```text
├── client
│   └── External API communication layer
│
├── commons.messages
│   └── Centralized application messages/constants
│
├── config
│   └── Application and WebClient configuration
│
├── controller
│   └── REST API endpoints
│
├── dto
│   └── Response DTO models
│
├── service.getProductsSimilars
│   └── Business logic and orchestration
│
└── BacktestApplication
    └── Spring Boot main application