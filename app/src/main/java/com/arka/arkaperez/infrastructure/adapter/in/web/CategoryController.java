package com.arka.arkaperez.infrastructure.adapter.in.web;

import com.arka.arkaperez.domain.model.Category;
import com.arka.arkaperez.domain.port.in.CategoryUseCase;
import com.arka.arkaperez.infrastructure.adapter.in.web.dto.CategoryDto;
import com.arka.arkaperez.infrastructure.adapter.in.web.mapper.CategoryWebMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryUseCase categoryUseCase;
    private final CategoryWebMapper webMapper;

    public CategoryController(CategoryUseCase categoryUseCase, CategoryWebMapper webMapper) {
        this.categoryUseCase = categoryUseCase;
        this.webMapper = webMapper;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<Category> categories = categoryUseCase.getAllCategories();
        List<CategoryDto> categoriesDTO = webMapper.toDTO(categories);
        return ResponseEntity.ok(categoriesDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        Category category = categoryUseCase.getCategoryById(id);
        CategoryDto categoryDTO = webMapper.toDTO(category);
        return ResponseEntity.ok(categoryDTO);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDTO) {
        Category category = webMapper.toDomain(categoryDTO);
        Category savedCategory = categoryUseCase.createCategory(category);
        CategoryDto savedCategoryDTO = webMapper.toDTO(savedCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategoryDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id,
                                                     @RequestBody CategoryDto categoryDTO) {
        Category category = webMapper.toDomain(categoryDTO);
        Category updatedCategory = categoryUseCase.updateCategory(id, category);
        CategoryDto updatedCategoryDTO = webMapper.toDTO(updatedCategory);
        return ResponseEntity.ok(updatedCategoryDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryUseCase.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}