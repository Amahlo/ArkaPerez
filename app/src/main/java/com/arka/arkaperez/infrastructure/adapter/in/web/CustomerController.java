package com.arka.arkaperez.infrastructure.adapter.in.web;

import com.arka.arkaperez.domain.model.Customer;
import com.arka.arkaperez.domain.port.in.CustomerUseCase;
import com.arka.arkaperez.infrastructure.adapter.in.web.dto.CustomerDto;
import com.arka.arkaperez.infrastructure.adapter.in.web.mapper.CustomerWebMapper;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

  private final CustomerUseCase customerUseCase;
  private final CustomerWebMapper mapper;

  public CustomerController(CustomerUseCase customerUseCase, CustomerWebMapper mapper) {
    this.customerUseCase = customerUseCase;
    this.mapper = mapper;
  }
 
  @GetMapping
  public ResponseEntity<List<CustomerDto>> getAllCustomers() {
    List<Customer> customers = customerUseCase.getAllCustomers();
    List<CustomerDto> customerDtos = customers.stream()
        .map(mapper::toDto)
        .collect(Collectors.toList());
    return ResponseEntity.ok(customerDtos);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {
    try {
      Customer customer = customerUseCase.getCustomerById(id);
      return ResponseEntity.ok(mapper.toDto(customer));
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto dto) {
    try {
      Customer customer = mapper.toDomain(dto);
      Customer savedCustomer = customerUseCase.createCustomer(customer);
      return ResponseEntity.ok(mapper.toDto(savedCustomer));
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id, @RequestBody CustomerDto dto) {
    try {
      Customer customer = mapper.toDomain(dto);
      Customer updatedCustomer = customerUseCase.updateCustomer(id, customer);
      return ResponseEntity.ok(mapper.toDto(updatedCustomer));
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
    try {
      customerUseCase.deleteCustomer(id);
      return ResponseEntity.noContent().build();
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/search")
  public ResponseEntity<List<CustomerDto>> searchCustomersByName(@RequestParam String name) {
    List<Customer> customers = customerUseCase.searchCustomersByName(name);
    List<CustomerDto> customerDtos = customers.stream()
        .map(mapper::toDto)
        .collect(Collectors.toList());
    return ResponseEntity.ok(customerDtos);
  }

  @GetMapping("/sorts")
  public ResponseEntity<List<CustomerDto>> getAllCustomersSorted() {
    List<Customer> customers = customerUseCase.getAllCustomersSorted();
    List<CustomerDto> customerDtos = customers.stream()
        .map(mapper::toDto)
        .collect(Collectors.toList());
    return ResponseEntity.ok(customerDtos); 
  }

}


