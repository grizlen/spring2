package ru.geekbrains.market.products;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.geekbrains.market.core.exceptions.ResourceNotFoundException;
import ru.geekbrains.market.products.model.Product;
import ru.geekbrains.market.products.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void findAllTest() {
        List<Product> products = productRepository.findAll();
        Assertions.assertEquals(5, products.size());
    }

    @Test
    public void findByIdTest() {
        Optional<Product> product = productRepository.findById(1l);
        Assertions.assertEquals("Хлеб", product.get().getTitle());
    }

}
