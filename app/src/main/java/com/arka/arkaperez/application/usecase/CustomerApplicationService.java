package com.arka.arkaperez.application.usecase;

import com.arka.arkaperez.domain.model.Customer;
import com.arka.arkaperez.domain.port.in.CustomerUseCase;
import com.arka.arkaperez.domain.port.out.CustomerRepositoryPort;


import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerApplicationService implements CustomerUseCase {

  private final CustomerRepositoryPort customerRepository;

  public CustomerApplicationService(CustomerRepositoryPort customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }

  @Override
  public Customer getCustomerById(Long id) {
    return customerRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Customer not found with id " + id));
  }

  @Override
  public Customer createCustomer(Customer customer) {
    validateCustomer(customer);
    return customerRepository.save(customer);
  }

  @Override
  public Customer updateCustomer(Long id, Customer customer) {
    validateCustomer(customer);
    if (!customerRepository.existsById(id)) {
      throw new RuntimeException("Customer not found with id " + id);
    }
    validateCustomer(customer);
    customer.setId(id);
    return customerRepository.save(customer);
  }

  @Override
  public void deleteCustomer(Long id) {
    if (!customerRepository.existsById(id)) {
      throw new RuntimeException("Customer not found with id " + id);
    }
    customerRepository.deleteById(id);
  }

  @Override
  public List<Customer> searchCustomersByName(String name) {
    return customerRepository.findByNameStartingWith(name);
  }

  @Override
  public List<Customer> getAllCustomersSorted() {
    return customerRepository.findAll()
        .stream()
        .sorted(Comparator.comparing(Customer::getName, String.CASE_INSENSITIVE_ORDER))
        .collect(Collectors.toList());
  }

  private void validateCustomer(Customer customer) {
    if (customer.getName() == null || customer.getName().trim().isEmpty()) {
      throw new IllegalArgumentException("Customer name cannot be null or empty");
    }
    if (!customer.isValidEmail()) {
      throw new IllegalArgumentException("Customer must have a valid email");
    }
  }

}
