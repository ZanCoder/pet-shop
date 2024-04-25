package com.example.dto;

import com.example.entity.Category;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDTO {
    private Long id;

    private String name;

    private String description;

    private double price;

    private int quantity;

    private String image;

    private boolean available;

    private Category category;

    private Long categoryId;
}
