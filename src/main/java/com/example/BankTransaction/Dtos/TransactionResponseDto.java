package com.example.BankTransaction.Dtos;

import com.example.BankTransaction.Enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDto {
    private int id;

    private TransactionType transactionType;

    private int amount;

    private LocalDateTime transactionDate;


}
