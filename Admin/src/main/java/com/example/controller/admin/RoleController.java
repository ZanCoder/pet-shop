package com.example.controller.admin;

import com.example.dto.RoleDTO;
import com.example.dto.UserDTO;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.service.RoleService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class RoleController {
    private RoleService roleService;
    private UserService userService;

    @Autowired
    public RoleController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/role")
    public String getAllRoles(Model model) {
        List<RoleDTO> roleDTOS = roleService.getAllRoles();
        List<UserDTO> userDTOS = userService.getAllUser();
        Role role = new Role();
        model.addAttribute("roles", roleDTOS);
        model.addAttribute("users", userDTOS);
        model.addAttribute("role", role);
        return "admin/page/role";
    }

    @PostMapping("/role/save")
    public String saveRole(@ModelAttribute("role") Role role) {
        roleService.saveRole(role);
        return "redirect:/admin/role";
    }
}
