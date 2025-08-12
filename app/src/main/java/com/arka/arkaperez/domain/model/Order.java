package com.arka.arkaperez.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;


public class Order {
    private Long id;
    private Customer customer;
    private LocalDateTime date;
    private BigDecimal total;
    private Set<Product> products;

    public Order() {
    }

    public Order(Long id, Customer customer, LocalDateTime date, BigDecimal total, Set<Product> products) {
      this.id = id;
      this.customer = customer;
      this.date = date;
      this.total = total;
      this.products = products;
    }

    public BigDecimal calculateTotal() {

      if (products == null || products.isEmpty()) {
        return BigDecimal.ZERO;
      }
      return products.stream()
          .map(Product::getUnitPrice)
          .filter(price -> price != null)
          .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public boolean isValidOrder() {
      return customer != null &&
             products != null &&
             !products.isEmpty() &&
             total != null &&
             total.compareTo(BigDecimal.ZERO) > 0;
    }

    public void addProduct(Product product) {
      if (product == null) {
        throw new IllegalArgumentException("Product cannot be null");
      }
      if (!product.isAvailable()) {
        throw new IllegalArgumentException("Product is not available");
      }
      products.add(product);
      updateTotal();
    }

    private void updateTotal() {
      this.total = calculateTotal();
    }

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public Customer getCustomer() {
      return customer;
    }

    public void setCustomer(Customer customer) {
      this.customer = customer;
    }

    public LocalDateTime getDate() {
      return date;
    }

    public void setDate(LocalDateTime date) {
      this.date = date;
    }

    public BigDecimal getTotal() {
      return total;
    }

    public void setTotal(BigDecimal total) {
      this.total = total;
    }

    public Set<Product> getProducts() {
      return products;
    }

    public void setProducts(Set<Product> products) {
      this.products = products;
    }
}
  

