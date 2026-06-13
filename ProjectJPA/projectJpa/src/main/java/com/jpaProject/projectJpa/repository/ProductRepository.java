package com.jpaProject.projectJpa.repository;


import com.jpaProject.projectJpa.entity.Product;
import com.jpaProject.projectJpa.projection.interfaceProjection.ProductSummary;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository
        extends JpaRepository<Product, Long> {

    //--------------------------------------------------
    // JPQL
    //--------------------------------------------------

    @Query("""
            SELECT p
            FROM Product p
            WHERE p.price > :price
           """)
    List<Product>
    findProductsGreaterThanPrice(

            @Param("price")
            BigDecimal price
    );



    //--------------------------------------------------
    // JPQL RELATIONSHIP QUERY
    //--------------------------------------------------

    @Query("""
            SELECT p
            FROM Product p
            WHERE p.category.name = :categoryName
           """)
    List<Product>
    findByCategoryName(

            @Param("categoryName")
            String categoryName
    );



    //--------------------------------------------------
    // JPQL + LIKE
    //--------------------------------------------------

    @Query("""
            SELECT p
            FROM Product p
            WHERE p.category.name = :categoryName
            AND p.name LIKE CONCAT('%', :keyword, '%')
           """)
    List<Product>
    findProductsByCategoryAndKeyword(

            @Param("categoryName")
            String categoryName,

            @Param("keyword")
            String keyword
    );



    //--------------------------------------------------
    // NAMED QUERY
    //--------------------------------------------------

    @Query(
            name = "Product.findExpensiveProducts"
    )
    List<Product>
    findExpensiveProducts(

            @Param("price")
            BigDecimal price
    );



    //--------------------------------------------------
    // NATIVE QUERY
    //--------------------------------------------------

    @Query(
            value = """
                    SELECT *
                    FROM products
                    WHERE stock_quantity < :stock
                    """,
            nativeQuery = true
    )
    List<Product>
    findLowStockProducts(

            @Param("stock")
            Integer stock
    );


    //--------------------------------------------------
    // Interface Projection
    //--------------------------------------------------

    @Query("""
        SELECT p
        FROM Product p
       """)
    List<ProductSummary>
    findProductSummary();

}