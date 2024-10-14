package com.ecommerce.prices.interfaces.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PriceRequest2 {

    private Long brandId;
    private String startDate;
    private String endDate;
    private Long priceList;
    private Long productId;
    private Integer priority;
    private BigDecimal price;
    private String curr;
    
}
