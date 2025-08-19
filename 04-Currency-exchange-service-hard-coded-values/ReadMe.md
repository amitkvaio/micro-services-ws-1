
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

  → The application will still **start normally** using local/default configuration.

---

# **Access URL Example**

# **Currency Exchange Service**

* URL:

  ```
  http://localhost:8000/currency-exchange/from/USD/to/INR
  ```
* This endpoint fetches currency exchange values (for example, converting **USD → INR**).
---
