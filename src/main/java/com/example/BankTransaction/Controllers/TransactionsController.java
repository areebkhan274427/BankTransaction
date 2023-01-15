package com.example.BankTransaction.Controllers;

import com.example.BankTransaction.Dtos.TransactionResponseDto;
import com.example.BankTransaction.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionsController {

    @Autowired
    TransactionService transactionService;


    @PutMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestParam int bankAccountId,@RequestParam int amount){
        try{
            transactionService.deposit(bankAccountId, amount);
            return new ResponseEntity<>("Money Deposited", HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>("Deposit Error Happened",HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestParam int bankAccountId,@RequestParam int amount){
        try{
            transactionService.withdraw(bankAccountId, amount);
            return new ResponseEntity<>("Money withdrawed", HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("get_last_n_days_transactions")
    public ResponseEntity<List<TransactionResponseDto>> getTransactions(@RequestParam int bankAccountId,@RequestParam int days){
        try {
            List<TransactionResponseDto> list=transactionService.getTransactions(bankAccountId,days);
            return new ResponseEntity<>(list,HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }
}
