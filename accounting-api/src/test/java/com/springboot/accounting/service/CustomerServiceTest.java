package com.springboot.accounting.service;

import com.springboot.accounting.dto.CustomerDto;
import com.springboot.accounting.dto.CustomerDtoConverter;
import com.springboot.accounting.exception.CustomerNotFoundException;
import com.springboot.accounting.model.Customer;
import com.springboot.accounting.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.util.Assert;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class CustomerServiceTest {

    private  CustomerService customerService;
    private CustomerRepository customerRepository;
    private CustomerDtoConverter customerDtoConverter;

    @BeforeEach
    public void setUp(){
        customerRepository = mock(CustomerRepository.class);
        customerDtoConverter = mock(CustomerDtoConverter.class);
        customerService = new CustomerService(customerRepository, customerDtoConverter);
    }

    @Test
    public void testFindByCustomerId_whenCustomerIdExists_shouldReturnCustomer(){
        Customer customer = new Customer("id","name","surname", Set.of());

        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.of(customer));

        Customer result = customerService.findCustomerById("id");

        assertEquals(result,customer);
    }

    @Test
    public void testFindByCustomerId_whenCustomerIdDoesNotExists_shouldThrowCustomerNotFoundException(){

        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.findCustomerById("id"));
    }

    @Test
    public void testGetCustomerById_whenCustomerIdExists_shouldReturnCustomer(){
        Customer customer = new Customer("id","name","surname", Set.of());
        CustomerDto customerDto = new CustomerDto("id", "name", "surname", Set.of());

        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.of(customer));
        Mockito.when(customerDtoConverter.convertToCustomerDto(customer)).thenReturn(customerDto);

        CustomerDto result = customerService.getCustomerById("id");

        assertEquals(result,customerDto);
    }
    @Test
    public void testGetCustomerById_whenCustomerIdDoesNotExists_shouldThrowCustomerNotFoundException(){

        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerById("id"));

        //converter ın hiçbir metodunu çağırma demek. Converter ın hiçbir metodu çağırılmadı demek.
        Mockito.verifyNoInteractions(customerDtoConverter);


    }
}