package com.springboot.accounting.repository;

import com.springboot.accounting.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,String>
{
}
