package ru.geekbrains.market.cart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.market.cart.model.CartItem;
import ru.geekbrains.market.cart.repository.CartRepository;
import ru.geekbrains.market.routing.dto.CartItemDTO;
import ru.geekbrains.market.routing.dto.ProductClient;
import ru.geekbrains.market.routing.dto.ProductDTO;
import ru.geekbrains.market.routing.dto.ProductRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductClient productClient;

    public List<CartItemDTO> getAll() {
        List<CartItem> cartItems = cartRepository.findAll();
        return cartItems.stream().map(this::cartItemToDTO).collect(Collectors.toList());
    }

    private CartItemDTO cartItemToDTO(CartItem cartItem) {
        CartItemDTO dto = new CartItemDTO();
        dto.setId(cartItem.getId());
        dto.setProduct_id(cartItem.getProduct_id());
        dto.setCount(cartItem.getCount());
        dto.setPrice(cartItem.getPrice());
        return dto;
    }

    public void addProduct(ProductRequestDTO productRequestDTO) {
        ProductDTO productDTO = productClient.getById(productRequestDTO.getId());
        CartItem cartItem = new CartItem();
        cartItem.setProduct_id(productDTO.getId());
        cartItem.setPrice(productDTO.getPrice());
        cartItem.setCount(productRequestDTO.getCount());
        cartRepository.save(cartItem);
    }
}
