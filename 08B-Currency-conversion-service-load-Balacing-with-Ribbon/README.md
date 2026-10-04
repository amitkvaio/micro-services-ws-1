# Currency Conversion Service - Ribbon Load Balancing

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

This chapter solves the problem of calling only one fixed exchange service instance by using Ribbon client-side load balancing.

## What You Will Learn

- How Ribbon selects from configured server list
- How Feign works with Ribbon
- Why multiple exchange instances help availability

## How To Run

- Start exchange service instances on ports listed in currency-exchange.ribbon.listOfServers
- Run from 08B-Currency-conversion-service-load-Balacing-with-Ribbon: mvn spring-boot:run
- Open http://localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/10 multiple times

## Example

`	ext
GET http://localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/10
`

Use the response to verify the service starts correctly and the configured endpoint is reachable.

## Key Points Or Common Mistakes

- Keep supporting services running when a chapter depends on them.
- Check the configured port before opening the URL.
- Do not change code while testing documentation examples unless the chapter asks for it.

## Chapter Summary And Next Step

This chapter adds one small step in the microservices learning path.
Continue with [09-Eureka-naming-server](../09-Eureka-naming-server/README.md), which solves the next problem in the learning path.

## Common Interview Questions And Short Answers

**Q1. What is Ribbon?**  
Ribbon is an older Netflix client-side load balancer.

**Q2. How are servers listed?**  
Using currency-exchange.ribbon.listOfServers.

**Q3. Why call the endpoint multiple times?**  
To see different backend instances handle requests.

**Q4. Is Ribbon modern?**  
Ribbon is old; Spring Cloud LoadBalancer is preferred in newer projects.

**Q5. Does this use Eureka?**  
No, this chapter uses a static server list.

**Q6. What comes next?**  
Eureka removes the need for a static list.

## Existing Content Preserved

The section below keeps the original README notes from this project so no existing explanation, command, example, or technical detail is lost.

# **Load Balancing : Netflix-Ribbon**

# **What is Load Balancing?**

* **Load balancing** means **distributing incoming requests** (traffic) across multiple servers.
* **No single server is overloaded**, and the application runs **fast, reliable, and always available**.

---

## **Types of Load Balancing**

### 1. **Client-Side Load Balancing**

* The **client (caller)** decides **which server** to send the request to.
* Example:

  * Ribbon (in Spring Cloud)
  * Feign + Ribbon
* Here, the client knows all server addresses (from **Eureka registry** or config).

---

### 2. **Server-Side Load Balancing**

* The **client always calls one entry point** (like an API Gateway or Load Balancer server).
* That load balancer decides which server instance will handle the request.
* Example:

  * **NGINX, HAProxy, AWS ELB, Kubernetes Service**

---

## **Load Balancing Strategies**

Different ways requests can be distributed:

1. **Round Robin** â†’ Each request goes to the next server in order.

   * Example:
     Request 1 â†’ Server A
     Request 2 â†’ Server B
     Request 3 â†’ Server C
     Request 4 â†’ Server A again

2. **Random** â†’ Request sent to a random server.

3. **Least Connections** â†’ New request goes to the server with the fewest active connections.

4. **Weighted** â†’ Some servers get more traffic based on their capacity (e.g., a powerful server gets 2x requests compared to a weaker one).

---
Load balancing = **Sharing traffic across servers** to improve **performance, reliability, and availability**.

---

### **Maven Dependency**
```xml
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
</dependency>
```

### **application.properties**
```properties
# spring.config.import=optional:configserver:http://localhost:8888 (optional)
spring.application.name=currency-conversion
server.port=8100
currency-exchange.ribbon.listOfServers=http://localhost:8000, http://localhost:8001,http://localhost:8002,http://localhost:8003,http://localhost:8004
```
---
## **How to Start the Services**
---

### **1. Start the Microservices**

1. **First**, start the **Currency Exchange Service**
   â†’ `06-Currency-exchange-service-configure-jpa`
2. **Second**, start the **Currency Conversion Service**
   â†’ `08B-Currency-conversion-service-load-Balacing-with-Ribbon`


## **URL**
```url
http://localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/10
```
---

## **Example**

Suppose you have a **Currency Exchange Service** running on 3 servers:

* Server 1 â†’ port 8000
* Server 2 â†’ port 8001
* Server 3 â†’ port 8002

Ribbon will send requests like:

* 1st request â†’ port 8000
* 2nd request â†’ port 8001
* 3rd request â†’ port 8002
* 4th request â†’ again port 8000

Without load balancing:

* All clients may hit **only 8000** â†’ it may crash due to overload.

With load balancing:

* Requests are shared: 1st to 8000, 2nd to 8001, 3rd to 8002, 4th to 8000 again.
* This makes the system **scalable and fault tolerant**.

## **Ribbon Summary**

* **Ribbon** is a **client-side load balancer**.
* It distributes requests across multiple service instances.
* Works with **Eureka** or a static list of servers.
* Improves **performance** and **fault tolerance**.
* Ribbon + **RestTemplate / Feign** â†’ adds load balancing automatically.
