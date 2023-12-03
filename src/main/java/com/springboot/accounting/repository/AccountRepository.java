package com.springboot.accounting.repository;

import com.springboot.accounting.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,String> {

}
