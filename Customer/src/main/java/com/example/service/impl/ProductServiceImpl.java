package com.example.service.impl;

import com.example.dto.ProductDTO;
import com.example.entity.Product;
import com.example.repository.CategoryRepository;
import com.example.repository.ProductRepository;
import com.example.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    public static final int PRODUCT_PER_PAGE = 15;

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = products.stream().map(product -> mapToDTO(product)).collect(Collectors.toList());
        return productDTOS;
    }

    @Override
    public Page<ProductDTO> getAllProducts(Pageable pageable) {
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage.map(product -> mapToDTO(product));
    }

    @Override
    @Transactional
    public Page<ProductDTO> findByName(String name, Pageable pageable) {
        Page<Product> productPage = productRepository.findByName(name, pageable);
        Page<ProductDTO> productDtos = productPage.map(product -> mapToDTO(product));
        return productDtos;
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            ProductDTO productDTO = mapToDTO(product);
            return productDTO;
        }
        return null;
    }

    @Override
    @Transactional
    public ProductDTO saveProduct(ProductDTO productDTO) {
        return mapToDTO(productRepository.save(mapToEntity(productDTO)));
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product mapToEntity(ProductDTO productDTO){
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setQuantity(productDTO.getQuantity());
        product.setImage(productDTO.getImage());
        product.setAvailable(productDTO.isAvailable());
        product.setCategory(productDTO.getCategory());
        product.setCategory(categoryRepository.findById(productDTO.getCategoryId()).get());
        return product;
    }

    public ProductDTO mapToDTO(Product product){
        ProductDTO productDto = new ProductDTO();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setQuantity(product.getQuantity());
        productDto.setImage(product.getImage());
        productDto.setAvailable(product.isAvailable());
        productDto.setCategory(product.getCategory());
        productDto.setCategoryId(product.getCategory().getId());
        return productDto;
    }
}
