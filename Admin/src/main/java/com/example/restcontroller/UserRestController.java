package com.example.restcontroller;

import com.example.dto.UserDTO;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class UserRestController {
    @Autowired
    private UserService userService;

    @GetMapping("/get-user")
    public UserDTO getUser(@RequestParam("id") Long id) {
        UserDTO userDTO = userService.getUserById(id);
        return userDTO;
    }
}
