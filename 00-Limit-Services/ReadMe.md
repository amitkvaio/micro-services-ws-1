# Limit-Services
#### @ConfigurationProperties("limits-service")
> It tells Spring Boot: Bind all the properties starting with limits-service. 
		from configuration files to the fields in this class.
		
#  Access URL
>http://localhost:2025/limits
---

```xml
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-bootstrap</artifactId>
</dependency>
```
---

### 📌 What is `spring-cloud-starter-bootstrap`?

* It is a **Spring Cloud dependency**.
* It helps our Spring Boot application **load configuration settings early**, before the main application context starts.
* These settings are usually kept in a **bootstrap context** (separate from the main application context).

---

### 📌 Why do we need it?

Normally, Spring Boot reads configuration from:

* `application.properties` / `application.yml`

* In **microservices with Spring Cloud**, sometimes we want to load configuration from an **external source** (like **Spring Cloud Config Server**, Consul, or Vault).

* For this, the app must fetch configuration **before** creating beans, otherwise, wrong or missing values may be used.

`spring-cloud-starter-bootstrap` ensures:

* External configs are loaded **first** (bootstrap phase).
* Then the main application context starts with correct settings.

---

### 📌 When should we use it?

Use this dependency if:

1. ✅ We are using **Spring Cloud Config Server** (to fetch configs from Git, SVN, etc.).
2. ✅ We are using **HashiCorp Vault** (to fetch secrets/credentials securely).
3. ✅ We need to **separate bootstrap configs** (like service name, discovery settings, config server URL) from normal application configs.

---

### 📌 Example Scenario

Suppose we have 10 microservices, and instead of keeping separate `application.yml` in each one.

* We keep all configs in a **Spring Cloud Config Server (Git repo)**.

* Without `spring-cloud-starter-bootstrap`:
  Our microservice might start **before** fetching configs → leading to errors.

* With `spring-cloud-starter-bootstrap`:
  Microservice **first connects to Config Server** → loads configs → then starts normally.

---

✅ **In short:**
`spring-cloud-starter-bootstrap` is useful when we want our microservice to load configs (from Config Server, Vault, or Consul) **before** anything else starts.
It ensures our service always starts with the **right configuration**.

---

# **00 Limit-Services**
## **Objective**

**We are reading the values of below property from the bootstrap.properties file.**

---
```
limits-service.maximum=2000
limits-service.minimum=1000
limits-service.name=Default-Properties
```
https://github.com/amitkvaio/micro-services-ws-1/blob/main/00-Limit-Services/src/main/resources/bootstrap.properties  


## **Check this java classes for more details.**

https://github.com/amitkvaio/micro-services-ws-1/blob/main/00-Limit-Services/src/main/java/com/amit/microservices/limitsservice/Configuration.java  

https://github.com/amitkvaio/micro-services-ws-1/blob/main/00-Limit-Services/src/main/java/com/amit/microservices/limitsservice/LimitsConfigurationController.java  

https://github.com/amitkvaio/micro-services-ws-1/blob/main/00-Limit-Services/src/main/java/com/amit/microservices/limitsservice/LimitsServiceSpringBootApplication.java  


# **Access URL**
```
http://localhost:2025/limits
```
# **Running using command line.**
```
mvn clean package -DskipTests
java -jar target/myapp.jar --server.port=8001
```