package com.springboot.accounting.dto

//Bu yapıyı oluşturma sebebimiz account bilgisinden customer bilgisini de çekebilmemiz.
data class AccountCustomerDto(
        val id: String,
        val name: String,
        val surname: String
)
