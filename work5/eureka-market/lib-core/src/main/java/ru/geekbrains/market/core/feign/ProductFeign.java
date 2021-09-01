package ru.geekbrains.market.core.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.geekbrains.market.core.dto.ProductDTO;

@FeignClient("ms-products")
public interface ProductFeign {
    @GetMapping
    public String test();
    @GetMapping("/api/v1/{id}")
    public ProductDTO getProductById(@PathVariable Long id);
}
