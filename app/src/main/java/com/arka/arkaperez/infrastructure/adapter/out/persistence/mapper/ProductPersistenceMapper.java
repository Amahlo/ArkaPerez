package com.arka.arkaperez.infrastructure.adapter.out.persistence.mapper;

import com.arka.arkaperez.domain.model.Product;
import com.arka.arkaperez.infrastructure.adapter.out.persistence.entity.ProductJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductPersistenceMapper {

    private final CategoryPersistenceMapper categoryMapper;

    public ProductPersistenceMapper(CategoryPersistenceMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public ProductJpaEntity toEntity(Product product) {
        if (product == null) {
            return null;
        }

        ProductJpaEntity entity = new ProductJpaEntity();
        entity.setId(product.getId());
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setCategory(categoryMapper.toEntity(product.getCategory()));
        entity.setBrand(product.getBrand());
        entity.setUnitPrice(product.getUnitPrice());
        entity.setStock(product.getStock());
        return entity;
    }

    public Product toDomain(ProductJpaEntity entity) {
        if (entity == null) {
            return null;
        }

        Product product = new Product();
        product.setId(entity.getId());
        product.setName(entity.getName());
        product.setDescription(entity.getDescription());
        product.setCategory(categoryMapper.toDomain(entity.getCategory()));
        product.setBrand(entity.getBrand());
        product.setUnitPrice(entity.getUnitPrice());
        product.setStock(entity.getStock());
        return product;
    }
}     