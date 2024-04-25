package com.example.service.impl;

import com.example.dto.CartDTO;
import com.example.entity.Cart;
import com.example.repository.CartRepository;
import com.example.repository.UserRepository;
import com.example.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    private CartRepository cartRepository;
    private UserRepository userRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<CartDTO> getAllCart() {
        List<Cart> carts = cartRepository.findAll();
        List<CartDTO> cartDTOS = carts.stream().map(cart -> mapToDTO(cart)).collect(Collectors.toList());
        return cartDTOS;
    }

    @Override
    public CartDTO getCartById(Long id) {
        return mapToDTO(cartRepository.findById(id).orElse(null));
    }

    public Cart mapToEntity(CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setId(cartDTO.getId());
        cart.setName(cartDTO.getName());
        cart.setPrice(cartDTO.getPrice());
        cart.setDescription(cartDTO.getDescription());
        cart.setQuantity(cartDTO.getQuantity());
        cart.setUser(cartDTO.getUser());
        return cart;
    }

    public CartDTO mapToDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setName(cart.getName());
        cartDTO.setPrice(cart.getPrice());
        cartDTO.setDescription(cart.getDescription());
        cartDTO.setQuantity(cart.getQuantity());
        cartDTO.setUser(cart.getUser());
        return cartDTO;
    }
}
