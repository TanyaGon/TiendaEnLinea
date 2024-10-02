package sv.edu.udb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class TiendaEnLineaApplication {

	public static void main(String[] args) {

		SpringApplication.run(TiendaEnLineaApplication.class, args);
	}

}
