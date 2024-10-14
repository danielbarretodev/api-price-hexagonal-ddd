package com.ecommerce.prices.interfaces.mappers;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.ecommerce.prices.domain.model.Price;
import com.ecommerce.prices.interfaces.request.PriceRequest;
import com.ecommerce.prices.interfaces.response.PriceResponse;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

    @Mapping(source = "startDate", target = "startDate", qualifiedByName = "timestampToString")
    @Mapping(source = "endDate", target = "endDate", qualifiedByName = "timestampToString")
    PriceResponse toPriceResponse(Price price);

    @Mapping(source = "startDate", target = "startDate", qualifiedByName = "stringToTimestamp")
    @Mapping(source = "endDate", target = "endDate", qualifiedByName = "stringToTimestamp")
    Price toPrice(PriceRequest request);

    // Conversión de String a Timestamp
    @Named("stringToTimestamp")
    default Timestamp stringToTimestamp(String dateString) {
        if (dateString == null) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return new Timestamp(sdf.parse(dateString).getTime());
        } catch (Exception e) {
            throw new RuntimeException("Invalid date format", e);
        }
    }

    // Conversión de Timestamp a String
    @Named("timestampToString")
    default String timestampToString(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(timestamp);
    }

}