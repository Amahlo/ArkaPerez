package com.arka.arkaperez.domain.model;

import java.time.LocalDateTime; 

public class Cart {
  
  private Long id;
  private Customer customer;
  private LocalDateTime createdDate;
  private String status;

  public Cart() {
  }

  public Cart(Long id, Customer customer, LocalDateTime createdDate, String status) {
    this.id = id;
    this.customer = customer;
    this.createdDate = createdDate;
    this.status = status;
  }

  public boolean isAbandoned() {
    return "abandoned".equals(status);
  }

  public boolean isActive() {
    return "active".equals(status);
  }

  public void abandon() {
    this.status = "abandoned";
  }

  public void activate() {
    this.status = "active";
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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

