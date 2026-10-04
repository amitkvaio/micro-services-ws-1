# Microservice Config Client

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

This chapter solves the problem of a microservice reading values from Config Server instead of only local files.

## What You Will Learn

- How a client connects to Config Server
- How active profiles affect loaded config
- How to expose configured values through REST APIs

## How To Run

- Start a Config Server on port 8888
- Run from 03-MS-Cloud-config-client: mvn spring-boot:run
- Open http://localhost:2021/reading-from-property-file-limits

## Example

`	ext
GET http://localhost:2021/reading-from-property-file-limits
`

Use the response to verify the service starts correctly and the configured endpoint is reachable.

## Key Points Or Common Mistakes

- Keep supporting services running when a chapter depends on them.
- Check the configured port before opening the URL.
- Do not change code while testing documentation examples unless the chapter asks for it.

## Chapter Summary And Next Step

This chapter adds one small step in the microservices learning path.
Continue with [04-Currency-exchange-service-hard-coded-values](../04-Currency-exchange-service-hard-coded-values/README.md), which solves the next problem in the learning path.

## Common Interview Questions And Short Answers

**Q1. What is a Config Client?**  
A service that reads external configuration from Config Server.

**Q2. Which property points to Config Server?**  
spring.cloud.config.uri points to the config server.

**Q3. What does spring.profiles.active=dev do?**  
It loads the dev profile configuration.

**Q4. Which endpoint reads configured limits?**  
/reading-from-property-file-limits.

**Q5. Why is bootstrap.properties used?**  
It loads config client settings early in startup.

**Q6. What happens if Config Server is down?**  
The client may fail or use local/default config depending on setup.

## Existing Content Preserved

The section below keeps the original README notes from this project so no existing explanation, command, example, or technical detail is lost.

# **Objective**

* Read values from a **centralized configuration file/property file**.
* Before reading values, the **Spring Cloud Config Server must be started**, otherwise the application will not start.

```xml
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-config</artifactId>
</dependency>
```
---

# **spring-cloud-starter-config Dependency**

# **What It Is**

* It is a **Spring Boot starter** that allows our application to connect with a **Spring Cloud Config Server**.
* It helps in **centralized configuration management**.

---

# **Why We Use It (Use Cases)**

1. **Centralized Configuration**

   * Instead of keeping `application.properties` or `application.yml` inside every microservice,
     we keep them in a **Git repository**.
   * Example:

     * `limits-service-dev.properties`
     * `limits-service-prod.properties`

2. **Dynamic Updates with @RefreshScope**

   * Properties can be changed **without restarting the application**.
   * Example:
     Change `limit-service.minimum` in Git â†’ Refresh the client â†’ New value is applied instantly.

3. **Environment-Specific Config**

   * Easily manage configs for **dev, test, staging, prod** from one place.
   * Example:

     * For **dev**: `limit-service.minimum=5`
     * For **prod**: `limit-service.minimum=100`

4. **Consistency Across Microservices**

   * Multiple microservices can share common properties (like DB connection, API keys).
   * Avoids **duplicate configs** in each service.

5. **Secure & Scalable**

   * Supports **encrypted values** (like passwords, secrets).
   * Scales well when we have **many microservices**.

---

# **How It Works**

* Add the dependency:

  ```xml
  <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-config</artifactId>
  </dependency>
  ```

* In `application.properties`:

  ```properties
  spring.application.name=limits-service
  spring.cloud.config.uri=http://localhost:8888
  ```

* Now, the application will **fetch its config** from the config server running on port **8888**.
---

```properties
spring.application.name=centralized
server.port=2021
spring.cloud.config.uri=http://localhost:8888
spring.profiles.active=prod
```

# **spring-cloud-starter-config**

* Connects the client service to the config server.
* Uses property:

  ```properties
  spring.cloud.config.uri=http://localhost:8888
  ```
* This tells the client where the config server is running.

---

# **spring.application.name**

* Example:

  ```properties
  spring.application.name=centralized
  ```
* The config server uses this name to find the right file, like:

  * `centralized-prod.properties`
  * `centralized-prod.yml`

---

# **spring.profiles.active**

* Example:

  ```properties
  spring.profiles.active=prod
  ```
* Activates the **prod profile**.
* Config Server will fetch:

  * `centralized-prod.properties`
  * OR `centralized-prod.yml`.

---

# **spring.cloud.config.server.git.default-label**

* Example:

  ```properties
  spring.cloud.config.server.git.default-label=main
  ```
* Tells the config server to use the **main branch** of the Git repository.


---

# **@RefreshScope Annotation**

* `@RefreshScope` is used to reload properties from the Config Server without restarting the application.
* It is useful when **new properties are added** in the config file.
---

# **Steps to Run**

1. Start **Spring Cloud Config Server** â†’ Example: `02-Cloud-Config-server`.
	> It will read the properties file from the git hub repository.
	> https://github.com/amitkvaio/msconfig	
2. Start **Config Client Service** â†’ Example: `03-MS-Cloud-config-client`.
3. Access the **3rd URL** to read values from the centralized location.

---

# **URLs to Access**
* `http://localhost:2021/hard-coded-limits`
* `http://localhost:2021/reading-from-property-file`
* `http://localhost:2021/reading-from-property-file-limits`
---

