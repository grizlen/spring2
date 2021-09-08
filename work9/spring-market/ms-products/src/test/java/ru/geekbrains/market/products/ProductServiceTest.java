package ru.geekbrains.market.products;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.market.core.exceptions.ResourceNotFoundException;
import ru.geekbrains.market.products.model.Product;
import ru.geekbrains.market.products.repository.ProductRepository;
import ru.geekbrains.market.products.service.ProductService;
import ru.geekbrains.market.routing.dto.ProductDTO;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = ProductService.class)
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void getAllTest() {
        Product product = new Product();
        product.setId(1l);
        product.setTitle("Нужная вещь");
        product.setPrice(1000f);
        List<Product> list = Collections.singletonList(product);

        Mockito
                .doReturn(list)
                .when(productRepository)
                .findAll();

        List<ProductDTO> result = productService.getAll();
        Assertions.assertEquals("Нужная вещь", result.get(0).getTitle());
    }

    @Test
    public void getByIdlTest() {
        Product product = new Product();
        product.setId(1l);
        product.setTitle("Нужная вещь");
        product.setPrice(1000f);
        Optional<Product> optionalProduct = Optional.of(product);

        Mockito
                .doReturn(optionalProduct)
                .when(productRepository)
                .findById(1l);

        ProductDTO result = productService.getById(1l);
        Assertions.assertEquals("Нужная вещь", result.getTitle());

        Optional<Object> empty = Optional.empty();

        Mockito
                .doReturn(empty)
                .when(productRepository)
                .findById(2l);

        Assertions.assertThrows(ResourceNotFoundException.class, () -> productService.getById(2l));
    }
}
