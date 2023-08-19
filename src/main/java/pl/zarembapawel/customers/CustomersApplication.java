package pl.zarembapawel.customers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.zarembapawel.customers.config.MapperConfig;

@SpringBootApplication(scanBasePackages = "pl.zarembapawel.customers")
public class CustomersApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomersApplication.class, args);
	}

}
