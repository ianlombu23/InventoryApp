package com.woyo.ms_inventory.repository;

import com.woyo.ms_inventory.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value =
            "SELECT * FROM product p " +
                    "WHERE (:stock IS NULL OR p.stock = :stock) " +
                    "AND (:category IS NULL OR JSON_EXTRACT(p.additional_info, '$.category') = :category) ",
            nativeQuery = true
    )
    Page<Product> findAllProduct(Integer stock, String category, Pageable pageable);
}
