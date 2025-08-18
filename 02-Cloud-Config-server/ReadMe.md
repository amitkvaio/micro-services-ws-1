## **Reading the properties from the git repository.**

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-server</artifactId>
</dependency>
```
---
# Spring Cloud Config Server with Git Repository
---
```properties
spring.application.name=Cloud-Config-server
server.port=8888
spring.cloud.config.server.git.uri=https://github.com/amitkvaio/msconfig.git
spring.cloud.config.server.git.default-label=main
```

# **URL**
```
# http://localhost:8888/centralized/default  
# http://localhost:8888/centralized/dev  
# http://localhost:8888/centralized/prod  
```