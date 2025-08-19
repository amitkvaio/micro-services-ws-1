
# 11 - Register Currency Conversion Service with Eureka Naming Server

---

````markdown
# 11 - Register Currency Conversion Service with Eureka Naming Server

This project connects **Currency Conversion Service** to the **Eureka Naming Server**.

---

## 📌 Dependency
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