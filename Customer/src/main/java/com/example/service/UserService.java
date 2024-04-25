package com.example.service;

import com.example.dto.UserDTO;
import com.example.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserDTO> getAllUser();
    UserDTO getUserById(Long id);
    User saveUser(User user);
    void deleteUser(Long id);
    User findByUsername(String username);
}
