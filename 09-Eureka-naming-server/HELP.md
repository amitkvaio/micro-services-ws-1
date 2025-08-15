# Here we are just setting the Eureka naming server
> In the previous Example - 08-Currency-conversion-service-using-feign\
	We have hard coded currency-exchange and URL as shown below
	@FeignClient(name="currency-exchange", url="localhost:8000")
	

# If I would want to get the currency conversions then its required to talk with different instance of currency exchange, what do I need to do?

>	As per previous example we will do like blow\
	I will go here and change the localhost:8001, localhost:8002, localhost:8003 and so on.

## Fein, provide an option where we can hard code multiple URLs in here, even that would not be a good solution because.
>   @FeignClient(name="currency-exchange", url="localhost:8000,localhost:8001,localhost:8002")

#### Let's say 8000 went down and let's say a new instance was brought up on 8000 or some other port
#### Then we must change the configuration of this application of the code, of this application all

# What would happen is in a microdevices architecture, all the instances of all the micro services would register with a service registry.

> The Currency Exchange Service would register with the service registry and all the other\ 
	micro services also registered with the service registry.

##### Currency conversion micro service wants to talk to the currency exchange, make service.
##### It would ask the service registry, hey, what are the addresses of the currency exchange?
##### he service registry would return those back to the currency and we should make a service.
##### Ad then the currency conversion micro service can send the request out to the currency exchange micro service.

# spring-cloud-starter-netflix-eureka-server
```
pom.xml
<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
```

# About Eureka Server
> A Eureka Server is a service registry where all microservices can register themselves and discover\ 
	other services without 	hardcoding their locations.

## **4. Advantages**

1. **Dynamic Service Discovery**

   * No need to hardcode IPs and ports.
2. **Load Balancing Support**

   * Works with Ribbon/Feign to call multiple instances.
3. **Automatic Health Checks**

   * Eureka removes dead instances from the registry.
4. **Scalability**

   * New instances automatically register and become available.
5. **Resilience**

   * Services can still use cached registry data if Eureka is temporarily unavailable.


>No hardcoded URLs for services.\
>Order service found User service via Eureka using its name, not IP/port.\
>If User service scales to multiple instances, Eureka + Feign can load balance calls automatically.


```
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
```

>Both are usually set to false in Eureka Server, because it doesn’t need to register itself or \fetch service details from anywhere.

# spring-cloud-starter-netflix-eureka-client

```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

# application.properties
>
server.port=8081\
spring.application.name=user-service\
eureka.client.service-url.defaultZone=http://localhost:8761/eureka

* It **registers** with the Eureka Server at `http://localhost:8761`.
* Shows up in the Eureka dashboard.
* Can be discovered by other services.



## **Eureka Service Discovery Flow**

```
                ┌──────────────────────────┐
                │  Eureka Server (Registry) │
                │  Port: 8761               │
                │  Dependency:              │
                │  spring-cloud-starter-    │
                │  netflix-eureka-server    │
                └───────────▲───────────────┘
                            │
       (Registers itself)   │
                            │
        ┌───────────────────┴───────────────────┐
        │                                        │
┌───────┴─────────┐                      ┌───────┴─────────┐
│  User Service    │                      │ Order Service   │
│ Port: 8081       │                      │ Port: 8082      │
│ spring-cloud-    │                      │ spring-cloud-   │
│ starter-netflix- │                      │ starter-netflix-│
│ eureka-client    │                      │ eureka-client   │
└───▲──────────────┘                      └───▲─────────────┘
    │   (Fetch registry list)                 │
    │                                          │
    └─────────────┬────────────────────────────┘
                  │
     Calls by Service Name (No Hardcoding)
                  │
        ┌─────────────────────────┐
        │ Example:                │
        │ GET http://USER-SERVICE │
        │ → Eureka resolves to    │
        │   http://localhost:8081 │
        └─────────────────────────┘
```

---

## **How It Works Step-by-Step**

1. **Eureka Server Starts** (`spring-cloud-starter-netflix-eureka-server`)

   * Runs on port `8761`.
   * Acts as the central **service registry**.

2. **Eureka Clients Start** (`spring-cloud-starter-netflix-eureka-client`)

   * `user-service` (port 8081) registers with Eureka Server as `USER-SERVICE`.
   * `order-service` (port 8082) registers as `ORDER-SERVICE`.

3. **Heartbeat Mechanism**

   * Every 30 seconds (default), clients send a **heartbeat** to tell Eureka “I’m alive.”

4. **Service Discovery**

   * If `order-service` wants to talk to `user-service`:

     * It makes a request using the **service name** (`USER-SERVICE`).
     * Eureka resolves the name to the **actual IP & port**.
     * This removes the need for hardcoded URLs.

5. **Dynamic Updates**

   * If `user-service` scales to 3 instances, Eureka updates the registry.
   * Requests can be **load balanced** across instances automatically.

---

## **Key Advantages of This Setup**

* **No hardcoded host/port** in service-to-service calls.
* **Scalable** — new instances register automatically.
* **Resilient** — dead services are removed from the registry.
* **Load balancing ready** — works with Ribbon or Feign.

