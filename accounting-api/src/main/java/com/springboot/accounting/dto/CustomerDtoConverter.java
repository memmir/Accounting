package com.springboot.accounting.dto;

import com.springboot.accounting.model.Customer;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CustomerDtoConverter {

    private final CustomerAccountDtoConverter customerAccountDtoConverter;

    public CustomerDtoConverter(CustomerAccountDtoConverter customerAccountDtoConverter) {
        this.customerAccountDtoConverter = customerAccountDtoConverter;
    }

    public AccountCustomerDto convertToAccountCustomer(Customer from){
        if(from == null){
            return new AccountCustomerDto("","","");
        }
        return new AccountCustomerDto(from.getId(), from.getName(), from.getSurname());
    }
    /*public AccountCustomerDto convertToAccountCustomer(Optional<Customer> from) {
        return from.map(f -> new AccountCustomerDto(f.getId(), f.getName(), f.getSurname())).orElse(null);
    }
*/

    public CustomerDto convertToCustomerDto(Customer from) {
        return new CustomerDto(
                from.getId(),
                from.getName(),
                from.getSurname(),
                from.getAccounts().stream().map(customerAccountDtoConverter::convert).collect(Collectors.toSet())
        );
    }
}
