package com.aziz.paymentapp.controller;

import com.aziz.paymentapp.model.AccountDto;
import com.aziz.paymentapp.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/accounts")
public class AccountController {

  private final AccountService accountService;

  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @GetMapping
  public ResponseEntity getAccounts() {
    final Iterable<AccountDto> accounts = accountService.getAccounts();
    return new ResponseEntity<>(accounts, HttpStatus.OK);
  }
}
