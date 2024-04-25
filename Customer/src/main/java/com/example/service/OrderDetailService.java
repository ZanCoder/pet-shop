package com.example.service;

import com.example.dto.OrderDetailDTO;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetailDTO> getAllOrderDetails();
    OrderDetailDTO getOrderDetailById(Long id);
}
