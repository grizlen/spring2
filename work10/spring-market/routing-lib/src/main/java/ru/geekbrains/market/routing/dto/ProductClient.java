package ru.geekbrains.market.routing.dto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("ms-products")
public interface ProductClient {

    @GetMapping("/api/v1/products")
    public List<ProductDTO> getAll();

    @GetMapping("/api/v1/products/{id}")
    public ProductDTO getById(@PathVariable Long id);
}
