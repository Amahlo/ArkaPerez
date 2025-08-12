package com.arka.arkaperez.application.usecase;

import com.arka.arkaperez.domain.model.Cart;
import com.arka.arkaperez.domain.port.in.CartUseCase;
import com.arka.arkaperez.domain.port.out.CartRepositoryPort;

import java.time.LocalDateTime;
import java.util.List;

public class CartApplicationService implements CartUseCase {

    private final CartRepositoryPort cartRepository;

    public CartApplicationService(CartRepositoryPort cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart getCartById(Long id) {
      return cartRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Cart not found with id: " + id));
    }

    @Override
    public Cart createCart(Cart cart) {
        validateCart(cart);
        cart.setCreatedDate(LocalDateTime.now());
        if (cart.getStatus() == null) {
            cart.setStatus("active");
        }
        return cartRepository.save(cart);
    }

    @Override
    public Cart updateCart(Long id, Cart cart) {
      if (!cartRepository.existsById(id)) {
        throw new RuntimeException("Cart not found with id: " + id);
      }
      validateCart(cart);
      cart.setId(id);
      return cartRepository.save(cart);
    }

    @Override
    public void deleteCart(Long id) {
        if (!cartRepository.existsById(id)) {
            throw new RuntimeException("Cart not found with id: " + id);
        }
        cartRepository.deleteById(id);
    }

    @Override
    public List<Cart> getAbandonedCarts() {
      return cartRepository.findByState("ABANDONED");
    }
    
    private void validateCart(Cart cart) {
        if (cart.getCustomer() == null) {
            throw new IllegalArgumentException("Cart must have a customer associated.");
        }
    }

}
  

