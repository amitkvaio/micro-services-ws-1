# How to run multiple instance of above application on different port?
>
To run multiple instances of a Spring Boot application on different ports, we can use the 
	<-Dserver.port=PORT> VM argument when starting the application.
-Dserver.port=8002,3,4 - VM arguments

# Use Command Line / VM Options
## Instance 1
> java -jar myapp.jar -Dserver.port=8001

## Instance 2
> java -jar myapp.jar -Dserver.port=8002

## Instance 3
> java -jar myapp.jar -Dserver.port=8003


## Using Eclipse IDE
>	-Dserver.port=8002
	-Dserver.port=8003
	-Dserver.port=8004

##It will get the output like below
>
id	1000
from	"USD"
to	"INR"
conversionMultiple	50
environment	"8002"













