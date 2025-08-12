package com.arka.arkaperez.domain.port.in;

import com.arka.arkaperez.domain.model.Customer;
import java.util.List;


public interface CustomerUseCase {
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Long id, Customer customer);
    void deleteCustomer(Long id);
    List<Customer> getAllCustomersSorted();
    List<Customer> searchCustomersByName(String name);
}

