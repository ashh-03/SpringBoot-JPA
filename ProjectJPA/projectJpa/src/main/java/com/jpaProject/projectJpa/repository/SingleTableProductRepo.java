package com.jpaProject.projectJpa.repository;


import com.jpaProject.projectJpa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



public interface SingleTableProductRepo extends JpaRepository<Product, Long> {



}
