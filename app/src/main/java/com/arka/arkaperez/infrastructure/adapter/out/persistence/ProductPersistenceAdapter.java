package com.arka.arkaperez.infrastructure.adapter.out.persistence;

import com.arka.arkaperez.domain.model.Product;
import com.arka.arkaperez.domain.port.out.ProductRepositoryPort;
import com.arka.arkaperez.infrastructure.adapter.out.persistence.entity.ProductJpaEntity;
import com.arka.arkaperez.infrastructure.adapter.out.persistence.mapper.ProductPersistenceMapper;
import com.arka.arkaperez.infrastructure.adapter.out.persistence.repository.ProductJpaRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductPersistenceAdapter implements ProductRepositoryPort {

  private final ProductJpaRepository jpaRepository;
  private final ProductPersistenceMapper mapper;

  public ProductPersistenceAdapter(ProductJpaRepository jpaRepository,
      ProductPersistenceMapper mapper) {
    this.jpaRepository = jpaRepository;
    this.mapper = mapper;
  }

  @Override
  public List<Product> findAll() {
    List<ProductJpaEntity> entities = jpaRepository.findAll();
    return entities.stream()
        .map(mapper::toDomain)
        .collect(Collectors.toList());
  }

  @Override
  public Optional<Product> findById(Long id) {
    return jpaRepository.findById(id)
        .map(mapper::toDomain);
  }

  @Override
  public Product save(Product product) {
    ProductJpaEntity entity = mapper.toEntity(product);
    ProductJpaEntity savedEntity = jpaRepository.save(entity);
    return mapper.toDomain(savedEntity);
  }

  @Override
  public void deleteById(Long id) {
    jpaRepository.deleteById(id);
  }

  @Override
  public boolean existsById(Long id) {
    return jpaRepository.existsById(id);
  }

  @Override
  public List<Product> findByCategoryName(String categoryName) {
    List<ProductJpaEntity> entities = jpaRepository.findByCategory_Name(categoryName);
    return entities.stream()
        .map(mapper::toDomain)
        .collect(Collectors.toList());
  }

  @Override
  public List<Product> findByNameContainingIgnoreCase(String name) {
    List<ProductJpaEntity> entities = jpaRepository.findByNameContainingIgnoreCase(name);
    return entities.stream()
        .map(mapper::toDomain)
        .collect(Collectors.toList());
  }

  @Override
  public List<Product> findByPriceRange(BigDecimal min, BigDecimal max) {
    List<ProductJpaEntity> entities = jpaRepository.findByPriceRange(min, max);
    return entities.stream()
        .map(mapper::toDomain)
        .collect(Collectors.toList());
  }
}