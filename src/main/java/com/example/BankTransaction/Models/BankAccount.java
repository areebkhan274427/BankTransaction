package com.example.BankTransaction.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "BankAccounts")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int balance;

    @CreationTimestamp
    private LocalDateTime createdOn;

    @Column(nullable = false,unique = true)
    private String email;

    @OneToMany(mappedBy = "bankAccount",cascade = CascadeType.ALL)
    private List<Transaction> transactionList;


}
