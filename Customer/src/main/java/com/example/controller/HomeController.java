package com.example.controller;

import com.example.dto.CategoryDTO;
import com.example.dto.ProductDTO;
import com.example.service.CategoryService;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
    private ProductService productService;
    private CategoryService categoryService;

    @Autowired
    public HomeController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/index")
    public String index(Model model) {
        List<ProductDTO> productDTOS = productService.getAllProducts();
        List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();
        model.addAttribute("products", productDTOS);
        model.addAttribute("categories", categoryDTOS);
        return "index";
    }
}
