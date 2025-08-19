

# Calling Currency Exchange Microservice from Currency Conversion Microservice

---

# 1. The Need

* We have **two microservices**:

  1. **Currency Exchange Service (06-Currency-exchange-service-configure-jpa)** → provides exchange rate (USD → INR).
  2. **Currency Conversion Service (07-currency-conversion-service-used-of-resttemplate)** → uses the exchange rate and calculates the converted value for a given quantity.

* Example:

  * If USD → INR = `82`
  * Quantity = `10`
  * Converted amount = `10 × 82 = 820`

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

* **Step 1:** Build `uriVariables` → `{from=USD, to=INR}`
* **Step 2:** Call Exchange Service API →
  `http://localhost:8000/currency-exchange/from/USD/to/INR`
* **Step 3:** Get the response from Exchange Service.
* **Step 4:** Multiply `quantity × conversionMultiple` to calculate the final amount.

---

# 5. Problem with `RestTemplate`

* The code is **long and repetitive** (20+ lines).
* If we have **hundreds of microservices** calling each other → we will need to write the same kind of code everywhere.
* This makes the system **hard to maintain**.
* ##### **For Better Solution reffer → 08A-Currency-conversion-service-using-feign**
---

# 6. Note
* `RestTemplate` works but is **tedious**.
* For **scalable microservices** → use **Feign**.
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
   → `06-Currency-exchange-service-configure-jpa`
2. **Second**, start the **Currency Conversion Service**
   → `07-currency-conversion-service-used-of-resttemplate`

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