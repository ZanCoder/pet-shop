package com.example.controller.admin;

import com.example.dto.CategoryDTO;
import com.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category")
    public String categoryList(Model model) {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        CategoryDTO categoryDTO = new CategoryDTO();
        model.addAttribute("categories", categories);
        model.addAttribute("category", categoryDTO);
        return "admin/page/category";
    }

    @PostMapping("/category/save")
    public String saveCategory(@ModelAttribute("category") CategoryDTO categoryDTO) {
        categoryService.saveCategory(categoryDTO);
        return "redirect:/admin/category";
    }

    @GetMapping("/category/delete")
    public String deleteCategory(@RequestParam("id") Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/admin/category";
    }
}
