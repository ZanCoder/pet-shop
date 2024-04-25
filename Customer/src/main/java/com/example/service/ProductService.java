package com.example.service;

import com.example.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();

    Page<ProductDTO> getAllProducts(Pageable pageable);

    ProductDTO getProductById(Long id);

    ProductDTO saveProduct(ProductDTO product);

    void deleteProduct(Long id);

    Page<ProductDTO> findByName(String name, Pageable pageable);
}
