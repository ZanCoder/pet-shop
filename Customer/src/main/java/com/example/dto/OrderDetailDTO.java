package com.example.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetailDTO {
    private Long id;

    private double price;

    private int quantity;

    private Long orderId;

    private Long productId;
}
