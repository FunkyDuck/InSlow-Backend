package tk.inslow.inslowapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication
public class InslowapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(InslowapiApplication.class, args);
	}

}
