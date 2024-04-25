package com.example.controller.admin;

import com.example.dto.CategoryDTO;
import com.example.dto.ProductDTO;
import com.example.service.CategoryService;
import com.example.service.ProductService;
import com.example.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProductController {
    private ProductService productService;
    private CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }
    @GetMapping("/product")
    public String productList(Model model) {
        return productListByPage(model, 1, "");
    }

    @GetMapping("/product/page/{pageNum}")
    public String productListByPage(Model model, @PathVariable int pageNum,
                                    @RequestParam(value = "name", defaultValue = "") String name) {
        Pageable pageable = PageRequest.of(pageNum - 1, ProductServiceImpl.PRODUCT_PER_PAGE);

        Page<ProductDTO> productDtos = productService.findByName(name, pageable);
        List<ProductDTO> productDtoList = productDtos.getContent();
        List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();
        ProductDTO productDTO = new ProductDTO();

        model.addAttribute("products", productDtoList);
        model.addAttribute("categories", categoryDTOS);
        model.addAttribute("product", productDTO);

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", productDtos.getTotalPages());
        model.addAttribute("keyword", name);
        return "admin/page/product";
    }

    @PostMapping("/product/save")
    public String saveProduct(@ModelAttribute("product") ProductDTO product, RedirectAttributes redirectAttributes) {
        productService.saveProduct(product);
        redirectAttributes.addFlashAttribute("message", "Product saved successfully");
        return "redirect:/admin/product";
    }

    @GetMapping("/product/delete")
    public String deleteProduct(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        productService.deleteProduct(id);
        redirectAttributes.addFlashAttribute("message", "Product deleted successfully");
        return "redirect:/admin/product";
    }
}
