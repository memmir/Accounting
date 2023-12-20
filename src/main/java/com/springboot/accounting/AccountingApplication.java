package com.springboot.accounting;

import com.springboot.accounting.model.Customer;
import com.springboot.accounting.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Clock;
import java.util.HashSet;

@SpringBootApplication
public class AccountingApplication implements CommandLineRunner {

	private final CustomerRepository customerRepository;

	public AccountingApplication(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(AccountingApplication.class, args);
	}

	@Bean
	public Clock clock() {
		return Clock.systemUTC();
	}

	@Override
	public void run(String... args) {
		//Customer customer = customerRepository.save(new Customer("","Mahmut", "Emir", new HashSet<>()));
		Customer customer = customerRepository.save(new Customer("Mahmut", "Emir"));
		System.out.println(customer);
	}

}
