package com.example.repository;

import com.example.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Truy vấn theo tên sản phẩm
    @Query("SELECT s FROM Product s WHERE s.name LIKE %:name%")
    Page<Product> findByName(@Param("name") String name, Pageable pageable);
}
