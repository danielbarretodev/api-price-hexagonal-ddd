package com.ecommerce.prices.application;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecommerce.prices.domain.exception.InvalidPriceRequestException;
import com.ecommerce.prices.domain.exception.PriceNotFoundException;
import com.ecommerce.prices.domain.model.Price;
import com.ecommerce.prices.domain.port.PriceRepositoryPort;
import com.ecommerce.prices.interfaces.mappers.PriceMapper;
import com.ecommerce.prices.interfaces.request.PriceRequest;
import com.ecommerce.prices.interfaces.response.PriceResponse;

@Service
public class PriceUseCase {

    private final PriceRepositoryPort priceRepositoryPort;
    
    private final PriceMapper priceMapper;


    public PriceUseCase(PriceRepositoryPort priceRepositoryPort
    , PriceMapper priceMapper) {
        this.priceRepositoryPort = priceRepositoryPort;
        this.priceMapper = priceMapper;
    }

    public PriceResponse savePrice(PriceRequest request) {
        validatePriceRequest(request);
        Price price = priceMapper.toPrice(request);
        Price savedPrice = priceRepositoryPort.save(price);
        return priceMapper.toPriceResponse(savedPrice);
    }
    
    public PriceResponse updatePrice(Long id, PriceRequest request) {
   
        validatePriceRequest(request);
        Optional<Price> existingPriceOptional = priceRepositoryPort.findById(id);

        if (existingPriceOptional.isEmpty()) {
            throw new PriceNotFoundException("Price with ID " + id + " not found");
        }
        
        Price priceToUpdate = priceMapper.toPrice(request);
        priceToUpdate.setId(id);
        Price updatedPrice = priceRepositoryPort.save(priceToUpdate);
    
        return priceMapper.toPriceResponse(updatedPrice);
    }

    public List<PriceResponse> getAllPrices() {
        return priceRepositoryPort.findAll()
                .stream()
                .map(priceMapper::toPriceResponse)
                .collect(Collectors.toList());
    }

    public PriceResponse getPriceById(Long id) {
        return priceRepositoryPort.findById(id)
                .map(priceMapper::toPriceResponse)
                .orElseThrow(() -> new PriceNotFoundException("Price with ID " + id + " not found"));
    }

    public boolean deletePrice(Long id) {
        Optional<Price> priceOptional = priceRepositoryPort.findById(id);
        if (priceOptional.isPresent()) {
            priceRepositoryPort.deleteById(id);
            return true;
        }
        throw new PriceNotFoundException("Price with ID " + id + " not found"); // Lanzar excepción si no se encuentra
    }

    public PriceResponse searchPrices(String applicationDate, Long productId, Long brandId) {
        return priceRepositoryPort.searchPrices(stringToTimestamp(applicationDate), productId, brandId)
                .map(priceMapper::toPriceResponse)
                .orElseThrow(() -> new PriceNotFoundException("No prices found for application date: " + applicationDate
                        + ", product ID: " + productId + ", brand ID: " + brandId));
    }
    
    private Timestamp stringToTimestamp(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sdf.parse(dateString);
            return new Timestamp(date.getTime());
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format", e);
        }
    }
    
        private void validatePriceRequest(PriceRequest request) {
        if (request.brandId() == null || request.productId() == null || request.price() == null) {
            throw new InvalidPriceRequestException("Brand ID, Product ID, and Price must not be null");
        }
        if (request.price().compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidPriceRequestException("Price must be greater than or equal to zero");
        }
        
        try {
            Timestamp startDate = stringToTimestamp(request.startDate());
            Timestamp endDate = stringToTimestamp(request.endDate());

            if (startDate.after(endDate)) {
                throw new InvalidPriceRequestException("Start date must be before end date");
            }
        } catch (Exception e) {
            throw new InvalidPriceRequestException("Invalid date format: " + e.getMessage());
        }
        
    }
}
