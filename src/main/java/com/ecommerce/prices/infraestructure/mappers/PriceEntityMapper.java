package com.ecommerce.prices.infraestructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.ecommerce.prices.domain.model.Price;
import com.ecommerce.prices.infraestructure.entities.PriceEntity;

@Mapper(componentModel = "spring")
public interface PriceEntityMapper {

    PriceEntityMapper INSTANCE = Mappers.getMapper(PriceEntityMapper.class);


    PriceEntity toEntity(Price price);

    Price toDomain(PriceEntity priceEntity);
}