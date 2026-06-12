package com.jpaProject.projectJpa.repository;

import com.jpaProject.projectJpa.entity.Product;
import com.jpaProject.projectJpa.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
