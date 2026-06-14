package com.jpaProject.projectJpa.service;

import com.jpaProject.projectJpa.dto.ProductMapper;
import com.jpaProject.projectJpa.dto.ProductResponseDto;
import com.jpaProject.projectJpa.dto.ProductSearchRequest;
import com.jpaProject.projectJpa.entity.Product;
import com.jpaProject.projectJpa.repository.ProductRepository;
import com.jpaProject.projectJpa.specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Page<ProductResponseDto> searchProducts(

            ProductSearchRequest request,

            int page,

            int size,

            String sortBy,

            String direction

    ) {

        Specification<Product> spec =
                Specification.where((Specification<Product>) null);

        //---------------- Name ----------------

        if(request.getName() != null &&
                !request.getName().isBlank()) {

            spec = spec.and(

                    ProductSpecification
                            .hasName(
                                    request.getName()
                            )
            );
        }

        //---------------- Category ----------------

        if(request.getCategory() != null &&
                !request.getCategory().isBlank()) {

            spec = spec.and(

                    ProductSpecification
                            .hasCategory(
                                    request.getCategory()
                            )
            );
        }

        //---------------- Min Price ----------------

        if(request.getMinPrice() != null) {

            spec = spec.and(

                    ProductSpecification
                            .minPrice(
                                    request.getMinPrice()
                            )
            );
        }

        //---------------- Max Price ----------------

        if(request.getMaxPrice() != null) {

            spec = spec.and(

                    ProductSpecification
                            .maxPrice(
                                    request.getMaxPrice()
                            )
            );
        }

        //---------------- In Stock ----------------

        if(Boolean.TRUE.equals(
                request.getInStock()
        )) {

            spec = spec.and(

                    ProductSpecification
                            .inStock()
            );
        }

        //---------------- Sorting ----------------

        Sort sort = direction.equalsIgnoreCase(
                "desc"
        )

                ? Sort.by(sortBy).descending()

                : Sort.by(sortBy).ascending();

        //---------------- Pagination ----------------

        Pageable pageable =

                PageRequest.of(

                        page,

                        size,

                        sort
                );

        Page<Product> products =

                productRepository.findAll(

                        spec,

                        pageable
                );

        //---------------- DTO Mapping ----------------

        return products.map(
                ProductMapper::toDto
        );
    }
}