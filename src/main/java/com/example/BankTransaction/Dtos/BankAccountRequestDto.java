package com.example.BankTransaction.Dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankAccountRequestDto {

    private String name;

    private String email;

    private int balance;

}
