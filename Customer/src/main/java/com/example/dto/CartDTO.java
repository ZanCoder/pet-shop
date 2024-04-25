package com.example.dto;

import com.example.entity.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartDTO {
    private Long id;

    private String name;

    private double price;

    private String description;

    private String image;

    private int quantity;

    private User user;
}
