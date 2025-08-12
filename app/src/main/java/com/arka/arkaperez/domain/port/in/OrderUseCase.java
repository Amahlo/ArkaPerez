package com.arka.arkaperez.domain.port.in;

import com.arka.arkaperez.domain.model.Order;
import com.arka.arkaperez.domain.model.Product;
import com.arka.arkaperez.domain.model.Customer;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderUseCase {

  List<Order> getAllOrders();
  Order getOrderById(Long id);
  Order creatOrder(Order order);
  Order updatOrder(Long id, Order order);
  void deleteOrder(Long id);
  List<Order> getOrdersByProduct(Product product);
  List<Order> getOrdersByDateRage(LocalDateTime start, LocalDateTime end);
  List<Order> getOrdersByCustomer(Customer customer);
}

