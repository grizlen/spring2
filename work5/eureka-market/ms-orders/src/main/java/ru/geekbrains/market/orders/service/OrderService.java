package ru.geekbrains.market.orders.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;
import ru.geekbrains.market.core.dto.OrderDTO;
import ru.geekbrains.market.core.feign.ProductFeign;
import ru.geekbrains.market.orders.model.Order;
import ru.geekbrains.market.orders.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

//    @Autowired
    private ProductFeign productFeign;

    @Autowired
    public void setProductFeign(ProductFeign productFeign) {
        this.productFeign = productFeign;
    }

    public List<OrderDTO> getOrders() {
        return orderRepository.findAll().stream().map(this::toOrderDTO).collect(Collectors.toList());
    }

    private OrderDTO toOrderDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setProduct_id(order.getProduct_id());
        dto.setProduct_title(productFeign.getProductById(order.getId()).getTitle());
        dto.setCount(order.getCount());
        return dto;
    }
}
