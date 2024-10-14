package com.ecommerce.prices.interfaces.controllers;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.prices.application.PriceUseCase;
import com.ecommerce.prices.interfaces.request.PriceRequest;
import com.ecommerce.prices.interfaces.response.PriceResponse;


@RestController
@RequestMapping("/api/prices")
public class PriceController {
    

   private final PriceUseCase priceUseCase;

    
    public PriceController(PriceUseCase priceUseCase) {
        this.priceUseCase = priceUseCase;
    }

    
    @GetMapping("/search")
    public ResponseEntity<PriceResponse> searchPrices(
            @RequestParam("applicationDate") String applicationDate,
            @RequestParam("productId") Long productId,
            @RequestParam("brandId") Long brandId) {
        
        return new ResponseEntity<>(priceUseCase.searchPrices(applicationDate, productId, brandId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PriceResponse> createPrice(@RequestBody PriceRequest request) {
        return new ResponseEntity<>(priceUseCase.savePrice(request), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<PriceResponse> updatePrice(@PathVariable Long id, @RequestBody PriceRequest request) {
        return new ResponseEntity<>(priceUseCase.updatePrice(id, request), HttpStatus.OK);
    }

    @GetMapping 
    public ResponseEntity<List<PriceResponse>> getAllPrices() {
        return new ResponseEntity<>(priceUseCase.getAllPrices(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PriceResponse> getPriceById(@PathVariable Long id) {
        return new ResponseEntity<>(priceUseCase.getPriceById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrice(@PathVariable Long id) {
        priceUseCase.deletePrice(id);
        return ResponseEntity.noContent().build();
    }
}

