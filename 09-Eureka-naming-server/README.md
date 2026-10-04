# Eureka Naming Server

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

This chapter solves the problem of service discovery by adding a registry where services can register and find each other.

## What You Will Learn

- How Eureka Server is enabled
- Why services register with Eureka
- Why hard-coded instance lists are not scalable

## How To Run

- Run from 09-Eureka-naming-server: mvn spring-boot:run
- Open http://localhost:8761/

## Example

`	ext
Open Eureka dashboard: http://localhost:8761/
`

Use the response to verify the service starts correctly and the configured endpoint is reachable.

## Key Points Or Common Mistakes

- Keep supporting services running when a chapter depends on them.
- Check the configured port before opening the URL.
- Do not change code while testing documentation examples unless the chapter asks for it.

## Chapter Summary And Next Step

This chapter adds one small step in the microservices learning path.
Continue with [10-Register-Currency-exchange-service-wtih-Eureka-naming-server](../10-Register-Currency-exchange-service-wtih-Eureka-naming-server/README.md), which solves the next problem in the learning path.

## Common Interview Questions And Short Answers

**Q1. What is Eureka?**  
A service registry for microservices.

**Q2. Which annotation enables Eureka Server?**  
@EnableEurekaServer.

**Q3. Why register-with-eureka=false?**  
The server does not need to register with itself.

**Q4. Which port is used?**  
Port 8761.

**Q5. What problem does Eureka solve?**  
Dynamic service discovery.

**Q6. What comes next?**  
Register Currency Exchange Service with Eureka.

## Existing Content Preserved

The section below keeps the original README notes from this project so no existing explanation, command, example, or technical detail is lost.

# **Eureka Service Discovery (Spring Cloud Netflix)**

### **Note**
```
Below Three application will work together.
09-Eureka-naming-server  
10-Register-Currency-exchange-service-wtih-Eureka-naming-server  
11-Register-Currency-conversion-service-with-eureka-naming-server  
```

---

## **1. The Problem (Before Eureka)**

* In earlier examples, we used **Feign** with hardcoded URLs.

  ```java
  @FeignClient(name="currency-exchange", url="localhost:8000")
  ```
* If we had multiple instances (8000, 8001, 8002â€¦), we had to **change the code/config** each time.
* If one server went down or a new one came up, we had to **update URLs manually**.
* This is **not practical** in a microservices architecture with many services.

---

## **2. The Solution â€“ Eureka Naming Server**

* Eureka acts as a **Service Registry**.
* All microservices **register themselves** with Eureka.
* Services ask Eureka for the **address of other services**, instead of using hardcoded URLs.

âœ… **No Hardcoded URLs** â†’ Just use **Service Name**

---

## **3. Dependencies**

### **For Eureka Server**

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
```
---

## **4. Configuration**

### **Eureka Server (application.properties)**

```properties
server.port=8761
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
```

> `false` â†’ Because the server doesnâ€™t need to register itself.
---

## **5. Flow of Service Discovery**

1. **Eureka Server Starts** â†’ Runs on port `8761`.
2. **Services Register** â†’ e.g.,

   * `user-service` â†’ Port 8081
   * `order-service` â†’ Port 8082
3. **Heartbeat** â†’ Services send "I am alive" signals every 30 sec.
4. **Service Lookup** â†’

   * `order-service` calls `http://USER-SERVICE`
   * Eureka resolves it to `http://localhost:8081`.
5. **Dynamic Updates** â†’

   * If new instances are added, they auto-register.
   * If a service dies, Eureka removes it.

---

## **6. Advantages of Eureka**

1. **Dynamic Service Discovery**

   * No hardcoded IPs/ports.
2. **Load Balancing Support**

   * Works with Ribbon/Feign.
3. **Health Checks**

   * Dead instances removed automatically.
4. **Scalability**

   * New instances register automatically.
5. **Resilience**

   * Uses cached data if Eureka is temporarily down.
