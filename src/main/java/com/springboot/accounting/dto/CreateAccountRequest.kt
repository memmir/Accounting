package com.springboot.accounting.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import java.math.BigDecimal

data class CreateAccountRequest(
        @field:NotBlank //Boş olamaz
        val customerId: String,
        @field:Min(0) //0 olamaz
        val initialCredit: BigDecimal
)
