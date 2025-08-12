package com.arka.arkaperez.domain.port.out;

import com.arka.arkaperez.domain.model.Order;
import com.arka.arkaperez.domain.model.Product;
import com.arka.arkaperez.domain.model.Customer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepositoryPort {
  List<Order> findAll();
  Optional<Order> findById(Long id);
  Order save(Order order);
  void deleteById(Long id);
  List<Order> findByProductsContaining(Product product);
  List<Order> findByDateBetween(LocalDateTime start, LocalDateTime end);
  List<Order> findByCustomer(Customer customer);
  boolean existsById(Long id);
}
