package com.example.BankTransaction.Repositories;

import com.example.BankTransaction.Models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,Integer> {

}
