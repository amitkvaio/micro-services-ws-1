# In this project we will exposing the hard-coded values on the UI.
>	Reading the values from configuration file/property file at the centralize location.
		Before reading the data from the centralized location spring cloud config server should 
		be start else it will give the default values.
		
#@RefreshScope
> @RefreshScope annotation is used to load the configuration properties value from the Config server.
for newly added property in the configuration file/properties


#URL to access
>	http://localhost:2021/hard-coded-limits
	http://localhost:2021/hard-coded-limits_
	http://localhost:2021/reading-from-property-file-limits

#Note::
##First start the spring cloud config server here just start 01-Spring-Cloud-Config-server.
##Second start the 03-MS-Cloud-config-client and hit the third url to read the values from centralized location.

>spring-cloud-starter-config server connect to spring.cloud.config.uri (http://localhost:8888) 
>server to make us to read the property from the centralize location.

#spring.application.name=centralized
>This is the name of your application. Spring Cloud Config Server will use this name to fetch the 
	correct config file (like centralized-prod.properties) from the config repository.

#spring.cloud.config.uri=http://localhost:8888
>This is the URL where your Spring Cloud Config Server is running. Your application will contact this server 
	to fetch configuration.

#spring.profiles.active=prod	
> This activates the prod profile. The config server will try to fetch centralized-prod.properties or centralized-prod.yml.
	
#spring.cloud.config.server.git.default-label=main
> This tells the config server to use the main branch from your Git repository.

#limit-service.minimum=100	
> A custom property to define the minimum limit value.

#limit-service.maximum=300	
> A custom property to define the maximum limit value.

#limit-service.name=Default-properties  
> A custom property to define the name of the property environment.














