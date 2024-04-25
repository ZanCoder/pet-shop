package com.example.dto;

import com.example.entity.Order;
import com.example.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {
    private Long id;

    @NotBlank(message = "Name cannot be blank!")
    private String fullName;

    @NotBlank(message = "Email cannot be blank!")
    @Email(message = "Invalid Email")
    private String email;

    @NotBlank(message = "Address cannot be blank!")
    private String address;

    @NotBlank(message = "Phone Number cannot be blank!")
    private String phoneNumber;

    @NotBlank(message = "Avatar cannot be blank!")
    private String avatar;

    private Set<Role> roles = new HashSet<>();

    private List<Order> orders = new ArrayList<>();

}
