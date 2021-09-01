package ru.geekbrains.market.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.market.orders.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
