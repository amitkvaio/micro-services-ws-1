#Here we are just setting the Eureka naming server
#-------------------------------------------------
> In the previous Example - 08-Currency-conversion-service-using-feign
	We have hard coded currency-exchange and URL as shown below
	@FeignClient(name="currency-exchange", url="localhost:8000")
	

# If I would want to get the currency conversions then its required to talk with different instance of currency exchange.
	
# What do I need to do?
#-------------------------
>	As per previous example we will do like blow
	I will go here and change the localhost:8001, localhost:8002, localhost:8003 and so on.

####Fein, provide an option where we can hard code multiple URLs in here, even that would not be a good solution because.
#####	@FeignClient(name="currency-exchange", url="localhost:8000,localhost:8001,localhost:8002")

###### Let's say 8000 went down and let's say a new instance was brought up on 8000 or some other port
##### Then we must change the configuration of this application of the code, of this application all
#---------------------------------------------------------------------      

# What would happen is in a microdevices architecture, all the instances of all the micro services would register with a service registry.


#---------------------------------------------------------------------------------------------------------------- 
## The Currency Exchange Service would register with the service registry and all the other micro services also registered with the service registry.

# Currency conversion micro service wants to talk to the currency exchange, make service.
---------------------------------------------------------------------------------------
#It would ask the service registry, hey, what are the addresses of the currency exchange?
##### he service registry would return those back to the currency and we should make a service.
##### Ad then the currency conversion micro service can send the request out to the currency exchange micro service.

'''
pom.xml
-------
<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
''''





