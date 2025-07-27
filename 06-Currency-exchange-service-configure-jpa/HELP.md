# Currency exchanges service with jpa with h2 database.

# Note :
 
>1.	Using inbuilt database i.e h2 data base
>2.	Check the property file for configuration.
>3.	spring.jpa.defer-datasource-initialization=true
>4.	Above property will called the data.sql file at the time of startup- it will create the table
	and insert the data.
>5.	h2 console access URL http://localhost:8000/h2-console
>6.	http://localhost:8000/currency-exchange-hard-coded/from/USD/to/INR - will return the hard-coded values.
>7.	http://localhost:8000/currency-exchange-jpa/from/USD/to/INR - will read the required data from the db and
	return the same values.
>8.	For each server startup existing data in h2 database will be wipe out.	


# H2 database configuration
>
spring.jpa.show-sql=true
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:amitdb
spring.jpa.defer-datasource-initialization=true

# Oracle data source details
### spring.datasource.url=jdbc:oracle:thin:@localhost:1521:XE
### spring.datasource.username=system
### spring.datasource.password=system
### spring.datasource.driver-class-name=oracle.jdbc.driver.OracleDriver
### spring.datasource.platform=oracle

#h2 console 
###	select * from CURRENCY_EXCHANGE;
###	http://localhost:8000/h2-console

#Access URL
###	http://localhost:8000/currency-exchange-hard-coded/from/USD/to/INR
###	http://localhost:8000/currency-exchange-jpa/from/USD/to/INR
###	http://localhost:8000/currency-exchange-jpa/from/EUR/to/INR
###	http://localhost:8000/currency-exchange-jpa/from/AUD/to/INR





