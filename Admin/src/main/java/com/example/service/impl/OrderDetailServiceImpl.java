package com.example.service.impl;

import com.example.dto.OrderDetailDTO;
import com.example.entity.OrderDetail;
import com.example.repository.OrderDetailRepository;
import com.example.repository.OrderRepository;
import com.example.repository.ProductRepository;
import com.example.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    private OrderDetailRepository orderDetailRepository;
    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    @Autowired
    public void setOrderDetailRepository(OrderDetailRepository orderDetailRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public OrderDetail mapToEntity(OrderDetailDTO orderDetailDTO) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(orderDetailDTO.getId());
        orderDetail.setPrice(orderDetailDTO.getPrice());
        orderDetail.setQuantity(orderDetailDTO.getQuantity());
        orderDetail.setOrder(orderRepository.getOne(orderDetailDTO.getOrderId()));
        orderDetail.setProduct(productRepository.getOne(orderDetailDTO.getProductId()));
        return orderDetail;
    }

    public OrderDetailDTO mapToDTO(OrderDetail orderDetail) {
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        orderDetailDTO.setId(orderDetail.getId());
        orderDetailDTO.setPrice(orderDetail.getPrice());
        orderDetailDTO.setQuantity(orderDetail.getQuantity());
        orderDetailDTO.setOrderId(orderDetail.getOrder().getId());
        orderDetailDTO.setProductId(orderDetail.getProduct().getId());
        return orderDetailDTO;
    }


    @Override
    public List<OrderDetailDTO> getAllOrderDetails() {
        List<OrderDetail> orderDetails = orderDetailRepository.findAll();
        List<OrderDetailDTO> orderDetailDTOs = orderDetails.stream().map(orderDetail -> mapToDTO(orderDetail)).collect(Collectors.toList());
        return orderDetailDTOs;
    }

    @Override
    public OrderDetailDTO getOrderDetailById(Long id) {
        return mapToDTO(orderDetailRepository.findById(id).orElse(null));
    }
}
