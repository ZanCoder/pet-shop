package com.example.service;

import com.example.dto.CartDTO;

import java.util.List;

public interface CartService {
    List<CartDTO> getAllCart();
    CartDTO getCartById(Long id);
}
