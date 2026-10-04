# Currency Exchange Service - Dynamic Port

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

This chapter solves the problem of identifying which running instance returned a response.

## What You Will Learn

- How to read the running server port
- How to run the same service on different ports
- Why environment details help in load balancing demos

## How To Run

- Run from 05-Currency-exchange-service-dynamic-port: mvn spring-boot:run
- Open http://localhost:8002/currency-exchange/from/USD/to/INR
- Optional second instance: mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8001

## Example

`	ext
GET http://localhost:8002/currency-exchange/from/USD/to/INR
`

Use the response to verify the service starts correctly and the configured endpoint is reachable.

## Key Points Or Common Mistakes

- Keep supporting services running when a chapter depends on them.
- Check the configured port before opening the URL.
- Do not change code while testing documentation examples unless the chapter asks for it.

## Chapter Summary And Next Step

This chapter adds one small step in the microservices learning path.
Continue with [06-Currency-exchange-service-configure-jpa](../06-Currency-exchange-service-configure-jpa/README.md), which solves the next problem in the learning path.

## Common Interview Questions And Short Answers

**Q1. Why show the port in the response?**  
It helps identify which instance handled the request.

**Q2. How can another port be used?**  
Pass --server.port or use the commented -Dserver.port value.

**Q3. What is the configured port?**  
The default configured port is 8002.

**Q4. Why is this useful later?**  
It helps demonstrate load balancing.

**Q5. Does this use a database?**  
No, this chapter still uses hard-coded values.

**Q6. What is the next improvement?**  
Move exchange data to JPA and H2.

## Existing Content Preserved

The section below keeps the original README notes from this project so no existing explanation, command, example, or technical detail is lost.


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
