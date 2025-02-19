package caryarit.inditex.precios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan({"caryarit.inditex.precios.domain.usecase.impl", "caryarit.inditex.precios.application.controller"})
@EnableJpaRepositories("caryarit.inditex.precios.domain.repository")
@EnableTransactionManagement
public class PreciosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PreciosApplication.class, args);
	}

}
