package SpringJdbcExample.springjdbcexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan("com.*")
@EnableCaching
public class SpringjdbcexampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringjdbcexampleApplication.class, args);
	}
}
