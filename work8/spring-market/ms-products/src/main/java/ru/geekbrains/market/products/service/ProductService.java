package ru.geekbrains.market.products.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.market.core.exceptions.ResourceNotFoundException;
import ru.geekbrains.market.products.model.Product;
import ru.geekbrains.market.routing.dto.ProductDTO;
import ru.geekbrains.market.products.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDTO> getAll() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> result = products.stream().map(this::productToDTO).collect(Collectors.toList());
        return result;
    }

    public ProductDTO getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product with id: " + id + " not found."));
        return productToDTO(product);
    }

    private ProductDTO productToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice());
        return dto;
    }
}
