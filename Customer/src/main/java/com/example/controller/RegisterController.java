package com.example.controller;

import com.example.dto.UserRegisterDTO;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.repository.RoleRepository;
import com.example.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private UserService userService;
    private RoleRepository roleRepository;

    public RegisterController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/form")
    public String showRegisterForm(Model model) {
        UserRegisterDTO registerUser = new UserRegisterDTO();
        model.addAttribute("registerUser", registerUser);
        return "register_form";
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @PostMapping("/process")
    public String processRegister(@Valid @ModelAttribute UserRegisterDTO registerUser, BindingResult result, HttpSession session, Model model) {
        String username = registerUser.getUsername();
        if (result.hasErrors()) {
            model.addAttribute("registerUser", registerUser);
            return "register_form";
        }

        // Kiểm tra user đã tồn tại?
        User userExisting = userService.findByUsername(username);
        if (userExisting != null) {
            model.addAttribute("registerUser", new UserRegisterDTO());
            model.addAttribute("myError", "Tài khoản đã tồn tại");
            return "register_form";
        }

        // Nếu chưa tồn tại thì save user new
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = new User();

        user.setUsername(registerUser.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(registerUser.getPassword()));
        user.setFullName(registerUser.getFullName());
        user.setEmail(registerUser.getEmail());
        user.setAddress(registerUser.getAddress());
        user.setPhoneNumber(registerUser.getPhoneNumber());
        user.setAvatar(registerUser.getAvatar());

        Role defaultRole = roleRepository.findByName("ROLE_CUSTOMER");
        Set<Role> roles = new HashSet<>();
        roles.add(defaultRole);
        user.setRoles(roles);

        userService.saveUser(user);

        // Thông báo thành công
        session.setAttribute("myUser", user);

        return "confirm";
    }
}
