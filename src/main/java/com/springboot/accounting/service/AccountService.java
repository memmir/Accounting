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
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.Supplier;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final AccountDtoConverter accountDtoConverter;
    private final Clock clock;

    public AccountService(AccountRepository accountRepository, CustomerService customerService, AccountDtoConverter accountDtoConverter, Clock clock) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.accountDtoConverter = accountDtoConverter;
        this.clock = clock;

    }

    public AccountDto createAccount(CreateAccountRequest createAccountRequest){
        Customer customer = customerService.findCustomerById(createAccountRequest.getCustomerId());

        Account account = new Account(
                customer,
                createAccountRequest.getInitialCredit(),
                getLocalDateTimeNow());

        if(createAccountRequest.getInitialCredit().compareTo(BigDecimal.ZERO) > 0){
            //Transaction transaction = transactionService.initiateMoney(account, createAccountRequest.getInitialCredit());
            Transaction transaction = new Transaction(
                    createAccountRequest.getInitialCredit(),
                    account);
            account.getTransaction().add(transaction);
        }
        Account newAccount = accountRepository.save(account);
        return accountDtoConverter.convert(newAccount);
    }
    private LocalDateTime getLocalDateTimeNow(){
        Instant instant = clock.instant();
        return LocalDateTime.ofInstant(instant, Clock.systemDefaultZone().getZone());

    }


}
