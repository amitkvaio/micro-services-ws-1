# Netflix Ribbon
	
#Types of Load Balancing:
#************************

#There are two types of load balancing

##Server Side Load Balancing: 
>	Server side load balancing is a monolithic It applies between the client and the server. 

##Client-Side Load Balancing: 
>	The client holds the list of server’s IPs so that it can deliver the requests. 
	The client selects an IP from the list, randomly, and forwards the request to the server.


#Netflix Ribbon
#****************
'''
<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
</dependency>
'''

>
* Netflix Ribbon is a Part of Netflix Open Source Software (Netflix OSS). 
* It is a cloud library that provides the client-side load balancing. 
* It automatically interacts with Netflix Service Discovery (Eureka) because it is a member of the Netflix family.


### The Ribbon mainly provides client-side load balancing algorithms. 
>	It is a client-side load balancer that provides control over the behavior of HTTP and TCP client. 
>	The important point is that when we use Feign, the Ribbon also applies.
	
#Features of Ribbon
#*******************
1 Load balancing
2 Fault tolerance
3 Multiple protocol support in Asynchronous model
4 Caching and batching

### We have configured the Ribbon and distributed the load between the three services.


#URL access
>Currency Exchange Service
 http://localhost:8000/currency-exchange/from/USD/to/INR

#Currency Conversion Service
## http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10
## http://localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/10

#How to run this application(IMPORTANT)
#*****************************************
>
1. First run 06-Currency-exchange-service-configure-jpa with five instance with port number 8000, 8001,8002,8003,8004
2. Start this application and check the above url
3. http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10
4. Is the rest template call port number is hard coded so refreshing the url port will not change
5. http://localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/10
6. is the feign client and with ribbon load balance refresh this url you will see the port number is changing.
7. However we are able to achieve the load balance but here we are hard-coding the port number
8. Think about what happen if we have n numbers of instance, its difficult to write that number of port
9. We can do better by using the Eureka naming server.














