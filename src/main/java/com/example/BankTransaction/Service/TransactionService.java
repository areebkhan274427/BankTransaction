package com.example.BankTransaction.Service;

import com.example.BankTransaction.Dtos.TransactionResponseDto;
import com.example.BankTransaction.Enums.TransactionType;
import com.example.BankTransaction.Models.BankAccount;
import com.example.BankTransaction.Models.Transaction;
import com.example.BankTransaction.Repositories.BankAccountRepository;
import com.example.BankTransaction.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BankAccountRepository bankAccountRepository;

    public void deposit(int bankAccountId,int amount) throws Exception{
        BankAccount bankAccount=bankAccountRepository.findById(bankAccountId).get();

        Transaction transaction=new Transaction(TransactionType.DEPOSIT,amount);

        int currentBalance=bankAccount.getBalance();
        int newBalance=currentBalance+amount;
        bankAccount.setBalance(newBalance);

        transaction.setBankAccount(bankAccount);

        List<Transaction> transactionList=bankAccount.getTransactionList();
        if(transactionList==null)
            transactionList=new ArrayList<>();

        transactionList.add(transaction);
        bankAccount.setTransactionList(transactionList);



        bankAccountRepository.save(bankAccount);

    }

    public void withdraw(int bankAccountId,int amount)throws Exception{
        BankAccount bankAccount=bankAccountRepository.findById(bankAccountId).get();

        if(amount>bankAccount.getBalance()){
            throw new Exception("Insufficient Balance");
        }

        Transaction transaction=new Transaction(TransactionType.WITHDRAW,amount);

        int currentBalance=bankAccount.getBalance();
        int newBalance=currentBalance-amount;
        bankAccount.setBalance(newBalance);

        transaction.setBankAccount(bankAccount);

        List<Transaction> transactionList=bankAccount.getTransactionList();
        if(transactionList==null)
            transactionList=new ArrayList<>();

        transactionList.add(transaction);
        bankAccount.setTransactionList(transactionList);



        bankAccountRepository.save(bankAccount);
    }

    public List<TransactionResponseDto> getTransactions(int bankAccountId,int days)throws Exception{

        List<TransactionResponseDto> list=new ArrayList<>();
        List<Transaction> transactionList=transactionRepository.getTransactionByBankAccountId(bankAccountId);

        LocalDateTime date =LocalDateTime.now();
        LocalDateTime pastDate=date.minusDays(days);


        for(Transaction t:transactionList){

            LocalDateTime d=t.getTransactionDate();
            TransactionResponseDto transactionResponseDto=new TransactionResponseDto();

            if(t.getTransactionDate().isAfter(pastDate)){
              transactionResponseDto=TransactionResponseDto.builder().id(t.getId()).transactionType(t.getTransactionType())
                    .amount(t.getAmount()).transactionDate(t.getTransactionDate()).build();

            list.add(transactionResponseDto);
            }
        }

        return list;

    }



}
