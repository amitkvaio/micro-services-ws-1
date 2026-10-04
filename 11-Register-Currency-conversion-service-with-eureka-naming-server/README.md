# Register Currency Conversion Service With Eureka

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

This chapter solves the problem of discovering Currency Exchange through Eureka from Currency Conversion.

## What You Will Learn

- How conversion service registers with Eureka
- How Feign uses the service name currency-exchange
- How hard-coded exchange URLs are removed

## How To Run

- Start 09-Eureka-naming-server
- Start 10-Register-Currency-exchange-service-wtih-Eureka-naming-server
- Run from this project: mvn spring-boot:run
- Open http://localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/10

## Example

`	ext
GET http://localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/10
`

Use the response to verify the service starts correctly and the configured endpoint is reachable.

## Key Points Or Common Mistakes

- Keep supporting services running when a chapter depends on them.
- Check the configured port before opening the URL.
- Do not change code while testing documentation examples unless the chapter asks for it.

## Chapter Summary And Next Step

This chapter adds one small step in the microservices learning path.
This is the final chapter in this workspace. Practice by adding API Gateway, Resilience4j, Docker Compose, or monitoring as a next step.

## Common Interview Questions And Short Answers

**Q1. What service name does Feign use?**  
It uses currency-exchange.

**Q2. Why remove the URL from @FeignClient?**  
Eureka provides the target service instance.

**Q3. Which service should start first?**  
Eureka and Currency Exchange should start first.

**Q4. What endpoint tests conversion?**  
/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}.

**Q5. What does this chapter complete?**  
Basic discovery-based service-to-service communication.

**Q6. What is a good next practice task?**  
Add API Gateway or Resilience4j on top of this flow.

## Existing Content Preserved

The section below keeps the original README notes from this project so no existing explanation, command, example, or technical detail is lost.


# 11 - Register Currency Conversion Service with Eureka Naming Server

---

````markdown
# 11 - Register Currency Conversion Service with Eureka Naming Server

This project connects **Currency Conversion Service** to the **Eureka Naming Server**.

---

## ðŸ“Œ Dependency
Add the following dependency in the `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
````

---

## @EnableDiscoveryClient

To register **Currency Conversion Service** with the **Eureka Naming Server**,
add the `@EnableDiscoveryClient` annotation to the `CurrencyConversionServicesApplicationUsingFeign` class.

---

## Eureka Configuration

Configure the Eureka server URL in the `application.properties` file:

```properties
eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka
```

---

## URL Access

### Currency Exchange Service

Run and check using the following URL:

```
http://localhost:8000/currency-exchange/from/USD/to/INR
```

### Currency Conversion Service

Run and check (port value `8000` will be changing depending on instance):

```
http://localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/10  
http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10  
```
---

## **How to Run the Application**

1. **Run Eureka Naming Server**

   * Start `09-Eureka-naming-server-setup` application.

2. **Run Currency Exchange Service**

   * Start `10-Register-Currency-exchange-service-with-eureka-naming-server` application.
   * Run more than one instance of this service by changing the port.

3. **Run Currency Conversion Service**

   * Start `11-Register-Currency-conversion-service-with-eureka-naming-server` application.

4. **Verify Services in Eureka**

   * Open Eureka dashboard in the browser and check:

     * `10-Register-Currency-exchange-service-with-eureka-naming-server`
     * `11-Register-Currency-conversion-service-with-eureka-naming-server`
       are registered successfully.

---

**Sample Response:**

```json
{
    "id": 10001,
    "from": "USD",
    "to": "INR",
    "quantity": 10,
    "conversionMultiple": 70.00,
    "totalCalucatedAmout": 700.00,
    "environment": "8000 feign"
}
```

