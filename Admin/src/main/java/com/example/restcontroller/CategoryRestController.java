package com.example.restcontroller;

import com.example.dto.CategoryDTO;
import com.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class CategoryRestController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/get-category")
    public CategoryDTO getCategoryById(@RequestParam("id") Long id) {
        CategoryDTO categoryDTO = categoryService.getCategoryById(id);
        return categoryDTO;
    }
}
