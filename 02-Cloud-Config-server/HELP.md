# Here we are trying to connect spring cloud config server to Git hub repository.
> For this need to add one more annotation @EnableConfigServer.
> For more details check the applicatin.properties file
> We can store all the configuration for different environments of different microservices in 
	just one place in a centralized location and 
> Spring cloud config server can be used to expose that configuration to all the microservices.
> We have establish the connection between SprinCloudConfigServer and the local Git repository.

# Limit-Services
## #@ConfigurationProperties("limits-service")
> It tells Spring Boot: Bind all the properties starting with limits-service. 
		from configuration files to the fields in this class.
		
#  Access URL
### http://localhost:2025/limits

# spring.cloud.config.server.git.uri=file:///D:/git/msconfig
>	This is the path to our local Git-based config repository. It could also be a GitHub URL.
###	spring.cloud.config.server.git.uri=https://github.com/amitkvaio/msconfig.git	

# spring.cloud.config.server.git.default-label=main
> This tells the config server to use the main branch from our Git repository.

# Why we are Seeing Both default and dev Properties?
>	What Happens Internally?
		When we try to access this url
			http://localhost:8888/centralized/dev
				Spring Cloud Config Server will do property merging:
					 * First, it loads properties from:
					 * centralized.properties (default profile)					
					 * Then, it overrides with properties from:
					 * centralized-dev.properties (dev profile)

> Higher profiles override lower profiles.

# Don't create centralized.properties (if we don't want defaults)
>	Only define profile-specific files like:
    centralized-dev.properties
    centralized-prod.properties

# URL						What we Get
>
.../centralized/default		Only default file
.../centralized/dev			default + dev (dev overrides default)
.../centralized/prod		default + prod (prod overrides default)

#======================================================================================
# How Spring Cloud Config Server works without writing any controller manually.
## Why URLs like http://localhost:8888/centralized/dev Work Automatically? without controller!
>	Since We are using Spring Cloud Config Server dependency <spring-cloud-config-server>.
>	This auto-configures everything needed to expose property files via HTTP endpoints.
>	Spring Cloud provides a pre-built controller that listens on:
>	http://<host>:<port>/{application}/{profile}
>   We does not need to define this controller ourself — it's automatically available when we include
	dependency.
	http://localhost:8888/centralized/default
	http://localhost:8888/centralized/dev
	http://localhost:8888/centralized/prod

	



















