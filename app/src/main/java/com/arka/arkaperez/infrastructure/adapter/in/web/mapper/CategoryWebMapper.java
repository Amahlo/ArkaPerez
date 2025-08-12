package com.arka.arkaperez.infrastructure.adapter.in.web.mapper;

import com.arka.arkaperez.domain.model.Category;
import com.arka.arkaperez.infrastructure.adapter.in.web.dto.CategoryDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryWebMapper {

  public CategoryDto toDTO(Category category) {
    if (category == null) {
      return null;
    }
    return new CategoryDto(category.getId(), category.getName());
  }

  public Category toDomain(CategoryDto dto) {
    if (dto == null) {
      return null;
    }

    Category category = new Category();
    category.setId(dto.getId());
    category.setName(dto.getName());
    return category;
  }

  public List<CategoryDto> toDTO(List<Category> categories) {
    return categories.stream()
        .map(this::toDTO)
        .collect(Collectors.toList());
  }
}