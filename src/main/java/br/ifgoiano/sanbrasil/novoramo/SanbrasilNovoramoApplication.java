package br.ifgoiano.sanbrasil.novoramo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SanbrasilNovoramoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SanbrasilNovoramoApplication.class, args);
	}

}
