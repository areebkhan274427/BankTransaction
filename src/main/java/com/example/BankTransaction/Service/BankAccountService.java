package com.example.BankTransaction.Service;

import com.example.BankTransaction.Convertors.BankAccountConvertor;
import com.example.BankTransaction.Dtos.BankAccountRequestDto;
import com.example.BankTransaction.Enums.TransactionType;
import com.example.BankTransaction.Models.BankAccount;
import com.example.BankTransaction.Models.Transaction;
import com.example.BankTransaction.Repositories.BankAccountRepository;
import com.example.BankTransaction.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankAccountService {

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    TransactionService transactionService;

    public void createBankAccount(BankAccountRequestDto bankAccountRequestDto) throws Exception{

        BankAccount bankAccount= BankAccountConvertor.convertDtoToEntity(bankAccountRequestDto);

        Transaction transaction=new Transaction(TransactionType.DEPOSIT, bankAccount.getBalance());

        List<Transaction> transactionList=bankAccount.getTransactionList();
        if(transactionList==null) transactionList=new ArrayList<>();
        transactionList.add(transaction);
        bankAccount.setTransactionList(transactionList);

        transaction.setBankAccount(bankAccount);



        bankAccountRepository.save(bankAccount);
    }

    public int getBalance(int bankAccountId)throws Exception{
        if(bankAccountRepository.findById(bankAccountId).isPresent()){
            return bankAccountRepository.findById(bankAccountId).get().getBalance();
        }
        else
            throw new Exception("Bank Account Not Found");
    }
}
