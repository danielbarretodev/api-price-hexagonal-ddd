package com.ecommerce.prices.interfaces.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PriceRequest (
    Long brandId,
    String startDate,
    String endDate,
    Long priceList,
    Long productId,
    Integer priority,
    BigDecimal price,
    String curr
){}
