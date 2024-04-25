package com.example.service;

import com.example.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryById(Long id);
    CategoryDTO saveCategory(CategoryDTO categoryDTO);
    void deleteCategory(Long id);
}
