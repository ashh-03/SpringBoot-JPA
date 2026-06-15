package com.jpaProject.projectJpa.repository;


import com.jpaProject.projectJpa.dto.ProductStatusDto;
import com.jpaProject.projectJpa.entity.Category;
import com.jpaProject.projectJpa.entity.Product;
import com.jpaProject.projectJpa.projection.dtoProjection.ProductCustomResponse;
import com.jpaProject.projectJpa.projection.dtoProjection.ProductResponse;
import com.jpaProject.projectJpa.projection.interfaceProjection.ProductSummary;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository
        extends JpaRepository<Product, Long> , JpaSpecificationExecutor<Product> {

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

    //-------------using isNull and isNotNull------------------

    @Query("""
       SELECT p
       FROM Product p
       WHERE p.description IS NULL
       """)
    List<Product> findProductsWithoutDescription();

    //---------------------------
    @Query("""
       SELECT p
       FROM Product p
       WHERE p.description IS NOT NULL
       """)
    List<Product> findProductsWithDescription();

    //---------------using having------------------------

    //--------used join here because here we picking each element from second table
    @Query("""
       SELECT c.name,
              AVG(p.price)
       FROM Category c
       JOIN c.products p
       GROUP BY c.name
       HAVING AVG(p.price) > 10000
       """)
    List<Product> findCategoryAvgPrice();

    //-----------------creating if-else using jpql-----------

    //---------without DTO
    @Query("""
       SELECT
       CASE

            WHEN p.price > 80000
            THEN 'Luxury'

            WHEN p.price > 30000
            THEN 'Premium'

            ELSE 'Budget'

       END

       FROM Product p
       """)
    List<Product> ReturnProductType();

    //--------with DTO

    @Query("""
       SELECT new com.jpaProject.projectJpa.dto.ProductStatusDto(

           p.name,

          CASE
      
                       WHEN p.stockQuantity = 0
                       THEN 'OUT OF STOCK'
       
                       WHEN p.stockQuantity < 10
                       THEN 'LOW STOCK'
      
                       ELSE 'AVAILABLE'
      
                   END

       )

       FROM Product p
       """)
    List<ProductStatusDto> ReturnProductStatus();

    //------------------using update---------------------

    //---------single update
    @Modifying
    @Transactional
    @Query("""
       UPDATE Product p
       SET p.stockQuantity = :stock
       WHERE p.id = :id
       """)
    int updateStockById(
            Long id,
            Integer stock
    );
    //---------bulk update
    @Modifying
    @Transactional
    @Query("""
       UPDATE Product p
       SET p.price = p.price * 1.10
       WHERE p.category.name = :category
       """)
    int increasePriceByCategory(
            String category
    );




    //--------------------using delete---------------------

    //--------single delete----
    @Modifying
    @Transactional
    @Query("""
       DELETE
       FROM Product p
       WHERE p.id = :id
       """)
    int deleteProduct(
            @Param("id") Long id
    );
//    //--------bulk delete----[bulk update method of performance tuning]
    @Modifying
    @Transactional
    @Query("""
       DELETE
       FROM Product p
       WHERE p.stockQuantity = 0
       """)
    int deleteOutOfStockProducts();

    //--------------------------------------------------
    // USING SUBQUERY
    //--------------------------------------------------

    @Query("""
       SELECT p
       FROM Product p
       WHERE p.price >
       (
            SELECT AVG(p2.price)
            FROM Product p2
       )
       """)
    List<Product> findProductsAboveAveragePrice();

    @Query("""
       SELECT p
       FROM Product p
       WHERE p.price =
       (
            SELECT MAX(p2.price)
            FROM Product p2
       )
       """)
    List<Product> findMostExpensiveProducts();

    //--------multiple query return
    @Query("""
            SELECT p
             FROM Product p
             WHERE p.category.id IN
             (
                  SELECT c.id
                  FROM Category c
             )
       """)
    List<Product> findProductsInGivenId();

    //--------------Using Exists----------------------
    @Query("""
       SELECT p
       FROM Product p
       WHERE EXISTS
       (
            SELECT r
            FROM Review r
            WHERE r.product = p
       )
       """)
    List<Product> findProductsWithReviews();

    @Query("""
       SELECT c
       FROM Category c
       WHERE EXISTS
       (
            SELECT p
            FROM Product p
            WHERE p.category = c
       )
       """)
    List<Category> findCategoriesHavingProducts();

    //--------------------------------------------------
    // JPQL N+1 Problem
    //--------------------------------------------------

    //-----------using join fetch------------

    //----single join fetch
    @Query("""
       SELECT p
       FROM Product p
       JOIN FETCH p.category
       """)
    List<Product> findAllProductsWithCategory();

    //---multiple join fetch
    @Query("""
       SELECT p
       FROM Product p
       JOIN FETCH p.category
       JOIN FETCH p.reviews
       """)
    List<Product> findAllProductWithCategoryAndReviews();

    //-------------using Entity Graph------------

    //-----single attribute
    @EntityGraph(attributePaths = {"category"})
    List<Product> findAllBySingleAttribute();

    //------multiple attributes
    @EntityGraph(attributePaths = {
            "category",
            "reviews"
    })
    List<Product> findAll();

    //----------using named entity query
    @EntityGraph(value = "Product.withCategory")
    List<Product> findAllByNamedEntityQuery();






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


    //--------------------------------------------------
    // Pagination
    //-------------------------------------------------




    //--------------------------------------------------
    // Locking Pessimistic Lock
    //-------------------------------------------------

    @Lock(
            LockModeType.PESSIMISTIC_WRITE
    )
    @Query("""
            SELECT p
            FROM Product p
            WHERE p.id = :id
           """)
    Optional<Product> findByIdForUpdate(
            Long id
    );


    //--------------------------------------------------
    // Bulk Updates
    //-------------------------------------------------

    @Modifying
    @Transactional
    @Query("""
       UPDATE Product p
       SET p.price =
           p.price * 1.10
       """)
    int increasePrice();

    @Modifying(
            clearAutomatically = true
    )

    @Query("""
       UPDATE Product p
       SET p.price =
           p.price * 0.90
       WHERE p.category.name =
             :category
       """)
    int applyDiscount(
            String category
    );


}
