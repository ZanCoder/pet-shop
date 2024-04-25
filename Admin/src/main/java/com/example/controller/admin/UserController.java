package com.example.controller.admin;


import com.example.dto.RoleDTO;
import com.example.dto.UserDTO;
import com.example.entity.User;
import com.example.service.OrderService;
import com.example.service.RoleService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserController {
    private UserService userService;
    private RoleService roleService;
    private OrderService orderService;

    @Autowired
    public UserController(UserService userService, RoleService roleService, OrderService orderService) {
        this.userService = userService;
        this.roleService = roleService;
        this.orderService = orderService;
    }

    @GetMapping("/user")
    public String userList(Model model) {
        List<UserDTO> users = userService.getAllUser();
        List<RoleDTO> roles = roleService.getAllRoles();
        User user = new User();
        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        model.addAttribute("user", user);
        return "admin/page/user";
    }

    @PostMapping("/user/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin/user";
    }

    @GetMapping("/user/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/user";
    }
}
