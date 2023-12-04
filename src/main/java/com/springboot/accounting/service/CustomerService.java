package com.springboot.accounting.service;

import com.springboot.accounting.exception.CustomerNotFoundException;
import com.springboot.accounting.model.Customer;
import com.springboot.accounting.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    protected Customer findCustomerById(String id){
        return customerRepository.findById(id)
                .orElseThrow(
                        () -> new CustomerNotFoundException("Customer could not find by id: "+ id));
    }
}
