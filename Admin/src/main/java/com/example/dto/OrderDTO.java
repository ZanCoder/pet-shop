package com.example.dto;

import com.example.entity.OrderDetail;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDTO {
    private Long id;

    private String address;

    private Date createDate;

    private List<OrderDetail> orderDetails = new ArrayList<>();

    private Long userId;
}
