```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-server</artifactId>
</dependency>
```
---

## **1. What is Spring Cloud Config Server?**

* **Purpose:**
  It is a centralized configuration management service for distributed systems (microservices).
* Instead of keeping application configurations (like database URLs, API keys, feature flags) **inside each microservice**, you store them in **one central place** (often in Git, SVN, or a local file system).
* All microservices then **fetch** their configuration from the Config Server at startup (and even at runtime, if you enable refresh).

---

## **2. Use Case**

### Scenario **without** Config Server:

* You have 5 microservices.
* Each one has its own `application.properties` or `application.yml` file.
* If you need to update a database password, you must:

  * Open each service
  * Update the password
  * Rebuild & redeploy all services
    → **This is time-consuming and error-prone.**

### Scenario **with** Config Server:

* You keep configuration in a **central Git repo** (e.g., `config-repo`).
* Example:

  * `service-a.properties`
  * `service-b.properties`
  * `application.properties` (common configs for all services)
* Config Server pulls the configuration from Git and provides it over HTTP.
* All microservices read their configuration from the Config Server.
  → **You update config in Git → All services can refresh without redeploying code.**

---

# 📘 Spring Cloud Config Server with Local Git Repository

---

## 🔧 Setup

1. Add the dependency in your project (`spring-cloud-config-server`).
2. Use annotation **`@EnableConfigServer`** in your Spring Boot main class.

   * This enables your project as a Config Server.

---

## 📌 Why Do We Need Config Server?

* In **microservices**, each service usually has its own configuration (URLs, DB settings, limits, etc.).
* Instead of keeping configs separately, we can **store all configs in one centralized Git repository**.
* The Config Server then **exposes these configs** to all microservices.
* Advantage:

  * Centralized management.
  * Easy updates.
  * Environment-specific configs in one place.

---

## 🗂️ Example – Limits Service

```java
@ConfigurationProperties("limits-service")
```

* This tells Spring Boot:
  👉 "Take all properties starting with `limits-service` from the config files and bind them to this class."

---

## 🌐 Access URL for Limits Service

* Run the microservice and check:

  ```
  http://localhost:2025/limits
  ```

---

## ⚙️ Git Repository Setup

* Connect Config Server to a Git repository.

### Local Git Path

```properties
spring.cloud.config.server.git.uri=file:///D:/git/msconfig
```

### GitHub Path

```properties
spring.cloud.config.server.git.uri=https://github.com/amitkvaio/msconfig.git
```

### Use `main` branch

```properties
spring.cloud.config.server.git.default-label=main
```

---

## ❓ Why Do We See Both Default and Dev Properties?

When you access a URL like:

```
http://localhost:8888/centralized/dev
```

➡️ Spring Cloud Config Server merges properties in this order:

1. Loads `centralized.properties` (**default profile**).
2. Overrides with `centralized-dev.properties` (**dev profile**).

👉 **Higher profiles override lower ones.**

---

## 📂 Best Practice

* If you don’t want default properties, **don’t create `centralized.properties`**.
* Instead, just use environment-specific files like:

  * `centralized-dev.properties`
  * `centralized-prod.properties`

---

## 🌍 URLs and What They Return

| URL                    | Config Files Used                                        |
| ---------------------- | -------------------------------------------------------- |
| `/centralized/default` | Only `centralized.properties`                            |
| `/centralized/dev`     | `centralized.properties` + `centralized-dev.properties`  |
| `/centralized/prod`    | `centralized.properties` + `centralized-prod.properties` |

---

## ⚡ Why URLs Work Without a Controller?

* We don’t need to manually write a REST Controller.
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

✅ **In short:**

* Config Server + Git Repo = Centralized Config Management.
* Microservices can load their configs dynamically without needing separate property files in each service.

---

# **URL**
```
# http://localhost:8888/centralized/default  
# http://localhost:8888/centralized/dev  
# http://localhost:8888/centralized/prod  
```