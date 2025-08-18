
# Prior to Micro Services, let's know about Cloud Computing.

---

## 1. **What is Cloud?**

* Cloud means **using the internet to access computing resources** like servers, storage, databases, and software instead of keeping them on your personal computer.
* Cloud means using someone else’s powerful computer (server) through the internet.*
* You don’t need to buy heavy servers or software, you can just use them on the internet.

**Example**:

* Just like electricity comes from the power grid (you don’t need your own power plant), cloud provides computing power from a shared infrastructure.
* Gmail, Google Drive, and Netflix all run on cloud.

```
   Internet
      |
   [ Cloud ]  --> (Storage, Compute, Apps)
      |
   [ User Laptop/Phone ]
```
---

## 2. **Why do we need the Cloud?**

* To **reduce cost** – no need to buy and maintain big servers.
* To **access from anywhere** – only internet is required.
* To **grow easily** – if your app gets more users, cloud gives more servers automatically.
* To **improve reliability** – even if one server fails, another is available.

**Example**:

* A small company launches an online shopping site. If suddenly many users visit, they don’t need to buy 100 new servers. Cloud automatically provides resources.

---

## 3. **How does Cloud provide Auto Scaling?**

* **Auto Scaling** means the system automatically increases or decreases resources based on traffic (demand).
* Cloud providers (like AWS, Azure, Google Cloud) monitor usage and add/remove servers.

**Example**:

* A ticket booking website during IPL season gets huge traffic. Cloud adds more servers during peak time and removes them when traffic goes down.
* This saves **money + ensures performance**.
* Day time: More people visit an app → Cloud adds extra servers.*
* Night time: Fewer visitors → Cloud reduces servers → Saves cost.*

---

## 4. **Advantages of Cloud**

1. **Cost Saving** – Pay only for what you use.
2. **Scalability** – Can grow or shrink resources easily.
3. **Flexibility** – Work from anywhere using the internet.
4. **Reliability** – Data is stored safely with backups.
5. **Speed** – Quickly launch new apps.
6. **Security** – Advanced firewalls, encryption, monitoring.

**Example**:

* Startups can launch apps quickly without spending millions on servers.

---

## 5. **Challenges with Cloud**

1. **Internet Dependence** – Without internet, no access.
2. **Security Risks** – Data stored in cloud may face cyber-attacks.
3. **Compliance Issues** – Some countries restrict storing data outside.
4. **Downtime** – If cloud provider faces outage, services may stop.
5. **Vendor Lock-In** – Difficult to switch from one cloud provider to another.

**Example**:

* If AWS goes down (it happened in 2020), many websites like Netflix, LinkedIn, and apps depending on it also face downtime.

---

## Widely Used Cloud Terms (Simple Explanation)

* **IaaS (Infrastructure as a Service)** – Provides servers, storage, networking. (e.g., AWS EC2).
* **PaaS (Platform as a Service)** – Provides ready platform to build apps. (e.g., Google App Engine).
* **SaaS (Software as a Service)** – Provides software via internet. (e.g., Gmail, Zoom).
* **Public Cloud** – Shared by many users (e.g., AWS, Azure).
* **Private Cloud** – Dedicated to one company only.
* **Hybrid Cloud** – Combination of public + private.

```
        +--------------------+
        |   SaaS (Apps)      | --> Gmail, Google Docs
        +--------------------+
        |   PaaS (Platform)  | --> Google App Engine
        +--------------------+
        |   IaaS (Servers)   | --> AWS EC2, Azure VM
        +--------------------+

```
---

# Introducing Spring Cloud
---
## 1. What is Spring Cloud?

* **Spring Cloud** is a set of tools built on **Spring Boot** to develop **Microservices**.
* It helps different microservices talk to each other, discover each other, handle configuration, load balancing, security, and resilience.

---

## 2. Why do we need Spring Cloud?

* In **Microservices Architecture**, we have many small services instead of one big application.
* Problems that arise:

  * How do services **find each other**?
  * How do we handle **central configuration**?
  * How to handle **failures & retries**?
  * How to **route requests**?
* Spring Cloud provides ready-made solutions for all these.

---

## 3. Key Features of Spring Cloud

1. **Service Discovery** → Using **Eureka** (services register and find each other).
2. **API Gateway** → Using **Spring Cloud Gateway** (single entry point).
3. **Centralized Configuration** → Using **Spring Cloud Config**.
4. **Load Balancing** → Using **Spring Cloud LoadBalancer** or Ribbon (old).
5. **Fault Tolerance** → Using **Resilience4j** (retry, circuit breaker).
6. **Distributed Tracing & Monitoring** → Using **Sleuth, Zipkin**.

---

## 4. Simple Example (E-commerce App)

Imagine an **online shopping app** built with microservices:

* **Product Service** → Manages products
* **Order Service** → Manages orders
* **Payment Service** → Manages payments

Without Spring Cloud → each service has to remember IPs and configs manually.
With Spring Cloud → Eureka handles service discovery, Config server manages configs, Gateway routes requests, Resilience4j handles failures.

---

## 5. ASCII Diagram: Spring Cloud in Action

```
               [ API Gateway ]
                     |
     ---------------------------------
     |               |               |
[ Product Service ] [ Order Service ] [ Payment Service ]
     |               |               |
   Config          Eureka         Resilience4j
   Server        (Discovery)      (Fault Tolerance)
```

---

## 6. Advantages of Spring Cloud

* Easy to build **scalable microservices**.
* Handles **common challenges** (discovery, config, failures).
* Works well with **Spring Boot**.
* Reduces developer effort (no need to build from scratch).

---

## 7. Widely Used Spring Cloud Components

* **Spring Cloud Config** → Centralized config.
* **Spring Cloud Netflix Eureka** → Service registry.
* **Spring Cloud Gateway** → Smart routing.
* **Resilience4j** → Fault tolerance.
* **Spring Cloud Sleuth + Zipkin** → Tracing requests.

---
# Monolithic vs Microservices

---

## 1. What is a Monolithic Application?

* A **Monolithic application** is built as **one big single unit**.
* All features (login, product, payment, reports, etc.) are packed together in one codebase.
* If one part fails, the whole system can be affected.

**Example:**

* A shopping website where **login, product, cart, payment, order** → all are inside one single application file.

**ASCII Diagram (Monolithic):**

```
+---------------------------------------------------+
|                 Monolithic App                    |
|---------------------------------------------------|
|  Login | Product | Cart | Payment | Order | Report|
+---------------------------------------------------+
```

---

## 2. What is a Microservices Application?

* A **Microservices application** is broken into **small, independent services**.
* Each service has its **own code, database, and deployment**.
* Services talk to each other using APIs (HTTP/REST).
* If one service fails, others can still work.

**Example:**

* Shopping website split into:

  * **Login Service** (manages users)
  * **Product Service** (manages products)
  * **Cart Service** (manages cart)
  * **Payment Service** (handles payments)
  * **Order Service** (handles orders)

**ASCII Diagram (Microservices):**

```
     +-----------------+       +-----------------+
     |  Login Service  | <---> | Product Service |
     +-----------------+       +-----------------+
               |                         |
     +-----------------+       +-----------------+
     |  Cart Service   | <---> | Payment Service |
     +-----------------+       +-----------------+
                        \ 
                         +-----------------+
                         |  Order Service  |
                         +-----------------+
```

---

## 3. Difference Between Monolithic and Microservices

| Feature     | Monolithic App              | Microservices App                  |
| ----------- | --------------------------- | ---------------------------------- |
| Structure   | Single big application      | Many small independent services    |
| Deployment  | Deploy everything together  | Deploy each service separately     |
| Scaling     | Scale entire app            | Scale only needed services         |
| Development | One large team              | Small independent teams            |
| Failure     | One bug may crash whole app | One service crash doesn’t stop all |
| Example     | Old Banking System          | Netflix, Amazon, Uber              |

---

## 4. Simple Real-Life Example

* **Monolithic = Big Restaurant Kitchen** → One chef handles all cooking. If chef is sick, kitchen closes.
* **Microservices = Food Court** → Different stalls for pizza, burger, sweets. If one stall closes, others still work.
---

## 🔹 What Are Microservices?

* Microservices are **small, independent services**.
* Each service does **one specific task or feature**.
* Together, all services form a **complete application**.

**ASCII Diagram (Microservices Structure):**

```
   +-------------+   +---------------+   +--------------+
   | User Service|   | Order Service |   | Payment Serv.|
   +-------------+   +---------------+   +--------------+
          \              |                  /
           \             |                 /
            \            |                /
             +----------------------------------+
             |   Food Delivery Application      |
             +----------------------------------+
```

---

## How Microservices Work

* Each service **runs in its own process** (separate runtime).
* Services communicate via **REST APIs** (lightweight calls using HTTP).
* REST API → Like sending a message:

  * User clicks **“Place Order”**
  * App calls **Order Service API**
  * Order Service talks to **Payment Service API**
  * Payment Service updates **Notification Service API**.

---

## Key Features of Microservices

* **Autonomous** → Each service works independently but connects with others.
* **Cloud-Ready** → Easy to deploy on **AWS, Azure, GCP**.
* **Smart Deployment** → Deploy only the needed service.
* **Scalable** → Add more instances of only the busy service.

**Example:**

* During peak hours, only **Order Service** may need scaling, not all services.

---

## Benefits of Using Microservices

1. **📦 Simple Management** → Easier to build, update, and manage.
2. **🛠️ Parallel Development** → Teams work on different services at the same time.
3. **🚀 Quick Updates** → Update one service without stopping the whole app.
4. **🔒 Reliability** → If one service fails, others still run.

---

## 🍔 Example: Food Delivery App with Microservices

* **User Service** → Manages login & profile.
* **Restaurant Service** → Manages restaurants & menus.
* **Order Service** → Handles orders.
* **Payment Service** → Manages payments securely.
* **Notification Service** → Sends order updates (SMS/Email/Push).

👉 Each service runs **independently**, can be updated without affecting others.

---
# Challenges with Microservices – Bounded Context
---



## 🔹 What is Bounded Context?

* A **bounded context** means **defining clear boundaries** around what each microservice should and should not do.
* Challenge: Deciding **where to cut** a big application into smaller services.

---

## 🔹 How to Identify Microservice Boundaries in a Monolith Application

### 1. From Monolith to Microservices

* A big monolithic app can be split into **5, 10, 20, or 100 microservices**.
* But finding the **right boundaries** is not easy.

---

### 2. Main Questions to Ask

* ❓ What is the **responsibility** of each microservice?
* ❓ What **should not** be part of it?
* ❓ How will services communicate with each other?

---

### 3. Challenge in New Applications

* At the start of a new project:

  * Business requirements are not fully clear.
  * Microservice boundaries are **hard to define**.

---

### 4. Evolutionary Process

* Microservice design is an **evolutionary journey**.
* We may not get boundaries correct in the **first attempt**.
* Over time, we refine them.

---

### 5. Trial and Error

* Splitting services requires **experimentation**.
* It’s okay to try different designs and **adjust later**.

---

### 6. Use Domain-Driven Design (DDD)

* Follow **DDD practices**:

  * Understand **business domains** (e.g., Orders, Payments, Inventory).
  * Split microservices based on these domains.
* Example: In an **E-commerce app**, keep **Order Service** separate from **Payment Service**.

---

### 7. Continuous Improvement

* Microservice structure should **evolve with business needs**.
* As we learn more → **keep improving boundaries**.

---

## Example: Splitting a Monolithic E-commerce App

**Monolithic Application:**

```
+--------------------------------------------------+
| Users | Products | Orders | Payments | Notifications |
+--------------------------------------------------+
```

**After Splitting into Microservices:**

```
+------------------+   +-----------------+   +------------------+
| User Service     |   | Product Service |   | Order Service     |
+------------------+   +-----------------+   +------------------+
            |                      |                     |
            +----------------------+---------------------+
                        |
            +----------------------+
            | Payment Service      |
            +----------------------+
                        |
            +----------------------+
            | Notification Service |
            +----------------------+
```

---

## Key Takeaways

* Defining **boundaries** is one of the **biggest challenges** in microservices.
* It’s an **iterative process** → Learn, improve, and adapt.
* **DDD (Domain-Driven Design)** helps to identify good service boundaries.
* Goal → Each service should have **clear responsibility** and be **independent**.

---
# **Challenges with Microservices – Configuration Management**
---

## What Is the Issue?

* Each **microservice needs configuration** to run.
* As services and environments increase → managing configs becomes complex.

---

## Example Scenario

* Suppose we have:

  * **10 microservices**
  * **5 environments** → dev, test, stage, UAT, prod
  * **50 total instances** (multiple copies per environment)

➡️ This means **hundreds of configuration files** to manage.

---

## The Challenge

* Each service & environment may require **different config values** like:

  * Database URLs
  * API keys
  * Port numbers
  * Environment variables

* **Problems with manual configuration:**

  * ❌ Time-consuming
  * ❌ Error-prone
  * ❌ Hard to track changes
  * ❌ Security risks (e.g., passwords stored in plain text)

---

## Why It Matters

If configs are not managed properly →

* ❌ Services may **fail to start**.
* ❌ Wrong configs in **production** → big failures.
* ❌ **Security risks** if secrets (passwords, tokens) are leaked.

---

## Ideal Solution: Centralized Configuration Management

A **centralized system** should handle all configs.

### Features it should support:

1. **Environment-specific configs**

   * Example: Different DB URL for dev vs prod.
2. **Service-level configs**

   * Each service has its own config file.
3. **Secrets management**

   * Secure storage for passwords, tokens, API keys.
4. **Dynamic updates**

   * Services should update configs **without restart**.

---

## 🔧 Tools Commonly Used

* **Spring Cloud Config** (popular in Java + Spring Boot).
* **HashiCorp Vault** (for secrets management).
* **Kubernetes ConfigMaps & Secrets**.

---

## ASCII Diagram: Centralized Config in Microservices

```
         +--------------------------+
         |   Central Config Server  |
         +--------------------------+
             |        |        |
   ----------+--------+--------+----------
   |                 |                  |
+---------+     +---------+        +---------+
| Service |     | Service |        | Service |
|   A     |     |   B     |        |   C     |
+---------+     +---------+        +---------+
   |                |                  |
 [ Dev ]         [ Test ]           [ Prod ]
```

---

## Key Takeaways

* Config management is a **major challenge** in microservices.
* **Centralized config server** makes it easier.
* Supports environment-specific values, secrets, and dynamic updates.
* Saves time, reduces errors, and increases security.

---
# **Challenges with Microservices – Dynamic Scaling and Load Balancing**
---

# Dynamic Scaling and Load Balancing in Microservices

---

## What Is Dynamic Scaling?

* **Dynamic Scaling** = Automatically **increase or decrease** the number of service instances based on **traffic/load**.
* Goal: Save resources + maintain good performance.

---

## Example Scenario

* Initially → **2 instances of Microservice2**.
* Traffic increases → Scale up to **10 instances**.
* Traffic decreases → Scale down back to **2 instances**.

Scaling is **automatic**, no need for manual effort.

---

## Benefits of Dynamic Scaling

1. 💰 Saves cost → Removes unused instances.
2. ⚡ Ensures high availability → Adds new instances during high demand.
3. 🔄 Reduces manual work → Scaling handled automatically.

---

## ⚖️ What Is Load Balancing?

* **Load Balancing** = Distributing user requests across **all available instances** of a service.
* Prevents:

  * One instance being **overloaded**
  * Other instances being **idle**

---

## 🧩 How It Should Work

1. If 1 instance of **Microservice1** and 4 instances of **Microservice2** exist → Load is spread evenly across 4 instances of Microservice2.
2. When new instances of Microservice2 start → Load balancer **automatically includes them** in traffic distribution.

---

## ✅ Required Capabilities

1. **Automatic Instance Management**

   * Add new instances when traffic increases.
   * Remove idle instances when traffic decreases.

2. **Dynamic Load Balancing**

   * Spread requests **fairly** across all instances.
   * Ensure all active services are **fully utilized**.

---

## 🚀 Tools That Help with Scaling & Load Balancing

* **Kubernetes (K8s):** For container orchestration + auto-scaling.
* **Service Mesh (Istio, Linkerd):** For smart routing + traffic management.
* **Cloud Auto-Scaling:**

  * AWS Auto Scaling
  * Google Cloud Auto Scaling
  * Azure Auto Scaling

---

## 🖼️ ASCII Diagram – Scaling and Load Balancing

### Initial State (Few Instances)

```
      [ Users ]
         |
    +-------------+
    | LoadBalancer|
    +-------------+
         |
   -----------------
   |       |       |
[MS2-1] [MS2-2] [MS2-3]    <-- 3 instances of Microservice2
```

### After Scaling (High Traffic)

```
      [ Users ]
         |
    +-------------+
    | LoadBalancer|
    +-------------+
         |
   -------------------------------------
   |   |   |   |   |   |   |   |   |   |
[MS2-1]...[MS2-10]      <-- 10 instances of Microservice2
```

👉 Load balancer spreads requests across **all 10 instances**.

---

## 🎯 Key Takeaways

* **Dynamic Scaling** = Add/remove service instances automatically.
* **Load Balancing** = Share requests evenly among instances.
* Together → They ensure **performance, cost savings, and reliability**.
* Tools like **Kubernetes + Service Mesh + Cloud Auto-Scaling** make this possible.

---
# **Challenges with Microservices – Visibility**
---

# 🔍 Visibility in Microservices

---

## 📌 Problem

* In **monolithic apps**, everything is in one place → easy to debug.
* In **microservices**, functionality is **spread across many services** (sometimes 10, 50, or even 100).
* If something goes wrong, it's hard to **know where the bug happened**.

👉 Example:

* User clicks "Buy Now"
* Request goes through **Service A → Service B → Service C**
* If the request fails, we must know *which service failed*.

---

## 🧾 Centralized Logging

* All microservices send logs to **one central place**.
* Helps trace:

  * What happened to a **user request**.
  * Which microservice caused the **error**.

👉 Example:
Instead of checking **Service A logs**, **Service B logs**, **Service C logs** separately →
You can search in **one system** and see the **full journey of the request**.

🔧 Tools used:

* **ELK Stack** (Elasticsearch + Logstash + Kibana)
* **Fluentd + Grafana Loki**
* **Graylog**

---

## 📈 Monitoring

* With **hundreds of microservices**, we can’t manually check each one.
* We need tools to:

  * Detect **down / failed services** automatically.
  * Monitor **CPU, Memory, Disk**.
  * Track **performance issues** in real time.

👉 Example:

* If **Service B** is consuming 90% CPU → Alert should be raised immediately.

---

## 🤖 Automation

* Monitoring and alerts should be **automatic**.
* System should:

  * Notify teams if service is **down**.
  * Raise alerts for **low disk space**.
  * Prevent human delay in fixing issues.

👉 Example:
If "Payment Service" goes down → system **sends alert** on Slack/Email immediately.

---

## 👁️ Good Visibility

With good logging + monitoring:

* Bugs can be found **faster**.
* System runs **smoothly**.
* Teams make **better decisions**.
* Major failures can be **prevented early**.

---

## ⚡ ASCII Diagram – Centralized Logging & Monitoring

```
   +-------------+       +-------------+       +-------------+
   |  Service A  | --->  |             |       |             |
   +-------------+       |             |       |             |
                         | Centralized | <---  |  Service B  |
   +-------------+       |   Logging   |       +-------------+
   |  Service C  | --->  |   &         |
   +-------------+       | Monitoring  | <---  +-------------+
                         |   System    |       |  Service D  |
                         +-------------+       +-------------+

                   📊 Logs + Metrics in one place
                   🔔 Alerts when problem occurs
```
---
# **Challenges with Microservices – Pack of Cards – Risks of Poorly Designed Microservices**
---

## ⚠️ Risks of Poor Design

* Microservices depend too much on each other.
* One service may call another, which then calls another → creates a long dependency chain.

---

## 🧱 Fragile Architecture – Like a Pack of Cards

* Some microservices become **central and critical**.
* If one important service fails:

  * Other services depending on it also fail.
  * The entire application can collapse.

**Example:**

* Microservice A → calls Microservice B → calls Microservice C
* If **C goes down** → B fails → then A fails → whole chain breaks.

---

## ✅ Why Fault Tolerance Is Important

* Fault tolerance = system continues working even if one service fails.
* Benefits:

  * Keeps system stable.
  * Improves reliability.
  * Builds user trust.

---

## 🛠️ How to Achieve Fault Tolerance

1. **Circuit Breakers** – stop calls to failing services (e.g., Netflix Hystrix, Resilience4j).
2. **Timeouts & Retries** – avoid waiting too long and retry safely.
3. **Fallback Methods** – provide default response if service fails.
4. **Redundancy** – run multiple instances of critical services.
5. **Asynchronous Messaging** – use message brokers (Kafka, RabbitMQ) to reduce direct dependencies.

---

📌 **In short:**
Poorly designed microservices are fragile like a pack of cards.
Good design with **fault tolerance** makes the system stable and reliable.

---
