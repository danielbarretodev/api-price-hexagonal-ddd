package com.ecommerce.prices.infraestructure.persistence;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ecommerce.prices.domain.model.Price;
import com.ecommerce.prices.domain.port.PriceRepositoryPort;
import com.ecommerce.prices.infraestructure.entities.PriceEntity;
import com.ecommerce.prices.infraestructure.mappers.PriceEntityMapper;

@Component
public class JpaPriceRepositoryAdapter implements PriceRepositoryPort {

    private final JpaPriceRepository jpaPriceRepository; // Tu repositorio de JPA para PriceEntity
    private final PriceEntityMapper priceEntityMapper; // Mapper para convertir entre Price y PriceEntity

    public JpaPriceRepositoryAdapter(JpaPriceRepository jpaPriceRepository, PriceEntityMapper priceEntityMapper) {
        this.jpaPriceRepository = jpaPriceRepository;
        this.priceEntityMapper = priceEntityMapper;
    }

    @Override
    public Price save(Price price) {
        // Convertir Price (entidad de dominio) a PriceEntity (entidad de persistencia)
        PriceEntity priceEntity = priceEntityMapper.toEntity(price);
        
        // Guardar PriceEntity en la base de datos
        PriceEntity savedEntity = jpaPriceRepository.save(priceEntity);
        
        // Convertir PriceEntity de nuevo a Price (entidad de dominio)
        return priceEntityMapper.toDomain(savedEntity);
    }

    @Override
    public List<Price> findAll() {
        // Obtener todas las entidades de persistencia
        List<PriceEntity> priceEntities = jpaPriceRepository.findAll();
        
        // Convertir la lista de PriceEntity a Price (entidades de dominio)
        return priceEntities.stream()
                .map(priceEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Price> findById(Long id) {
        // Buscar la entidad de persistencia por ID
        return jpaPriceRepository.findById(id)
                .map(priceEntityMapper::toDomain); // Convertir a Price
    }

    @Override
    public void deleteById(Long id) {
        // Eliminar la entidad de persistencia por ID
        jpaPriceRepository.deleteById(id);
    }

    @Override
    public Optional<Price> searchPrices(Timestamp applicationDate, Long productId, Long brandId) {
        // Buscar precios en la base de datos usando el repositorio
        return jpaPriceRepository.searchPrices(applicationDate, productId, brandId)
                .map(priceEntityMapper::toDomain); // Convertir a Price
    }
}
