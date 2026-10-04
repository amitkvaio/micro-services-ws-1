# Spring Cloud Config Server - Local Git Repository

This learning chapter is part of the micro-services-ws-1 workspace.

## Agenda

- [Problem we will solve](#problem-we-will-solve)
- [What you will learn](#what-you-will-learn)
- [How to run](#how-to-run)
- [Example](#example)
- [Key points or common mistakes](#key-points-or-common-mistakes)
- [Chapter summary and next step](#chapter-summary-and-next-step)
- [Common interview questions](#common-interview-questions-and-short-answers)
- [Existing content preserved](#existing-content-preserved)

## Problem We Will Solve

This chapter solves the problem of keeping configuration outside the service by reading config from a local Git repository.

## What You Will Learn

- How Spring Cloud Config Server works
- How @EnableConfigServer enables config serving
- How services can read environment-specific config

## How To Run

- Prepare local config repository at D:/git/msconfig
- Run from 01-Spring-Cloud-Config-server: mvn spring-boot:run
- Open http://localhost:8888/centralized/default or /dev or /prod

## Example

`	ext
GET http://localhost:8888/centralized/dev
`

Use the response to verify the service starts correctly and the configured endpoint is reachable.

## Key Points Or Common Mistakes

- Keep supporting services running when a chapter depends on them.
- Check the configured port before opening the URL.
- Do not change code while testing documentation examples unless the chapter asks for it.

## Chapter Summary And Next Step

This chapter adds one small step in the microservices learning path.
Continue with [02-Cloud-Config-server](../02-Cloud-Config-server/README.md), which solves the next problem in the learning path.

## Common Interview Questions And Short Answers

**Q1. What is Spring Cloud Config Server?**  
It is a central server for externalized application configuration.

**Q2. Why use a Git repository for config?**  
Git gives versioning, history, and environment-specific files.

**Q3. What annotation enables Config Server?**  
@EnableConfigServer enables it.

**Q4. Which port is used?**  
The config server runs on port 8888.

**Q5. What is default-label?**  
It tells Config Server which Git branch or label to read.

**Q6. What problem does this solve?**  
It avoids copying config into every microservice.

## Existing Content Preserved

The section below keeps the original README notes from this project so no existing explanation, command, example, or technical detail is lost.

# **About Spring cloud Config Server**
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-server</artifactId>
</dependency>
```
---

# **1. What is Spring Cloud Config Server?**

* **Purpose:**
  It is a centralized configuration management service for distributed systems (microservices).
* Instead of keeping application configurations (like database URLs, API keys, feature flags) **inside each microservice**, we store them in **one central place** (often in Git, SVN, or a local file system).
* All microservices then **fetch** their configuration from the Config Server at startup (and even at runtime, if we enable refresh).

---

# **2. Use Case**

### Scenario **without** Config Server:

* We have 5 microservices.
* Each one has its own `application.properties` or `application.yml` file.
* If we need to update a database password, we must:

  * Open each service
  * Update the password
  * Rebuild & redeploy all services
    â†’ **This is time-consuming and error-prone.**

## **Scenario with Config Server:**

* We keep configuration in a **central Git repo** (e.g., `config-repo`).
* Example:

  * `service-a.properties`
  * `service-b.properties`
  * `application.properties` (common configs for all services)
* Config Server pulls the configuration from Git and provides it over HTTP.
* All microservices read their configuration from the Config Server.
  â†’ **We update config in Git â†’ All services can refresh without redeploying code.**

---

# **Spring Cloud Config Server with Local Git Repository**

---
1. Add the dependency in our project (`spring-cloud-config-server`).
2. Use annotation **`@EnableConfigServer`** in our Spring Boot main class.

   * This enables our project as a Config Server.

---

# **Why Do We Need Config Server?**

* In **microservices**, each service usually has its own configuration (URLs, DB settings, limits, etc.).
* Instead of keeping configs separately, we can **store all configs in one centralized Git repository**.
* The Config Server then **exposes these configs** to all microservices.
* Advantage:

  * Centralized management.
  * Easy updates.
  * Environment-specific configs in one place.

---

# **application.properties**

```properties
spring.application.name=spring-cloud-config-server
server.port=8888

#Reading from the local git repository
spring.cloud.config.server.git.uri=file:///D:/git/msconfig
spring.cloud.config.server.git.default-label=main
```
---

# **Best Practice**

* If we donâ€™t want default properties, **donâ€™t create `centralized.properties`**.
* Instead, just use environment-specific files like:

  * `centralized-dev.properties`
  * `centralized-prod.properties`

---

# **URLs and What They Return**

| URL                    | Config Files Used                                        |
| ---------------------- | -------------------------------------------------------- |
| `/centralized/default` | Only `centralized.properties`                            |
| `/centralized/dev`     | `centralized.properties` + `centralized-dev.properties`  |
| `/centralized/prod`    | `centralized.properties` + `centralized-prod.properties` |

---

# **Why URLs Work Without a Controller?**

* We donâ€™t need to manually write a REST Controller.
* Spring Cloud Config Server provides an **in-built controller** when we add the dependency.
* This controller exposes config files automatically using this format:

  ```
  http://<host>:<port>/{application}/{profile}
  ```
* Example:

  * `http://localhost:8888/centralized/default`
  * `http://localhost:8888/centralized/dev`
  * `http://localhost:8888/centralized/prod`

---

# **In short:**

* Config Server + Git Repo = Centralized Config Management.
* Microservices can load their configs dynamically without needing separate property files in each service.
---

# **URL**
```
# http://localhost:8888/centralized/default  
# http://localhost:8888/centralized/dev  
# http://localhost:8888/centralized/prod  
```
