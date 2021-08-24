package ru.geekbrains.springshop.msproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(scanBasePackages = "ru.geekbrains.springshop")
public class MsProductsApp {

	public static void main(String[] args) {
		SpringApplication.run(MsProductsApp.class, args);
	}

}
