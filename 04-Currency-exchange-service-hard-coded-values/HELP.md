# spring.config.import=optional:configserver:http://localhost:8888
> Try to load the configuration from the given location, but do not fail if it's unavailable.

#spring.config.import: 
>Used to import configuration from another source, such as a remote config server.

#configserver:http://localhost:8888: 
>Refers to a Spring Cloud Config Server running at that URL.

# optional:: 
> If the config server is down, unavailable, or mis configured, the application will not fail to start. 
	It will ignore the error and continue running with local/default configuration.

#Access URL
#Currency Exchange Service
#http://localhost:8000/currency-exchange/from/USD/to/INR







