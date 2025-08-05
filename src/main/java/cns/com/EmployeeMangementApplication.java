package cns.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EmployeeMangementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeMangementApplication.class, args);
		System.out.println("Employee Management project is running =============================");
	}

}
