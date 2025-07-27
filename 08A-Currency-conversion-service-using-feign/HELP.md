#08A-Currency-conversion-service-using-feign
# Access URL
>http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10
>http://localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/10

#Start the services in this order
>
01-Spring-Cloud-Config-server
06-Currency-exchange-service-configure-jpa
08A-Currency-conversion-service-using-feign

# Problem ?
>
We had to write a lot of tedious code around RestTemplate to get the Currency Conversion
service to talk with the Currency Exchange Microservice.

>To make a simple REST API call, we need to write about 20 lines of code. 
	And just imagine what would happen if we have hundreds of Microservices, they are calling each other?

> So,we need to repeat this kind of code everywhere.
And that's where Spring Cloud provides us with a framework called Feign.

> Feign makes it really, easy to call other Microservices and to make use of Feign
we need to add a specific dependency into our Currency Conversion service.

# Feign Dependency
>
<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>

> We would want to be able to talk to the currency-exchange-service from the CurrencyConversionController.

>
Feign is the rest service client: its make it easy to call rest full web service.
@FeignClient is used to talk with external micro services.
	name = name of the currency-exchange microservices
	URL = where currency-exchange ms running.
	
#Types of Load Balancing:
************************
>There are two types of load balancing

#1.Server-Side Load Balancing
>   The load balancer sits between the client and the server.
    It is the responsibility of the server (or infrastructure) to decide which server will handle the request.
    The client does not know which server is handling the request.
    Mostly used in traditional or monolithic applications.

# Example:
>   We open www.amazon.com in your browser.
    Our request first goes to a load balancer.
    The load balancer checks available servers and sends your request to Server 2 (if it's free).
    The server processes the request and sends the result back to the client.

#2.Client-Side Load Balancing
>   The client holds the list of all server instances/IPs.
    The client itself decides which server to call.
    Often used in microservices architecture.
    Example libraries that support this: Spring Cloud LoadBalancer, Netflix Ribbon (deprecated).

# Example:
>   A microservice wants to talk to another microservice (currency-exchange-service).
    The RestTemplate (with load balancing) picks one of the available server instances from a list:
        http://localhost:8001
        http://localhost:8002
    The client chooses one randomly or round-robin and makes the call.


