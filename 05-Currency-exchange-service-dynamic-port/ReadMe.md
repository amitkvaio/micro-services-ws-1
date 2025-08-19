
# **Running Multiple Instances of a Spring Boot Application**

# **Why We Need It**

* Running multiple instances is useful for:

  * **Load balancing** (e.g., behind API Gateway / Eureka).
  * **High availability** (if one instance goes down, others handle requests).

---

# **Method 1: Using Command Line (VM Options)**

* Run each instance with a **different port number** using `-Dserver.port`.

# Example:

```bash
java -jar myapp.jar -Dserver.port=8001
java -jar myapp.jar -Dserver.port=8002
java -jar myapp.jar -Dserver.port=8003
```

---

# **Method 2: Using Eclipse IDE**

* Add **VM arguments** in the **Run Configurations**.

# Example:

```bash
-Dserver.port=8002
-Dserver.port=8003
-Dserver.port=8004
```

---

# **Expected Output Example**

When accessing an instance, the response shows the **port number** in the `environment` field.

```json
{
  "id": 1000,
  "from": "USD",
  "to": "INR",
  "conversionMultiple": 50,
  "environment": "8002"
}
```

* Here `"environment": "8002"` means the response came from the instance running on **port 8002**.

---

This approach allows multiple instances of the same Spring Boot application to run on **different ports** at the same time.

# **URL**
```
http://localhost:8002/currency-exchange/from/USD/to/INR  
http://localhost:8003/currency-exchange/from/USD/to/INR  
http://localhost:8004/currency-exchange/from/USD/to/INR  
```