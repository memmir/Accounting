package com.springboot.accounting.controller;

import com.springboot.accounting.dto.AccountDto;
import com.springboot.accounting.dto.CreateAccountRequest;
import com.springboot.accounting.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/account")
//@RequestMapping(value = "/v1/account", method = RequestMethod.POST)
public class AccountController {

    private final AccountService accountService;


    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@Valid @RequestBody CreateAccountRequest request){
        return ResponseEntity.ok(accountService.createAccount(request));
    }
}
