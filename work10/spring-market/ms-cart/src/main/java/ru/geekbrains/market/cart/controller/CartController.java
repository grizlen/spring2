package ru.geekbrains.market.cart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.cart.service.CartService;
import ru.geekbrains.market.routing.dto.CartItemDTO;
import ru.geekbrains.market.routing.dto.ProductRequestDTO;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public List<CartItemDTO> getAll() {
        return cartService.getAll();
    }

    @PostMapping
    public void addProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        cartService.addProduct(productRequestDTO);
    }
}
