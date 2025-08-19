## **Register the services with eureka server**
---

### Dependency

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

---

### What is it?

* This dependency makes our Spring Boot application a **Eureka Client**.
* A **Eureka Client** is a service (microservice) that can **register itself** with a **Eureka Server (Service Registry)** and can also **discover other services** from it.
* Eureka is part of the **Netflix OSS** libraries used in **Spring Cloud** for **service discovery**.

---

### Why do we need it? (Use Case)

1. In **microservices architecture**, we may have **many services** (like Currency exchanges service, Currency conversion services etc).
2. Each service may run on **different ports** or even on **different servers**.
3. Hardcoding service URLs (like `http://localhost:8000/currency-exchange/from/USD/to/INR`) in code is **not scalable**.
#### 4. With Eureka:

   * Every service **registers itself** with the Eureka Server.
   * When one service wants to talk to another, it **asks Eureka** to find the instance instead of hardcoding URLs.
   * Eureka also handles **load balancing** (if multiple instances of a service are running).

---

### ✅ Example

* Suppose we have these services:

  * **Eureka Server** (Service Registry) → runs at `http://localhost:8761`
  * **Currency-exchange** → registers with Eureka
  * **Currency-conversion** → registers with Eureka

Now, if **Currency-conversion** wants to call **Currency-exchange**:

* **Without Eureka →** It must know the exact URL like `http://localhost:8000/currency-exchange/from/USD/to/INR`
* **With Eureka →** It just asks for `CURRENCY-EXCHANGE` from Eureka, and Eureka gives the correct instance (even if it’s on another port or server).

---

### ✅ Widely Used Annotations with Eureka Client

* `@EnableEurekaClient` → Marks the application as a Eureka client.
* `@LoadBalanced` (with RestTemplate) → Enables **client-side load balancing** using Eureka service names.
* Example:

```java
package com.amit.microservices.controller;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.amit.microservices.bean.CurrencyConversion;
import com.amit.microservices.proxy.CurrencyExchangeProxy;

@RestController
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyExchangeProxy proxy;
	
	//with using proxy
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversionFeign(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity
			) {
				
		CurrencyConversion currencyConversion = proxy.retrieveExchangeValue(from, to);
		
		return new CurrencyConversion(currencyConversion.getId(), 
				from, to, quantity, 
				currencyConversion.getConversionMultiple(), 
				quantity.multiply(currencyConversion.getConversionMultiple()), 
				currencyConversion.getEnvironment() + " " + "feign");
	}
}
 
```

```Java
package com.amit.microservices.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.amit.microservices.bean.CurrencyConversion;


//@FeignClient(name="currency-exchange", url="localhost:8000") //Hard coded value port number will not change.
@FeignClient(name="currency-exchange")
public interface CurrencyExchangeProxy {
	
	//http://localhost:8000/currency-exchange/from/USD/to/INR
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangeValue(
			@PathVariable String from,
			@PathVariable String to);
}

/*
Feign is the rest service client.
	its make it easy to call rest full web service.
@FeignClient is used to talk with external micro services.
	name = name of the currency-exchange microservices
	URL = where currency-exchange ms running.

We would want the feign client to talk to Eureka and pick up the instances of currency-exchange 
and do load balancing between them.
Remove the url part as below
@FeignClient(name="currency-exchange")
*/
```
Here, `"CURRENCY-EXCHANGE"` is the **service name** registered in Eureka, not a hardcoded URL.

---

### **Real-Life Scenario**

Imagine **Amazon**:

* They have services like `Product-Service`, `Cart-Service`, `Order-Service`, `Payment-Service`.
* If tomorrow `Product-Service` runs on a new server or different port → **no code change is needed**, because Eureka keeps track of it.
* This makes the system **scalable** and **fault tolerant**.

---

### **Note**
This dependency is used to **make your application discoverable** (register itself) and **able to discover other microservices** in a distributed system without hardcoding IPs/ports.

---

# Using H2 Database in Spring Boot

### **Key Points**

1. **Inbuilt Database**

   * Spring Boot comes with an **inbuilt H2 database** (lightweight, in-memory database).
   * No need to install separately.

2. **Configuration in Properties File**

   * Example:

     ```properties
     spring.datasource.url=jdbc:h2:mem:testdb
     spring.datasource.driverClassName=org.h2.Driver
     spring.datasource.username=sa
     spring.datasource.password=password
     spring.h2.console.enabled=true
     spring.jpa.hibernate.ddl-auto=none
     spring.jpa.defer-datasource-initialization=true
     ```
   * `spring.jpa.defer-datasource-initialization=true` → ensures that **data.sql** is executed after JPA schema creation.

3. **Data Initialization (data.sql)**

   * On application startup, Spring Boot looks for `data.sql`.
   * Example `data.sql`:

     ```sql
     CREATE TABLE CURRENCY_EXCHANGE(
         id BIGINT AUTO_INCREMENT PRIMARY KEY,
         currency_from VARCHAR(10),
         currency_to VARCHAR(10),
         conversion_multiple DECIMAL(19,2)
     );

     INSERT INTO CURRENCY_EXCHANGE(currency_from, currency_to, conversion_multiple)
     VALUES ('USD', 'INR', 82.50);
     ```
   * This will **create the table** and **insert sample data**.

4. **H2 Console Access**

   * Open browser → [http://localhost:8000/h2-console](http://localhost:8000/h2-console)
   * JDBC URL: `jdbc:h2:mem:testdb`
   * Query Example:

     ```sql
     SELECT * FROM CURRENCY_EXCHANGE;
     ```

5. **REST API Access Example**

   * API: [http://localhost:8000/currency-exchange/from/USD/to/INR](http://localhost:8000/currency-exchange/from/USD/to/INR)
   * This will fetch the matching record from the H2 DB and return it as JSON.
   * Example Response:

     ```json
     {
       "id": 1,
       "from": "USD",
       "to": "INR",
       "conversionMultiple": 82.50
     }
     ```

6. **Data Reset on Restart**

   * Since H2 is **in-memory**, data is stored only in RAM.
   * On every server restart → existing data will be **wiped out** and reloaded from `data.sql`.

---

### ✅ Quick Example Flow

1. Start application → H2 DB is created in memory.
2. `data.sql` runs → Table `CURRENCY_EXCHANGE` created, USD→INR inserted.
3. Open **H2 Console** → Run `SELECT * FROM CURRENCY_EXCHANGE;` → See data.
4. Call API → `/currency-exchange/from/USD/to/INR` → JSON response from H2 DB.
5. Restart app → DB resets (fresh data loaded again).
---