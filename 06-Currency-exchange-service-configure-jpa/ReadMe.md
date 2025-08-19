# **Currency Exchange Service with JPA & H2 Database**
---

# H2 Dependency

```xml
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
</dependency>
```

### 1. What is this?

* This is a **Maven dependency**.
* It tells Maven to include the **H2 database library** in our project.

---

### 2. What is H2?

* H2 is a **lightweight, in-memory database**.
* It is often used for **development, testing, and learning**.
* It does not need separate installation — it runs inside the application.

---

### 3. Why do we use this dependency?

* To connect Spring Boot application with **H2 database**.
* It allows the application to:

  * Create tables.
  * Insert data automatically (`data.sql`).
  * Run queries using JPA/Hibernate.

---

### 4. How does it work?

* When we add this dependency:

  * Spring Boot automatically detects H2.
  * It configures database connection using application.properties.
  * We can open **H2 Console** (web UI) to see data.

---

### 5. Example

* Suppose we add this dependency and run the app.
* In `application.properties` we write:

  ```properties
  spring.h2.console.enabled=true
  spring.datasource.url=jdbc:h2:mem:testdb
  ```
* When the app starts:

  * H2 DB (`testdb`) is created in memory.
  * `data.sql` is executed (tables + data inserted).
  * Access console at: [http://localhost:8080/h2-console](http://localhost:8080/h2-console).

---

**In short:**
`com.h2database:h2` is required when we want to use **H2 in-memory database** with Spring Boot for quick testing and development.

---
# Currency Exchange Service with JPA & H2 Database
---

### 1. Database Used

* We are using **H2 in-memory database** (data will be deleted on every restart).
* Easy for development and testing.
* Console URL: [http://localhost:8000/h2-console](http://localhost:8000/h2-console)

---

### 2. Configuration (application.properties)

```properties
spring.jpa.show-sql=true
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:amitdb
spring.jpa.defer-datasource-initialization=true
```

* `spring.jpa.show-sql=true` → SQL queries will be shown in logs.
* `spring.h2.console.enabled=true` → Enables H2 console access.
* `spring.datasource.url=jdbc:h2:mem:amitdb` → Defines in-memory DB name (`amitdb`).
* `spring.jpa.defer-datasource-initialization=true` → Runs `data.sql` at startup (creates table + inserts data).

---

### 3. Data Initialization

* **File used:** `data.sql`
* Runs automatically when application starts.
* Creates **CURRENCY\_EXCHANGE** table and inserts sample data.
* Example SQL:

```sql
insert into currency_exchange
(id,currency_from,currency_to,conversion_multiple,environment) 
values(10001,'USD','INR',70,'0');

insert into currency_exchange
(id,currency_from,currency_to,conversion_multiple,environment)
values(10002,'EUR','INR',75,'0');

insert into currency_exchange
(id,currency_from,currency_to,conversion_multiple,environment)
values(10003,'AUD','INR',25,'0');
```

![H2 database screen image.](./src/main/resources/images/db2_db.jpg)

---

### 4. REST Endpoints

1. **Hard-coded response**

   * URL: [http://localhost:8000/currency-exchange-hard-coded/from/USD/to/INR](http://localhost:8000/currency-exchange-hard-coded/from/USD/to/INR)
   * Returns fixed values (not from DB).

   ✅ Example Response:

   ```json
   {
     "id": 1001,
     "from": "USD",
     "to": "INR",
     "conversionMultiple": 82,
     "environment": "8000"
   }
   ```

2. **JPA-based response**

   * URL: [http://localhost:8000/currency-exchange-jpa/from/USD/to/INR](http://localhost:8000/currency-exchange-jpa/from/USD/to/INR)
   * Reads data from **H2 database**.
   * Supports multiple queries:

     * `/from/USD/to/INR`
     * `/from/EUR/to/INR`
     * `/from/AUD/to/INR`

   ✅ Example Response from DB:

   ```json
   {
     "id": 1002,
     "from": "EUR",
     "to": "INR",
     "conversionMultiple": 90,
     "environment": "8000"
   }
   ```
### **Access URL**
```
http://localhost:8000/currency-exchange-hard-coded/from/USD/to/INR  
http://localhost:8000/currency-exchange-jpa/from/USD/to/INR  
http://localhost:8000/currency-exchange-jpa/from/EUR/to/INR  
http://localhost:8000/currency-exchange-jpa/from/AUD/to/INR  
```
---

### 5. Key Points

* Each restart wipes out existing data (in-memory database).
* Useful for **testing microservices**.
* Can later be connected to **Oracle DB** by changing properties:

  ```properties
  spring.datasource.url=jdbc:oracle:thin:@localhost:1521:XE
  spring.datasource.username=system
  spring.datasource.password=system
  spring.datasource.driver-class-name=oracle.jdbc.driver.OracleDriver
  spring.datasource.platform=oracle
  ```
---
📌 **Example Use Case:**

* If a client requests currency conversion `USD → INR`, service fetches conversion multiple (e.g., `82`) from H2 DB and returns result.
* In real-world, this can be extended to fetch from Oracle or MySQL instead of H2.
---