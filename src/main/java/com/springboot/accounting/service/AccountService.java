package com.springboot.accounting.service;

import com.springboot.accounting.dto.AccountDto;
import com.springboot.accounting.dto.AccountDtoConverter;
import com.springboot.accounting.dto.CreateAccountRequest;
import com.springboot.accounting.model.Account;
import com.springboot.accounting.model.Customer;
import com.springboot.accounting.model.Transaction;
import com.springboot.accounting.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final TransactionService transactionService;
    private final AccountDtoConverter accountDtoConverter;

    public AccountService(AccountRepository accountRepository, CustomerService customerService, TransactionService transactionService, AccountDtoConverter accountDtoConverter) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.transactionService = transactionService;
        this.accountDtoConverter = accountDtoConverter;
    }

    public AccountDto createAccount(CreateAccountRequest createAccountRequest){
        Customer customer = customerService.findCustomerById(createAccountRequest.getCustomerId());

        Account account = new Account(
                customer,
                createAccountRequest.getInitialCredit(),
                LocalDateTime.now());

        if(createAccountRequest.getInitialCredit().compareTo(BigDecimal.ZERO) > 0){
            Transaction transaction = transactionService.initiateMoney(account, createAccountRequest.getInitialCredit());
            account.getTransaction().add(transaction);
        }
        Account newAccount = accountRepository.save(account);
        return accountDtoConverter.convert(newAccount);
    }

}
