package com.springboot.accounting.dto

import java.math.BigDecimal
import java.time.LocalDateTime

//Bu yapıyı oluşturma sebebimiz customer bilgisinden account bilgisini de çekebilmemiz.
data class CustomerAccountDto(
        val id:String,
        var balance: BigDecimal= BigDecimal.ZERO,
        val transactions: Set<TransactionDto>,
        val creationDate: LocalDateTime
)
