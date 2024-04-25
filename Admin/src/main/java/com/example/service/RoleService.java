package com.example.service;

import com.example.dto.RoleDTO;
import com.example.entity.Role;

import java.util.List;

public interface RoleService {
    List<RoleDTO> getAllRoles();
    RoleDTO getRoleById(Long id);
    Role findByRoleName(String roleName);
    Role saveRole(Role role);
    void deleteRole(Long id);
}
