# Currency Conversion Service - RestTemplate

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

This chapter solves the problem of one microservice calling another service using RestTemplate.

## What You Will Learn

- How Currency Conversion calls Currency Exchange
- How RestTemplate sends HTTP requests
- How quantity and conversion multiple produce total amount

## How To Run

- Start 06-Currency-exchange-service-configure-jpa first
- Run from 07-currency-conversion-service-used-of-resttemplate: mvn spring-boot:run
- Open http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10

## Example

`	ext
GET http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10
`

Use the response to verify the service starts correctly and the configured endpoint is reachable.

## Key Points Or Common Mistakes

- Keep supporting services running when a chapter depends on them.
- Check the configured port before opening the URL.
- Do not change code while testing documentation examples unless the chapter asks for it.

## Chapter Summary And Next Step

This chapter adds one small step in the microservices learning path.
Continue with [08A-Currency-conversion-service-using-feign](../08A-Currency-conversion-service-using-feign/README.md), which solves the next problem in the learning path.

## Common Interview Questions And Short Answers

**Q1. What is RestTemplate?**  
A Spring client used to call REST APIs.

**Q2. Which service must run first?**  
The currency exchange service must run first.

**Q3. Why is this approach limited?**  
The exchange URL is hardcoded in code.

**Q4. Which port does conversion use?**  
Port 8100.

**Q5. What is calculated?**  
quantity multiplied by conversionMultiple.

**Q6. What is improved next?**  
Feign makes the REST client cleaner.

## Existing Content Preserved

The section below keeps the original README notes from this project so no existing explanation, command, example, or technical detail is lost.



# Calling Currency Exchange Microservice from Currency Conversion Microservice

---

# 1. The Need

* We have **two microservices**:

  1. **Currency Exchange Service (06-Currency-exchange-service-configure-jpa)** â†’ provides exchange rate (USD â†’ INR).
  2. **Currency Conversion Service (07-currency-conversion-service-used-of-resttemplate)** â†’ uses the exchange rate and calculates the converted value for a given quantity.

* Example:

  * If USD â†’ INR = `82`
  * Quantity = `10`
  * Converted amount = `10 Ã— 82 = 820`

---

# 2. How to Call One Microservice from Another?

* We can use **`RestTemplate`** in Spring Boot.
* `RestTemplate` allows us to make **REST API calls** to another microservice.

---

# 3. Example with `RestTemplate`

```java
@RestController
public class CurrencyConversionController {
    
    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    // Example: http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10
    public CurrencyConversion calculateCurrencyConversion(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity) {
        
        // Step 1: Define the URL parameters
        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        
        // Step 2: Call the Currency Exchange Microservice
        ResponseEntity<CurrencyConversion> responseEntity =
            new RestTemplate().getForEntity(
                "http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversion.class,
                uriVariables);
        
        // Step 3: Extract response body
        CurrencyConversion currencyConversion = responseEntity.getBody();
        
        // Step 4: Return a new object with calculated value
        return new CurrencyConversion(
                currencyConversion.getId(),
                from,
                to,
                quantity,
                currencyConversion.getConversionMultiple(),
                quantity.multiply(currencyConversion.getConversionMultiple()),
                currencyConversion.getEnvironment());
    }
}
```

---

# 4. Explanation of Code

* **Step 1:** Build `uriVariables` â†’ `{from=USD, to=INR}`
* **Step 2:** Call Exchange Service API â†’
  `http://localhost:8000/currency-exchange/from/USD/to/INR`
* **Step 3:** Get the response from Exchange Service.
* **Step 4:** Multiply `quantity Ã— conversionMultiple` to calculate the final amount.

---

# 5. Problem with `RestTemplate`

* The code is **long and repetitive** (20+ lines).
* If we have **hundreds of microservices** calling each other â†’ we will need to write the same kind of code everywhere.
* This makes the system **hard to maintain**.
* ##### **For Better Solution reffer â†’ 08A-Currency-conversion-service-using-feign**
---

# 6. Note
* `RestTemplate` works but is **tedious**.
* For **scalable microservices** â†’ use **Feign**.
* Feign = less code, more readability, easier maintenance.
---

# **application.properties**

```properties
spring.config.import=optional:configserver:http://localhost:8888
spring.application.name=currency-conversion
server.port=8100
```
---

# How to Start the Services

---

# **1. Start the Microservices**

1. **First**, start the **Currency Exchange Service**
   â†’ `06-Currency-exchange-service-configure-jpa`
2. **Second**, start the **Currency Conversion Service**
   â†’ `07-currency-conversion-service-used-of-resttemplate`

---

# **2. Verify Currency Exchange Service**

* Check if the **Currency Exchange Service** is running by opening this URL in our browser:

  [http://localhost:8000/currency-exchange/from/USD/to/INR](http://localhost:8000/currency-exchange/from/USD/to/INR)

---

# **3. Call Currency Conversion Service**

* Once the Exchange Service is running, call the **Currency Conversion Service**:

  [http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10](http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10)

---

# **4. Other Example URLs**

We can also try these URLs:

* **Hard-coded values (for testing)**
  [http://localhost:8100/currency-conversion-hard-coded-values/from/USD/to/INR/quantity/100](http://localhost:8100/currency-conversion-hard-coded-values/from/USD/to/INR/quantity/100)

* **Dynamic calculation (with RestTemplate)**
  [http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/100](http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/100)

---

# **Sort Summary:**

* Start **Exchange Service (8000)** first.
* Start **Conversion Service (8100)** next.
* Test both services using the given URLs.
---
