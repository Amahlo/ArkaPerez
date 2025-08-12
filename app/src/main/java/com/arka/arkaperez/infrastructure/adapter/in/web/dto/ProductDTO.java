package com.arka.arkaperez.infrastructure.adapter.in.web.dto;

import java.math.BigDecimal;

public class ProductDTO {

  private Long id;
  private String name;
  private String description;
  private Long categoryId; 
  private String brand;
  private BigDecimal unitPrice;
  private Integer stock;

  public ProductDTO() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public Long getCategoryId() {
    return categoryId;
  }

  public String getBrand() {
    return brand;
  }

  public BigDecimal getUnitPrice() {
    return unitPrice;
  }

  public Integer getStock() {
    return stock;
  }

  public void setName(String name2) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setName'");
  }

  public void setDescription(String description2) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setDescription'");
  }

  public void setCategoryId(Long long1) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setCategoryId'");
  }

  public void setUnitPrice(BigDecimal unitPrice2) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setUnitPrice'");
  }

  public void setStock(Integer stock2) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setStock'");
  }

  public void setBrand(String brand2) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setBrand'");
  }

}