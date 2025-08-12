package com.arka.arkaperez.domain.port.out;

import com.arka.arkaperez.domain.model.Cart;
import java.util.List;
import java.util.Optional;

public interface CartRepositoryPort {
  List<Cart> findAll();
  Optional<Cart> findById(Long id);
  Cart save(Cart cart);
  void deleteById(Long id);
  List<Cart> findByState(String state);
  boolean existsById(Long id);
}

