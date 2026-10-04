# Currency Exchange Service - Hard-Coded Values

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

This chapter solves the first currency service problem by returning hard-coded exchange data through a REST API.

## What You Will Learn

- How to create a currency exchange endpoint
- How path variables work
- Why hard-coded values are only a learning step

## How To Run

- Run from 04-Currency-exchange-service-hard-coded-values: mvn spring-boot:run
- Open http://localhost:8000/currency-exchange/from/USD/to/INR

## Example

`	ext
GET http://localhost:8000/currency-exchange/from/USD/to/INR
`

Use the response to verify the service starts correctly and the configured endpoint is reachable.

## Key Points Or Common Mistakes

- Keep supporting services running when a chapter depends on them.
- Check the configured port before opening the URL.
- Do not change code while testing documentation examples unless the chapter asks for it.

## Chapter Summary And Next Step

This chapter adds one small step in the microservices learning path.
Continue with [05-Currency-exchange-service-dynamic-port](../05-Currency-exchange-service-dynamic-port/README.md), which solves the next problem in the learning path.

## Common Interview Questions And Short Answers

**Q1. What does this service return?**  
It returns a hard-coded conversion multiple.

**Q2. Why use hard-coded values first?**  
It keeps the first REST example simple.

**Q3. Which endpoint is exposed?**  
/currency-exchange/from/{from}/to/{to}.

**Q4. Which port is used?**  
Port 8000.

**Q5. What is the limitation?**  
Data changes require code changes.

**Q6. What is improved in the next chapter?**  
The service starts showing dynamic environment/port details.

## Existing Content Preserved

The section below keeps the original README notes from this project so no existing explanation, command, example, or technical detail is lost.


# **Objective**
```
Here we setting and returning the hard coded values of currency exchange from the `CurrencyExchangeController`  
Exloring more about the below mentioned properties.
```

```java
package com.amit.microservices.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.amit.microservices.bean.CurrencyExchange;

@RestController
public class CurrencyExchangeController {
	
			                     //where {from} and {to} are path variable  
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchnageValue(
			@PathVariable String from,
			@PathVariable String to) {
		CurrencyExchange currencyExchange = new CurrencyExchange(1000L, "USD", "INR", BigDecimal.valueOf(86));
		return currencyExchange;
	}
}

```


# **spring.config.import Property**

* **Purpose:** Used to import configuration from an external source (like a remote Config Server).
* Example:

  ```properties
  spring.config.import=optional:configserver:http://localhost:8888
  ```
---

# **configserver:[http://localhost:8888](http://localhost:8888)**

* Refers to a **Spring Cloud Config Server** running at `http://localhost:8888`.
* The application will fetch its configuration from this server.

---

# **optional:**

* Makes the config import **optional**.
* If the config server is:

  * **Down**
  * **Unavailable**
  * **Misconfigured**

  â†’ The application will still **start normally** using local/default configuration.

---

# **Access URL Example**

# **Currency Exchange Service**

* URL:

  ```
  http://localhost:8000/currency-exchange/from/USD/to/INR
  ```
* This endpoint fetches currency exchange values (for example, converting **USD â†’ INR**).
---

