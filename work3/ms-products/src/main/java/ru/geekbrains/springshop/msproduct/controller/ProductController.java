package ru.geekbrains.springshop.msproduct.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springshop.libcore.exceptions.ResourceNotFoundException;
import ru.geekbrains.springshop.libcore.dtos.ProductDTO;
import ru.geekbrains.springshop.msproduct.service.ProductService;

@RestController
@RequestMapping(path = "api/v1/product")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Page<ProductDTO> getProducts(
            @RequestParam(required = false) Float min_price,
            @RequestParam(required = false) Float max_price,
            @RequestParam(required = false) String title,
//            @RequestParam MultiValueMap<String, String> params,
            @RequestParam(name = "page", defaultValue = "0") Integer page
    ){
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        if (min_price != null) {
            params.add("min_price", String.valueOf(min_price));
        }
        if (max_price != null) {
            params.add("max_price", String.valueOf(max_price));
        }
        if (title != null) {
            params.add("title", title);
        }
        return productService.getProducts(params, page, 2);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ProductDTO getProductById(@PathVariable Long id) {
        return productService.getProductById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product with Id: " + id + " not found.")
        );
    }

}
