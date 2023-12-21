package com.springboot.accounting.dto

import com.springboot.accounting.model.Account

data class CustomerDto(
        val id: String?,
        val name: String?,
        val surname: String?,
        val accounts: Set<CustomerAccountDto>
)
