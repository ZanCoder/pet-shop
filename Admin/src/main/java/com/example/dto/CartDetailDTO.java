package com.example.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartDetailDTO {
    private Long id;

    private String productCode;

    private String voucher;

    private double transportationCosts;

    private double tax;

    private double totalProduct;

    private Long cartId;

    private Long productId;
}
