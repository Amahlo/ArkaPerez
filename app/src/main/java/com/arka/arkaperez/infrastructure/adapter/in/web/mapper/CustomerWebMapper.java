package com.arka.arkaperez.infrastructure.adapter.in.web.mapper;

import com.arka.arkaperez.domain.model.Customer;
import com.arka.arkaperez.infrastructure.adapter.in.web.dto.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerWebMapper {

  public CustomerDto toDto(Customer domain) {
    if (domain == null) {
      return null;
    }

        CustomerDto dto = new CustomerDto();
        dto.setId(domain.getId());
        dto.setName(domain.getName());
        dto.setEmail(domain.getEmail());
        dto.setPhone(domain.getPhone());
        dto.setCountry(domain.getCountry());
        dto.setCity(domain.getCity());
        return dto;
  }

  public Customer toDomain(CustomerDto dto) {
    if (dto == null) {
      return null;
    }

        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setCountry(dto.getCountry());
        customer.setCity(dto.getCity());
        return customer;
  }
}
