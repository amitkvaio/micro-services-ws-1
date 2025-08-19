# Exloring/Uses of `openfeign` Dependency 
## And
# 08A – Currency Conversion Service using Feign

## **Maven Dependency**
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```
---

### 1. What is Feign?

* **Feign** is a **declarative REST client** provided by **Spring Cloud**.
* It makes calling other microservices **much simpler** than using `RestTemplate`.
* Instead of writing 15–20 lines of boilerplate code with `RestTemplate`, we just write a **Java interface** with annotations.

---

### 2. What this dependency does

* Adds Feign support to our Spring Boot project.
* Automatically integrates with:

  * **Spring Boot** (auto-configuration)
  * **Eureka/Service Discovery** (if available)
  * **Load Balancer** (if available)
* Removes the need to manually create and configure REST clients.

---

### 3. Why do we use it?

* To call another microservice with **just 1–2 lines of code**.
* Reduces **boilerplate code**.
* Increases **readability and maintainability**.
* Very useful when we have **many microservices** calling each other.

---

### 4. How to use Feign in a project?

#### Step 1: Add dependency (already shown above)
#### Step 2: Enable Feign Clients in our main application

```java
@SpringBootApplication
@EnableFeignClients   //  important annotation
public class CurrencyConversionServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CurrencyConversionServiceApplication.class, args);
    }
}
```

#### Step 3: Create a Feign Client Interface

```java
@FeignClient(name = "currency-exchange", url = "localhost:8000")
public interface CurrencyExchangeProxy {
    
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    CurrencyConversion retrieveExchangeValue(
            @PathVariable String from,
            @PathVariable String to);
}
```

#### Step 4: Use the Feign Client in Controller

```java
@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeProxy proxy;

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity) {
        
        CurrencyConversion response = proxy.retrieveExchangeValue(from, to);

        return new CurrencyConversion(
                response.getId(),
                from, to, quantity,
                response.getConversionMultiple(),
                quantity.multiply(response.getConversionMultiple()),
                response.getEnvironment() + " feign");
    }
}
```

---

### 5. Example Flow

* We call:
  `http://localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/10`
* `CurrencyConversionService` (8100) calls `CurrencyExchangeService` (8000) **using Feign**.
* Response is automatically mapped to the `CurrencyConversion` object.
* Final amount is calculated and returned.

---

# 08A – Currency Conversion Service using Feign

## 🔹 The Problem with RestTemplate

* To call the Currency Exchange Microservice, we used **RestTemplate**.
* For a **simple REST API call**, we had to write \~20 lines of code.
* In a real project, with **hundreds of microservices**, this becomes **repetitive and hard to maintain**.

---

## 🔹 Solution → Feign

* **Feign** is a REST client provided by **Spring Cloud**.
* It makes calling other microservices **very easy**.
* Instead of writing boilerplate code, we just write an **interface**.
---

## 🔹 How Feign Works

* `@FeignClient` is used to call another microservice.
* `name` → Name of the target microservice.
* `url` → Where the target microservice is running.

### Example:

```java
@FeignClient(name="currency-exchange", url="localhost:8000")
public interface CurrencyExchangeProxy {
    
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    CurrencyConversion retrieveExchangeValue(
        @PathVariable String from,
        @PathVariable String to);
}
```

In the controller, we can directly use this proxy instead of writing long RestTemplate code.
---
# How to Start the Services

---

### **1. Start the Microservices**

1. **First**, start the **Currency Exchange Service**
   → `06-Currency-exchange-service-configure-jpa`
2. **Second**, start the **Currency Conversion Service**
   → `08A-Currency-conversion-service-using-feign`

---

### **2. Verify Currency Exchange Service**

Check if the **Currency Exchange Service** is running by opening this URL in our browser:

##### Currency Exchange Service
  http://localhost:8000/currency-exchange/from/USD/to/INR  

#### Currency Conversion Service

  [http://localhost:8100/currency-exchange-feign/from/USD/to/INR](http://localhost:8100/currency-exchange-feign/from/USD/to/INR)

  [http://localhost:8100/currency-exchange-feign/from/EUR/to/INR](http://localhost:8100/currency-exchange-feign/from/EUR/to/INR)

  [http://localhost:8100/currency-exchange-feign/from/AUD/to/INR](http://localhost:8100/currency-exchange-feign/from/AUD/to/INR)

---

## **Short Summary**

* Start **Exchange Service (port 8000)** first.
* Start **Conversion Service (port 8100)** next.
* Test both services using the given URLs.

---

### **In Short**

* **Without Feign** → Use `RestTemplate` → More code, boilerplate.
* **With Feign** → Just define an interface → Spring auto-creates REST client.

---

### **Key Comparison**

* **RestTemplate**

  * Needs extra code (HTTP calls, object mapping).
  * Repetitive and less readable.

* **Feign**
  * Very simple, clean, and declarative.
  * Less code, more readability.