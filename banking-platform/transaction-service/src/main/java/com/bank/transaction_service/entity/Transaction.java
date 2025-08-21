package com.bank.transaction.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountId;
    private Double amount;
    private String type; // DEPOSIT or WITHDRAW
    private LocalDateTime timestamp;

    // Getters and setters
}
