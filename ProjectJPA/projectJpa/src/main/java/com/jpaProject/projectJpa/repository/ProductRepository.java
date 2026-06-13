package com.jpaProject.projectJpa.repository;


import com.jpaProject.projectJpa.entity.Product;
import com.jpaProject.projectJpa.projection.dtoProjection.ProductCustomResponse;
import com.jpaProject.projectJpa.projection.dtoProjection.ProductResponse;
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

    //----------------------Descending Order------------------------

    //-----multiple sorting
//    @Query("""
//       SELECT p
//       FROM Product p
//       ORDER BY p.category.name ASC,
//                p.price DESC
//       """)

    @Query("""
       SELECT p
       FROM Product p
       ORDER BY p.price DESC
       """)
    List<Product> findAllOrderByPriceDesc();

    //---------------------Using Between---------------------------

    @Query("""
       SELECT p
       FROM Product p
       WHERE p.price BETWEEN :minPrice
                         AND :maxPrice
       """)
    List<Product> findProductsBetweenPrice(

            @Param("minPrice")
            BigDecimal minPrice,

            @Param("maxPrice")
            BigDecimal maxPrice
    );

    //---------------------Using IN ------------------------

    @Query("""
       SELECT p
       FROM Product p
       WHERE p.id IN :ids
       """)
    List<Product> findProductsByIds(

            @Param("ids")
            List<Long> ids
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


    //--------------------------------------------------
    // DTO Projection
    //--------------------------------------------------

    @Query("""
        SELECT new  com.jpaProject.projectJpa.projection.dtoProjection.ProductResponse(
                p.name,
                p.price
        )
        FROM Product p
       """)
    List<ProductResponse>
    findProductDto();


    //--------------------------------------------------
    // DTO Projection + Custom Response
    //--------------------------------------------------

    @Query("""
        SELECT new com.jpaProject.projectJpa.projection.dtoProjection.ProductCustomResponse(
                p.name,
                p.price
                , p.stockQuantity
        )
        FROM Product p
       """)
    List<ProductCustomResponse>
    findProductCustomResponseDto();


}
