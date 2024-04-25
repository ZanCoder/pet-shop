package com.example.controller.admin;

import com.example.dto.CartDTO;
import com.example.dto.CartDetailDTO;
import com.example.dto.ProductDTO;
import com.example.dto.UserDTO;
import com.example.entity.Cart;
import com.example.entity.CartDetail;
import com.example.service.CartDetailService;
import com.example.service.CartService;
import com.example.service.ProductService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class CartController {
    private CartService cartService;
    private CartDetailService cartDetailService;
    private UserService userService;
    private ProductService productService;

    @Autowired
    public CartController(CartService cartService, CartDetailService cartDetailService, UserService userService, ProductService productService) {
        this.cartService = cartService;
        this.cartDetailService = cartDetailService;
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/cart")
    public String getAllCarts(Model model) {
        List<CartDTO> cartDTOS = cartService.getAllCart();
        List<CartDetailDTO> cartDetailDTOS = cartDetailService.getAllCartDetails();
        List<UserDTO> userDTOS = userService.getAllUser();
        List<ProductDTO> productDTOS = productService.getAllProducts();
        model.addAttribute("carts", cartDTOS);
        model.addAttribute("cartDetails", cartDetailDTOS);
        model.addAttribute("users", userDTOS);
        model.addAttribute("products", productDTOS);
        return "admin/page/cart";
    }
}
