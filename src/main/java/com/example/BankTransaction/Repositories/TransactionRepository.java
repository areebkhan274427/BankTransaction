package com.example.BankTransaction.Repositories;

import com.example.BankTransaction.Models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {


    List<Transaction> getTransactionByBankAccountId(int bankAccountId);
}
