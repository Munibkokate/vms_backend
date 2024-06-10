package net.vehicle.vmsbackend;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//This annotation is used to mark the main class of a Spring Boot application
@SpringBootApplication

public class VmsBackendApplication {

	 // Main method which serves as the entry point for the Spring Boot application
	public static void main(String[] args) {
		SpringApplication.run(VmsBackendApplication.class, args);
	}	
}
