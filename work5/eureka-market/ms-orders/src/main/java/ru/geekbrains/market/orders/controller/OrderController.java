package ru.geekbrains.market.orders.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.market.core.dto.OrderDTO;
import ru.geekbrains.market.orders.service.OrderService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public String test() {
        return "OrderController";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/api/v1")
    public List<OrderDTO> getOrders() {
        return orderService.getOrders();
    }
}
