package com.example.BankTransaction.Controllers;

import com.example.BankTransaction.Dtos.BankAccountRequestDto;
import com.example.BankTransaction.Service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank")
public class BankAccountController {

    @Autowired
    BankAccountService bankAccountService;


    @PostMapping("/create")
    public ResponseEntity<String> createBank(@RequestBody BankAccountRequestDto bankAccountRequestDto){
        try{
            bankAccountService.createBankAccount(bankAccountRequestDto);
            return new ResponseEntity<>("Bank Account Created", HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }

    @GetMapping("get_balance")
    public ResponseEntity<Integer> getBalance(@RequestParam int bankAccountId){
        try{
            int balance=bankAccountService.getBalance(bankAccountId);
            return new ResponseEntity<>(balance,HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
