package com.aziz.paymentapp.controller;

import com.aziz.paymentapp.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/transactions")
public class TransactionController {

  private final TransactionService transactionService;

  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @GetMapping("{accountId}")
  public ResponseEntity getTransactions(@PathVariable Long accountId) {
    final var transactions = transactionService.getAllTransactions(accountId);
    return new ResponseEntity<>(transactions, HttpStatus.OK);
  }
}
