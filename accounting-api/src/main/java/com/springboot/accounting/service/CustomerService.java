package com.springboot.accounting.service;

import com.springboot.accounting.dto.CustomerDto;
import com.springboot.accounting.dto.CustomerDtoConverter;
import com.springboot.accounting.exception.CustomerNotFoundException;
import com.springboot.accounting.model.Customer;
import com.springboot.accounting.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter customerDtoConverter;

    public CustomerService(CustomerRepository customerRepository, CustomerDtoConverter customerDtoConverter) {
        this.customerRepository = customerRepository;
        this.customerDtoConverter = customerDtoConverter;
    }

    protected Customer findCustomerById(String id){
        return customerRepository.findById(id)
                .orElseThrow(
                        () -> new CustomerNotFoundException("Customer could not find by id: "+ id));
    }

    public CustomerDto getCustomerById(String customerId) {
        return customerDtoConverter.convertToCustomerDto(findCustomerById(customerId));
    }
}
