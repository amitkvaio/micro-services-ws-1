# 10-Register-Currency-conversion-service-with-eureka-naming-server

### Will connect Currency-Conversion-services to Eureka naming server for that need to add one more dependency to the pom.xl
```
<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-eureka</artifactId>
			<version>1.4.7.RELEASE</version>
</dependency>
```

# @EnableDiscoveryClient
>   To Register Currency-Conversion-services with the Eureka naming server 
	need to add @EnableDiscoveryClient annotation to the CurrencyConversionServicesApplicationUsingFeign class

# Eureka Configuration
> After this need to configure the URL for Eureka naming server, to do this need to add below property 
		in the application.property file
```        
eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka
```

#URL access
##Check the Currency Exchange Service or not by running below url
>http://localhost:8000/currency-exchange/from/USD/to/INR

##Try to run below Currency Conversion Service, check environment 8000 value will be changing.
>http://localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/10

```
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


#How to run the this application
---
>
First run 09-Eureka-naming-server-setup application
>
Second run 10-Register-Currency-conversion-service-with-eureka-naming-server application
>
Run more then one instance of 
10-Register-Currency-conversion-service-with-eureka-naming-server By changing the port
>
Third run 11-Register-Currency-conversion-service-with-eureka-naming-server application
>
Check on Eureka browser weather
   10-Register-Currency-conversion-service-with-eureka-naming-server 
	11-Register-Currency-exchange-service-with-Eureka-naming-server, services has registered or not.
---










