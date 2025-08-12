package com.arka.arkaperez.infrastructure.adapter.in.web.mapper;

import com.arka.arkaperez.domain.model.Category;
import com.arka.arkaperez.domain.model.Product;
import com.arka.arkaperez.domain.port.out.CategoryRepositoryPort;
import com.arka.arkaperez.infrastructure.adapter.in.web.dto.ProductDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductWebMapper {

  private final CategoryRepositoryPort categoryRepository;

  public ProductWebMapper(CategoryRepositoryPort categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public ProductDto toDTO(Product product) {

    if (product == null) {
      return null;
    }

    ProductDto dto = new ProductDto();
    dto.setId(product.getId());
    dto.setName(product.getName());
    dto.setDescription(product.getDescription());
    dto.setCategoryId(product.getCategory() != null ? product.getCategory().getId() : null);
    dto.setBrand(product.getBrand());
    dto.setUnitPrice(product.getUnitPrice());
    dto.setStock(product.getStock());
    return dto;
  }

  public Product toDomain(ProductDto dto) {
        if (dto == null) {
            return null;
        }

        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setBrand(dto.getBrand());
        product.setUnitPrice(dto.getUnitPrice());
        product.setStock(dto.getStock());

        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + dto.getCategoryId()));
            product.setCategory(category);
        } 

        return product;
    }

  public List<ProductDto> toDTO(List<Product> products) {
    return products.stream()
        .map(this::toDTO)
        .collect(Collectors.toList());
  }
}