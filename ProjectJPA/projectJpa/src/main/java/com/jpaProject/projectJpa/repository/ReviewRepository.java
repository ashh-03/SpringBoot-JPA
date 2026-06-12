package com.jpaProject.projectJpa.repository;

import com.jpaProject.projectJpa.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review , Long> {
}
