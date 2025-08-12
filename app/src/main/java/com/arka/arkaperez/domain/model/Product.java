package com.arka.arkaperez.domain.model;

import java.math.BigDecimal;

public class Product {
    private Long id;
    private String name;
    private String description;
    private Category category;
    private String brand;
    private BigDecimal unitPrice;
    private Integer stock;

    public Product() {}

    public boolean hasValidPrice() {
        return unitPrice != null && unitPrice.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isInStock() {
        return stock != null && stock > 0;
    }

    public boolean isAvailable() {
        return hasValidPrice() && isInStock();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
}
