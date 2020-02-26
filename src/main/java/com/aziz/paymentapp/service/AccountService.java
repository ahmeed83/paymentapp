package com.aziz.paymentapp.service;

import com.aziz.paymentapp.entity.AccountEntity;
import com.aziz.paymentapp.model.AccountDto;
import com.aziz.paymentapp.repository.AccountRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

  private final AccountRepository accountRepository;

  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public Iterable<AccountDto> getAccounts() {
    final Iterable<AccountEntity> accountsFromDb = accountRepository.findAll();
    List<AccountDto> accounts = new ArrayList<>();
    accountsFromDb.forEach(accountEntity -> {
      final AccountDto build = AccountDto.builder()
                                         .id(accountEntity.getId())
                                         .name(accountEntity.getName())
                                         .email(accountEntity.getEmail())
                                         .balance(accountEntity.getBalance())
                                         .build();
      accounts.add(build);
    });

    return accounts;
  }
}
