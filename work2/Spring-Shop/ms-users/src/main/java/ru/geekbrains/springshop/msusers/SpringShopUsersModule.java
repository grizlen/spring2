package ru.geekbrains.springshop.msusers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"ru.geekbrains"})
public class SpringShopUsersModule {

	public static void main(String[] args) {
		SpringApplication.run(SpringShopUsersModule.class, args);
	}

}
