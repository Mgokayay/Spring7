package com.example.spring7.dto;

public record AccountResponse(Long id,String accountName,Double moneyAmount,CustomerResponse customerResponse) {
}
