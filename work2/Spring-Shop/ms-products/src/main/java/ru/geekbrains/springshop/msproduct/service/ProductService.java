package ru.geekbrains.springshop.msproduct.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import ru.geekbrains.springshop.libdb.dto.ProductDTO;
import ru.geekbrains.springshop.msproduct.entity.Product;
import ru.geekbrains.springshop.msproduct.repository.ProductRepository;
import ru.geekbrains.springshop.msproduct.repository.specifications.ProductSpecifications;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private static final int ITEMS_ON_PAGE = 5;

    private final ProductRepository productRepository;

    public Page<ProductDTO> getProducts(MultiValueMap<String, String> params, int page, int pageSize) {
        Specification<Product> spec = ProductSpecifications.build(params);
        PageRequest pr = PageRequest.of(page < 0 ? 0 : page,
                pageSize < ITEMS_ON_PAGE ? ITEMS_ON_PAGE : pageSize);
        return productRepository.findAll(spec, pr).map(this::toProductDTO);
    }

    public Optional<ProductDTO> getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return Optional.ofNullable(toProductDTO(product.get()));
    }

    private ProductDTO toProductDTO(Product product) {
        ProductDTO result = new ProductDTO();
        result.setId(product.getId());
        result.setTitle(product.getTitle());
        result.setPrice(product.getPrice());
        return result;
    }
}
