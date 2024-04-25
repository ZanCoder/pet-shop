package com.example.service;

import com.example.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrder();
    OrderDTO getOrderById(Long id);
}
