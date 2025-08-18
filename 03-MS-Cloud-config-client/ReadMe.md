## **Objective**

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

## **What It Is**

* It is a **Spring Boot starter** that allows your application to connect with a **Spring Cloud Config Server**.
* It helps in **centralized configuration management**.

---

## **Why We Use It (Use Cases)**

1. **Centralized Configuration**

   * Instead of keeping `application.properties` or `application.yml` inside every microservice,
     you keep them in a **Git repository**.
   * Example:

     * `limits-service-dev.properties`
     * `limits-service-prod.properties`

2. **Dynamic Updates with @RefreshScope**

   * Properties can be changed **without restarting the application**.
   * Example:
     Change `limit-service.minimum` in Git → Refresh the client → New value is applied instantly.

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
   * Scales well when you have **many microservices**.

---

## **How It Works**

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

Do you want me to **add this explanation** to your existing **Spring Cloud Config notes** and prepare a combined **study document (.docx/.pdf)** for you?



```properties
spring.application.name=centralized
server.port=2021
spring.cloud.config.uri=http://localhost:8888
spring.profiles.active=prod
```

### **spring-cloud-starter-config**

* Connects the client service to the config server.
* Uses property:

  ```properties
  spring.cloud.config.uri=http://localhost:8888
  ```
* This tells the client where the config server is running.

---

### **spring.application.name**

* Example:

  ```properties
  spring.application.name=centralized
  ```
* The config server uses this name to find the right file, like:

  * `centralized-prod.properties`
  * `centralized-prod.yml`

---

### **spring.profiles.active**

* Example:

  ```properties
  spring.profiles.active=prod
  ```
* Activates the **prod profile**.
* Config Server will fetch:

  * `centralized-prod.properties`
  * OR `centralized-prod.yml`.

---

### **spring.cloud.config.server.git.default-label**

* Example:

  ```properties
  spring.cloud.config.server.git.default-label=main
  ```
* Tells the config server to use the **main branch** of the Git repository.


---

## **@RefreshScope Annotation**

* `@RefreshScope` is used to reload properties from the Config Server without restarting the application.
* It is useful when **new properties are added** in the config file.
---

## **Steps to Run**

1. Start **Spring Cloud Config Server** → Example: `02-Cloud-Config-server`.
	> It will read the properties file from the git hub repository.
	> https://github.com/amitkvaio/msconfig	
2. Start **Config Client Service** → Example: `03-MS-Cloud-config-client`.
3. Access the **3rd URL** to read values from the centralized location.

---

## **URLs to Access**
* `http://localhost:2021/hard-coded-limits`
* `http://localhost:2021/reading-from-property-file`
* `http://localhost:2021/reading-from-property-file-limits`
---
