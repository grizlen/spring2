package ru.geekbrains.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@SpringBootApplication(scanBasePackages = {"ru.geekbrains.market", "ru.geekbrains.market.core.feign"})
@EnableEurekaClient
@EnableFeignClients
public class OrdersApp {
    public static void main(String[] args) {
        SpringApplication.run(OrdersApp.class, args);
    }
}
