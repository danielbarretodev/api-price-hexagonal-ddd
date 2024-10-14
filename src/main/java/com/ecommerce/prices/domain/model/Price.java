package com.ecommerce.prices.domain.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder 
@AllArgsConstructor
@NoArgsConstructor
public class Price {
    private Long id;
    private Long brandId;
    private Timestamp startDate;
    private Timestamp endDate;
    private Long priceList;
    private Long productId;
    private Integer priority;
    private BigDecimal price;
    private String currency;
}

