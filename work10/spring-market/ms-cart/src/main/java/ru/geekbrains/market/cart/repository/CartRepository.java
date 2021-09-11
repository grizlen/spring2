package ru.geekbrains.market.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.market.cart.model.CartItem;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {
}
