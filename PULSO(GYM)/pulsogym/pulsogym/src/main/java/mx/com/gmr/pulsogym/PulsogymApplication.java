package mx.com.gmr.pulsogym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import mx.com.gmr.app.security.annotation.ActivarJwt;


@SpringBootApplication(
		scanBasePackages  = {
		"mx.com.gmr.pulsogym",
		"mx.com.gmr.app.security"})
public class PulsogymApplication {

	public static void main(String[] args) {
		SpringApplication.run(PulsogymApplication.class, args);
	}

}
