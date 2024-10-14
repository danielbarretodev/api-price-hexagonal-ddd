package com.ecommerce.prices.interfaces.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PriceResponse(
    Long id,
    Long productId,
    Long brandId,
    Long priceList,
    String startDate,
    String endDate,
    BigDecimal price

 ) {}

