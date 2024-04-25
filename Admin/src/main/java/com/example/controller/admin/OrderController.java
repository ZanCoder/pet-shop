package com.example.controller.admin;

import com.example.dto.OrderDTO;
import com.example.dto.OrderDetailDTO;
import com.example.service.OrderDetailService;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class OrderController {
    private OrderService orderService;
    private OrderDetailService orderDetailService;

    @Autowired
    public OrderController(OrderService orderService, OrderDetailService orderDetailService) {
        this.orderService = orderService;
        this.orderDetailService = orderDetailService;
    }

    @GetMapping("/order")
    public String getAllOrders(Model model) {
        List<OrderDTO> orderDTOS = orderService.getAllOrder();
        List<OrderDetailDTO> orderDetailDTOS = orderDetailService.getAllOrderDetails();
        model.addAttribute("orders", orderDTOS);
        model.addAttribute("orderDetails", orderDetailDTOS);
        return "admin/page/order";
    }
}
