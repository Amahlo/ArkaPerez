package com.arka.arkaperez.domain.port.out;

import com.arka.arkaperez.domain.model.Customer;
import java.util.List;
import java.util.Optional;  

public interface CustomerRepositoryPort {
  List<Customer> findAll();
  Optional<Customer> findById(Long id);
  Customer save(Customer customer);
  void deleteById(Long id);
  List<Customer> findByNameStartingWith(String letter);
  boolean existsById(Long id);
}

