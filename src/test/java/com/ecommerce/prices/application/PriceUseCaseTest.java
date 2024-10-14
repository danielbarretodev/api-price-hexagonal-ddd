package com.ecommerce.prices.application;

import com.ecommerce.prices.interfaces.mappers.PriceMapper;
import com.ecommerce.prices.domain.exception.PriceNotFoundException;
import com.ecommerce.prices.domain.model.Price;
import com.ecommerce.prices.domain.port.PriceRepositoryPort;
import com.ecommerce.prices.interfaces.request.PriceRequest;
import com.ecommerce.prices.interfaces.response.PriceResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class PriceUseCaseTest {

    private PriceRepositoryPort priceRepositoryPort;
    private PriceMapper priceMapper;
    private PriceUseCase priceUseCase;

    @BeforeEach
    void setUp() {
        priceRepositoryPort = mock(PriceRepositoryPort.class);
        priceMapper = mock(PriceMapper.class);
        priceUseCase = new PriceUseCase(priceRepositoryPort, priceMapper);
    }

    @Test
    void testSavePrice() {
        PriceRequest priceRequest = new PriceRequest(1L, "2024-10-11 10:00:00", "2024-10-11 11:00:00", 1L, 1L, 1, BigDecimal.valueOf(100.00), "USD");
        Price price = Price.builder()
                .productId(1L)
                .brandId(1L)
                .startDate(Timestamp.valueOf("2024-10-11 10:00:00"))
                .endDate(Timestamp.valueOf("2024-10-11 11:00:00"))
                .priceList(1L)
                .priority(1)
                .price(BigDecimal.valueOf(100.00))
                .currency("USD")
                .build();
        
        PriceResponse expectedResponse = new PriceResponse(1L,1L, 1L, 1L, "2024-10-11 10:00:00", "2024-10-11 11:00:00", BigDecimal.valueOf(100.00));

        when(priceMapper.toPrice(priceRequest)).thenReturn(price);
        when(priceRepositoryPort.save(price)).thenReturn(price);
        when(priceMapper.toPriceResponse(price)).thenReturn(expectedResponse);

        PriceResponse response = priceUseCase.savePrice(priceRequest);

        assertNotNull(response);
        assertEquals(expectedResponse, response);
        verify(priceMapper).toPrice(priceRequest);
        verify(priceRepositoryPort).save(price);
        verify(priceMapper).toPriceResponse(price);
    }

    @Test
    void testGetAllPrices() {
        Price price = Price.builder()
                .productId(1L)
                .brandId(1L)
                .startDate(Timestamp.valueOf("2024-10-11 10:00:00"))
                .endDate(Timestamp.valueOf("2024-10-11 11:00:00"))
                .priceList(1L)
                .priority(1)
                .price(BigDecimal.valueOf(100.00))
                .currency("USD")
                .build();
        
        PriceResponse priceResponse = new PriceResponse(1L,1L, 1L, 1L, "2024-10-11 10:00:00", "2024-10-11 11:00:00", BigDecimal.valueOf(100.00));

        when(priceRepositoryPort.findAll()).thenReturn(List.of(price));
        when(priceMapper.toPriceResponse(price)).thenReturn(priceResponse);

        List<PriceResponse> responses = priceUseCase.getAllPrices();

        assertEquals(1, responses.size());
        assertEquals(priceResponse, responses.get(0));
        verify(priceRepositoryPort).findAll();
    }

    @Test
    void testGetPriceByIdFound() {
        Long id = 1L;
        Price price = Price.builder()
                .productId(1L)
                .brandId(1L)
                .startDate(Timestamp.valueOf("2024-10-11 10:00:00"))
                .endDate(Timestamp.valueOf("2024-10-11 11:00:00"))
                .priceList(1L)
                .priority(1)
                .price(BigDecimal.valueOf(100.00))
                .currency("USD")
                .build();
        
        PriceResponse expectedResponse = new PriceResponse(1L,1L, 1L, 1L, "2024-10-11 10:00:00", "2024-10-11 11:00:00", BigDecimal.valueOf(100.00));

        when(priceRepositoryPort.findById(id)).thenReturn(Optional.of(price));
        when(priceMapper.toPriceResponse(price)).thenReturn(expectedResponse);

        PriceResponse response = priceUseCase.getPriceById(id);

        assertEquals(expectedResponse, response);
        verify(priceRepositoryPort).findById(id);
    }

    @Test
    void testGetPriceByIdNotFound() {
        Long id = 1L;
        when(priceRepositoryPort.findById(id)).thenReturn(Optional.empty());

        assertThrows(PriceNotFoundException.class, () -> priceUseCase.getPriceById(id));

        verify(priceRepositoryPort).findById(id);
    }

    @Test
    void testDeletePriceSuccess() {
        Long id = 1L;
        Price price = Price.builder()
                .productId(1L)
                .brandId(1L)
                .startDate(Timestamp.valueOf("2024-10-11 10:00:00"))
                .endDate(Timestamp.valueOf("2024-10-11 11:00:00"))
                .priceList(1L)
                .priority(1)
                .price(BigDecimal.valueOf(100.00))
                .currency("USD")
                .build();
        
        when(priceRepositoryPort.findById(id)).thenReturn(Optional.of(price));

        boolean result = priceUseCase.deletePrice(id);

        assertTrue(result);
        verify(priceRepositoryPort).deleteById(id);
    }

    @Test
    void testDeletePriceNotFound() {
     
        Long id = 1L;
        when(priceRepositoryPort.findById(id)).thenReturn(Optional.empty());

       
        assertThrows(PriceNotFoundException.class, () -> priceUseCase.deletePrice(id));
        verify(priceRepositoryPort).findById(id); // Verifica que se llamó al método findById
        verify(priceRepositoryPort, never()).deleteById(id); // Verifica que no se llamó a deleteById
    }


@Test
void testSearchPricesFound() {
    String applicationDate = "2024-10-11 10:00:00";
    Long productId = 1L;
    Long brandId = 1L;

    Price price = Price.builder()
            .id(1L) 
            .brandId(1L)
            .startDate(Timestamp.valueOf("2024-10-11 10:00:00"))
            .endDate(Timestamp.valueOf("2024-10-11 11:00:00"))
            .priceList(1L)
            .priority(1)
            .price(BigDecimal.valueOf(100.00))
            .currency("USD")
            .build();
    
    PriceResponse expectedResponse = new PriceResponse(
            1L,
            1L,  
            1L,  
            1L,  
            "2024-10-11 10:00:00", 
            "2024-10-11 11:00:00",  
            BigDecimal.valueOf(100.00)  
    );

    when(priceRepositoryPort.searchPrices(any(), eq(productId), eq(brandId))).thenReturn(Optional.of(price));
    when(priceMapper.toPriceResponse(price)).thenReturn(expectedResponse);

    PriceResponse response = priceUseCase.searchPrices(applicationDate, productId, brandId);

    assertEquals(expectedResponse, response, "The expected price response does not match the actual response");
    verify(priceRepositoryPort).searchPrices(any(), eq(productId), eq(brandId));
}


   @Test
void testSearchPricesNotFound() {
    String applicationDate = "2024-10-11 10:00:00";
    Long productId = 1L;
    Long brandId = 1L;

    when(priceRepositoryPort.searchPrices(any(), eq(productId), eq(brandId))).thenReturn(Optional.empty());

    assertThrows(PriceNotFoundException.class, () -> priceUseCase.searchPrices(applicationDate, productId, brandId));
    verify(priceRepositoryPort).searchPrices(any(), eq(productId), eq(brandId)); 
}
}
