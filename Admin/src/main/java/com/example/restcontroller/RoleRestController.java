package com.example.restcontroller;

import com.example.dto.RoleDTO;
import com.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class RoleRestController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/get-role")
    public RoleDTO getRoleById(@RequestParam("id") Long id) {
        RoleDTO roleDTO = roleService.getRoleById(id);
        return roleDTO;
    }
}
