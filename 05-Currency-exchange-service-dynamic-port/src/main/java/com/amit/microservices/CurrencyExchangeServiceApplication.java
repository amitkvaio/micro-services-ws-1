package com.amit.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CurrencyExchangeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangeServiceApplication.class, args);
		System.out.println("CurrencyExchangeServiceApplication has been started!!");
	}
}

/*


How to run multiple instance of above application on different port?
-Dserver.port=8002,3,4 - VM arguments
Here we are setting the environment port in which current instance is running and same getting printed.

will get the output like below
id	1000
from	"USD"
to	"INR"
conversionMultiple	50
environment	"8002"

*/
