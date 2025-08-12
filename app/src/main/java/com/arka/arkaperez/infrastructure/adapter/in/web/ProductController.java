package com.arka.arkaperez.infrastructure.adapter.in.web;

import com.arka.arkaperez.domain.model.Product;
import com.arka.arkaperez.domain.port.in.ProductUseCase;
import com.arka.arkaperez.infrastructure.adapter.in.web.dto.ProductDto;
import com.arka.arkaperez.infrastructure.adapter.in.web.mapper.ProductWebMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

  private final ProductUseCase productUseCase;
  private final ProductWebMapper webMapper;

  public ProductController(ProductUseCase productUseCase, ProductWebMapper webMapper) {
    this.productUseCase = productUseCase;
    this.webMapper = webMapper;
  }

  @GetMapping
  public ResponseEntity<List<ProductDto>> getAllProducts() {
    List<Product> products = productUseCase.getAllProducts();
    List<ProductDto> productsDTO = webMapper.toDTO(products);
    return ResponseEntity.ok(productsDTO);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
    Product product = productUseCase.getProductById(id);
    ProductDto productDTO = webMapper.toDTO(product);
    return ResponseEntity.ok(productDTO);
  }

  @PostMapping
  public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDTO) {
    Product product = webMapper.toDomain(productDTO);
    Product savedProduct = productUseCase.createProduct(product);
    ProductDto savedProductDTO = webMapper.toDTO(savedProduct);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedProductDTO);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id,
      @RequestBody ProductDto productDTO) {
    Product product = webMapper.toDomain(productDTO);
    Product updatedProduct = productUseCase.updateProduct(id, product);
    ProductDto updatedProductDTO = webMapper.toDTO(updatedProduct);
    return ResponseEntity.ok(updatedProductDTO);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    productUseCase.deleteProduct(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/category/{name}")
  public ResponseEntity<List<ProductDto>> getProductsByCategory(@PathVariable String name) {
    List<Product> products = productUseCase.getProductsByCategory(name);
    List<ProductDto> productsDTO = webMapper.toDTO(products);
    return ResponseEntity.ok(productsDTO);
  }

  @GetMapping("/search")
  public ResponseEntity<List<ProductDto>> searchProducts(@RequestParam String term) {
    List<Product> products = productUseCase.searchProductsByName(term);
    List<ProductDto> productsDTO = webMapper.toDTO(products);
    return ResponseEntity.ok(productsDTO);
  }

  @GetMapping("/sorted")
  public ResponseEntity<List<ProductDto>> getAllProductsSorted() {
    List<Product> products = productUseCase.getAllProductsSorted();
    List<ProductDto> productsDTO = webMapper.toDTO(products);
    return ResponseEntity.ok(productsDTO);
  }

  @GetMapping("/range")
  public ResponseEntity<List<ProductDto>> getProductsByPriceRange(
      @RequestParam BigDecimal min,
      @RequestParam BigDecimal max) {
    List<Product> products = productUseCase.getProductsByPriceRange(min, max);
    List<ProductDto> productsDTO = webMapper.toDTO(products);
    return ResponseEntity.ok(productsDTO);
  }
}
