package com.example.service.impl;

import com.example.dto.RoleDTO;
import com.example.entity.Role;
import com.example.repository.RoleRepository;
import com.example.service.RoleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        List<RoleDTO> roleDtos = roles.stream().map(role -> mapToDTO(role)).collect(Collectors.toList());
        return roleDtos;
    }

    @Override
    public RoleDTO getRoleById(Long id) {
        return roleRepository.findById(id).map(role -> mapToDTO(role)).orElse(null);
    }

    @Override
    @Transactional
    public Role findByRoleName(String roleName) {
        return roleRepository.findByName(roleName);
    }

    @Override
    @Transactional
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    @Transactional
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    public Role mapToEntity(RoleDTO roleDto) {
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setName(roleDto.getName());
        return role;
    }

    public RoleDTO mapToDTO(Role role) {
        RoleDTO roleDto = new RoleDTO();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        return roleDto;
    }
}
