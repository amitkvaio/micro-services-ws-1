# Limit Service

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

This chapter solves the first problem of reading limit values from application configuration and exposing them through a REST API.

## What You Will Learn

- How a simple Spring Boot REST service works
- How configuration values are bound with @ConfigurationProperties
- How to expose values using /limits

## How To Run

- Run from 00-Limit-Services: mvn spring-boot:run
- Open http://localhost:2025/limits
- Optional jar run after packaging: java -jar target/00-Limit-Services-0.0.1-SNAPSHOT.jar --server.port=8002

## Example

`	ext
GET http://localhost:2025/limits
`

Use the response to verify the service starts correctly and the configured endpoint is reachable.

## Key Points Or Common Mistakes

- Keep supporting services running when a chapter depends on them.
- Check the configured port before opening the URL.
- Do not change code while testing documentation examples unless the chapter asks for it.

## Chapter Summary And Next Step

This chapter adds one small step in the microservices learning path.
Continue with [01-Spring-Cloud-Config-server](../01-Spring-Cloud-Config-server/README.md), which solves the next problem in the learning path.

## Common Interview Questions And Short Answers

**Q1. What is the purpose of Limit Service?**  
It exposes minimum and maximum values from configuration.

**Q2. What does @ConfigurationProperties do?**  
It binds external properties to a Java bean.

**Q3. Why start with this service?**  
It gives a simple baseline before introducing centralized config.

**Q4. Which port is used here?**  
The service runs on port 2025.

**Q5. What endpoint is exposed?**  
The endpoint is /limits.

**Q6. Can the port be changed?**  
Yes, pass --server.port when running the jar.

## Existing Content Preserved

The section below keeps the original README notes from this project so no existing explanation, command, example, or technical detail is lost.

# Limit-Services
#### **@ConfigurationProperties("limits-service")**
> It tells Spring Boot: Bind all the properties starting with limits-service. 
		from configuration files to the fields in this class.
		
# **Access URL**
>http://localhost:2025/limits
---

```xml
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-bootstrap</artifactId>
</dependency>
```
---
### **What is `spring-cloud-starter-bootstrap`?**

* It is a **Spring Cloud dependency**.
* It helps our Spring Boot application **load configuration settings early**, before the main application context starts.
* These settings are usually kept in a **bootstrap context** (separate from the main application context).
---

### **Why do we need it?**

Normally, Spring Boot reads configuration from:

* `application.properties` / `application.yml`

* In **microservices with Spring Cloud**, sometimes we want to load configuration from an **external source** (like **Spring Cloud Config Server**, Consul, or Vault).

* For this, the app must fetch configuration **before** creating beans, otherwise, wrong or missing values may be used.

`spring-cloud-starter-bootstrap` ensures:

* External configs are loaded **first** (bootstrap phase).
* Then the main application context starts with correct settings.

---

### **When should we use it?**

Use this dependency if:

1. We are using **Spring Cloud Config Server** (to fetch configs from Git, SVN, etc.).
2. We are using **HashiCorp Vault** (to fetch secrets/credentials securely).
3. We need to **separate bootstrap configs** (like service name, discovery settings, config server URL) from normal application configs.

---

### **Example Scenario**

Suppose we have 10 microservices, and instead of keeping separate `application.yml` in each one.

* We keep all configs in a **Spring Cloud Config Server (Git repo)**.

* Without `spring-cloud-starter-bootstrap`:
  Our microservice might start **before** fetching configs â†’ leading to errors.

* With `spring-cloud-starter-bootstrap`:
  Microservice **first connects to Config Server** â†’ loads configs â†’ then starts normally.

---

## **In short:**
`spring-cloud-starter-bootstrap` is useful when we want our microservice to load configs (from Config Server, Vault, or Consul) **before** anything else starts.
It ensures our service always starts with the **right configuration**.

---

# **00 Limit-Services**
## **Objective**

**We are reading the values of below property from the bootstrap.properties file.**

---
```
limits-service.maximum=2000
limits-service.minimum=1000
limits-service.name=Default-Properties
```
https://github.com/amitkvaio/micro-services-ws-1/blob/main/00-Limit-Services/src/main/resources/bootstrap.properties  


## **Check this java classes for more details.**

https://github.com/amitkvaio/micro-services-ws-1/blob/main/00-Limit-Services/src/main/java/com/amit/microservices/limitsservice/Configuration.java  

https://github.com/amitkvaio/micro-services-ws-1/blob/main/00-Limit-Services/src/main/java/com/amit/microservices/limitsservice/LimitsConfigurationController.java  

https://github.com/amitkvaio/micro-services-ws-1/blob/main/00-Limit-Services/src/main/java/com/amit/microservices/limitsservice/LimitsServiceSpringBootApplication.java  


# **Access URL**
```
http://localhost:2025/limits
```
# **Running using command line.**
```
mvn clean package -DskipTests
java -jar target/myapp.jar --server.port=8001
```
