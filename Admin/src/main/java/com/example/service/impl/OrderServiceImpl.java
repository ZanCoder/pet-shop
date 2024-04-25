package com.example.service.impl;

import com.example.dto.OrderDTO;
import com.example.entity.Order;
import com.example.repository.OrderRepository;
import com.example.repository.UserRepository;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private UserRepository userRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public Order mapToEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setAddress(orderDTO.getAddress());
        order.setCreateDate(orderDTO.getCreateDate());
        order.setOrderDetails(orderDTO.getOrderDetails());
        order.setUser(userRepository.getOne(orderDTO.getUserId()));
        return order;
    }

    public OrderDTO mapToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setAddress(order.getAddress());
        orderDTO.setCreateDate(order.getCreateDate());
        orderDTO.setOrderDetails(order.getOrderDetails());
        orderDTO.setUserId(order.getUser().getId());
        return orderDTO;
    }

    @Override
    public List<OrderDTO> getAllOrder() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDTO> orderDTOs = orders.stream().map(order -> mapToDTO(order)).collect(Collectors.toList());
        return orderDTOs;
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        return mapToDTO(orderRepository.findById(id).orElse(null));
    }
}
