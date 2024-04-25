package com.example.service;

import com.example.dto.CartDetailDTO;

import java.util.List;

public interface CartDetailService {
    List<CartDetailDTO> getAllCartDetails();
    CartDetailDTO getCartDetailById(Long id);
}
