package com.example.BankTransaction.Convertors;

import com.example.BankTransaction.Dtos.BankAccountRequestDto;
import com.example.BankTransaction.Models.BankAccount;

public class BankAccountConvertor {

    public static BankAccount convertDtoToEntity(BankAccountRequestDto bankAccountRequestDto){
        BankAccount bankAccount=BankAccount.builder().name(bankAccountRequestDto.getName())
                .balance(bankAccountRequestDto.getBalance()).email(bankAccountRequestDto.getEmail()).build();
        return bankAccount;
    }
}
