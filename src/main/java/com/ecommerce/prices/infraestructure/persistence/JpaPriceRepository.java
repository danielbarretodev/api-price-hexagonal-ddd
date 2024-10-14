package com.ecommerce.prices.infraestructure.persistence;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.prices.infraestructure.entities.PriceEntity;

@Repository
public interface JpaPriceRepository extends JpaRepository<PriceEntity, Long> {
    
    
      @Query(value = "SELECT * FROM prices p WHERE :applicationDate >= p.start_date " +
       "AND :applicationDate <= p.end_date " +
       "AND p.product_id = :productId " +
       "AND p.brand_id = :brandId " +
       "ORDER BY p.priority DESC LIMIT 1", nativeQuery = true)
    Optional<PriceEntity> searchPrices(@Param("applicationDate") Timestamp applicationDate,
                            @Param("productId") Long productId,
                            @Param("brandId") Long brandId);
}
