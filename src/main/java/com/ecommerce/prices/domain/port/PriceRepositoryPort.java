package com.ecommerce.prices.domain.port;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import com.ecommerce.prices.domain.model.Price;

public interface PriceRepositoryPort {
    Price save(Price price);
    List<Price> findAll();
    Optional<Price> findById(Long id);
    void deleteById(Long id);
    Optional<Price> searchPrices(Timestamp applicationDate, Long productId, Long brandId);
}