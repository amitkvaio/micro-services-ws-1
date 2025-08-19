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
* If we had multiple instances (8000, 8001, 8002…), we had to **change the code/config** each time.
* If one server went down or a new one came up, we had to **update URLs manually**.
* This is **not practical** in a microservices architecture with many services.

---

## **2. The Solution – Eureka Naming Server**

* Eureka acts as a **Service Registry**.
* All microservices **register themselves** with Eureka.
* Services ask Eureka for the **address of other services**, instead of using hardcoded URLs.

✅ **No Hardcoded URLs** → Just use **Service Name**

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

> `false` → Because the server doesn’t need to register itself.
---

## **5. Flow of Service Discovery**

1. **Eureka Server Starts** → Runs on port `8761`.
2. **Services Register** → e.g.,

   * `user-service` → Port 8081
   * `order-service` → Port 8082
3. **Heartbeat** → Services send "I am alive" signals every 30 sec.
4. **Service Lookup** →

   * `order-service` calls `http://USER-SERVICE`
   * Eureka resolves it to `http://localhost:8081`.
5. **Dynamic Updates** →

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