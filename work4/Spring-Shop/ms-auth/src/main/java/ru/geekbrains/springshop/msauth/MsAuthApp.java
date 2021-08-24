package ru.geekbrains.springshop.msauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.geekbrains.springshop")
public class MsAuthApp {

	public static void main(String[] args) {
		SpringApplication.run(MsAuthApp.class, args);
	}

}
