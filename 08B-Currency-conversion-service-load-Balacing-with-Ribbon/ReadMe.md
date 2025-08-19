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

1. **Round Robin** → Each request goes to the next server in order.

   * Example:
     Request 1 → Server A
     Request 2 → Server B
     Request 3 → Server C
     Request 4 → Server A again

2. **Random** → Request sent to a random server.

3. **Least Connections** → New request goes to the server with the fewest active connections.

4. **Weighted** → Some servers get more traffic based on their capacity (e.g., a powerful server gets 2x requests compared to a weaker one).

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
   → `06-Currency-exchange-service-configure-jpa`
2. **Second**, start the **Currency Conversion Service**
   → `08B-Currency-conversion-service-load-Balacing-with-Ribbon`


## **URL**
```url
http://localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/10
```
---

## **Example**

Suppose you have a **Currency Exchange Service** running on 3 servers:

* Server 1 → port 8000
* Server 2 → port 8001
* Server 3 → port 8002

Ribbon will send requests like:

* 1st request → port 8000
* 2nd request → port 8001
* 3rd request → port 8002
* 4th request → again port 8000

Without load balancing:

* All clients may hit **only 8000** → it may crash due to overload.

With load balancing:

* Requests are shared: 1st to 8000, 2nd to 8001, 3rd to 8002, 4th to 8000 again.
* This makes the system **scalable and fault tolerant**.

## **Ribbon Summary**

* **Ribbon** is a **client-side load balancer**.
* It distributes requests across multiple service instances.
* Works with **Eureka** or a static list of servers.
* Improves **performance** and **fault tolerance**.
* Ribbon + **RestTemplate / Feign** → adds load balancing automatically.