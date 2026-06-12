package com.jpaProject.projectJpa.repository;

import com.jpaProject.projectJpa.entity.Category;
import com.jpaProject.projectJpa.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
