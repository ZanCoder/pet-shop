package com.example.service.impl;

import com.example.dto.CategoryDTO;
import com.example.entity.Category;
import com.example.repository.CategoryRepository;
import com.example.repository.ProductRepository;
import com.example.service.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS = categories.stream().map(category -> mapToDTO(category)).collect(Collectors.toList());
        return categoryDTOS;
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        return categoryRepository.findById(id).map(category -> mapToDTO(category)).orElse(null);
    }

    @Override
    @Transactional
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        return mapToDTO(categoryRepository.save(mapToEntity(categoryDTO)));
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public CategoryDTO mapToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }

    public Category mapToEntity(CategoryDTO categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        return category;
    }
}
