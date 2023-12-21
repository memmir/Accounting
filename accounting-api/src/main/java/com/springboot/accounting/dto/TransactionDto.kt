package com.springboot.accounting.dto

import com.springboot.accounting.model.Account
import com.springboot.accounting.model.TransactionType
import java.math.BigDecimal
import java.time.LocalDateTime

data class TransactionDto(
        val id: String?,
        val transactionType: TransactionType? = TransactionType.INITIAL,
        val amount: BigDecimal?,
        val transactionDate: LocalDateTime?,

)
