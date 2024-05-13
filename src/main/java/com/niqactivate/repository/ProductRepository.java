package com.niqactivate.repository;

import com.niqactivate.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("SELECT p FROM Product p" +
            " WHERE (:shopperId is null or p.shopperId = :shopperId) " +
            "AND (:category is null or p.category = :category) " +
            "AND (:brand is null or p.brand = :brand)")
    List<Product> findByFilters(@Param("shopperId") String shopperId,
            @Param("category") String category,
            @Param("brand") String brand,
            Pageable pageable);
}
