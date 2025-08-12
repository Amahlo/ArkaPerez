package com.arka.arkaperez.infrastructure.adapter.out.persistence.repository;

import com.arka.arkaperez.infrastructure.adapter.out.persistence.entity.ProductJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductJpaEntity, Long> {

    List<ProductJpaEntity> findByCategory_Name(String categoryName);

    List<ProductJpaEntity> findByNameContainingIgnoreCase(String name);

    @Query("SELECT p FROM ProductJpaEntity p WHERE p.unitPrice BETWEEN :min AND :max")
    List<ProductJpaEntity> findByPriceRange(@Param("min") BigDecimal min,
                                            @Param("max") BigDecimal max);
}