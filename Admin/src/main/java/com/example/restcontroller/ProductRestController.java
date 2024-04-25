package com.example.restcontroller;

import com.example.dto.ProductDTO;
import com.example.entity.Product;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class ProductRestController {
    @Autowired
    private ProductService productService;

    @GetMapping("/get-product")
    public ProductDTO getProduct(@RequestParam("id") Long id) {
        ProductDTO productDto = productService.getProductById(id);
        return productDto;
    }

}
