package com.jpaProject.projectJpa.repository;

import com.jpaProject.projectJpa.entity.Product;
import com.jpaProject.projectJpa.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    //----------named query usage-----------


    @Query(
            name = "Product.findExpensiveProducts"
    )

    List<Product> findExpensiveProducts(@Param("price") BigDecimal price);





//-----------------------------------------------------------------
    //------------------------------------
    //------------JPQL Queries------------
    //------------------------------------

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


//-----------------------------------------------------------------


    @Query("""
        SELECT p
        FROM Product p
        WHERE p.category.name = :categoryName
       """)
    List<Product> findByCategoryName(
            @Param("categoryName")
            String categoryName
    );


//-----------------------------------------------------------------


    @Query("""
        SELECT p
        FROM Product p
        WHERE p.category.name = :categoryName
        AND p.price > :price
       """)
    List<Product> findProductsByCategoryAndPrice(

            @Param("categoryName")
            String categoryName,

            @Param("price")
            BigDecimal price
    );


//-----------------------------------------------------------------


//
//    @Query("""
//        SELECT p
//        FROM Product p
//        WHERE p.name LIKE CONCAT('%', :keyword, '%')
//       """)
//    List<Product> searchProducts(
//            @Param("keyword")
//            String keyword
//    );


    @Query("""
        SELECT p
        FROM Product p
        WHERE p.name LIKE CONCAT('%', :keyword, '%')
       """)
    List<Product> searchProducts(
            String keyword
    );



//-----------------------------------------------------------------


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

//-----------------------------------------------------------------

    //------------------------------------
    //----------NATIVE Query(SQL)---------
    //------------------------------------





}
