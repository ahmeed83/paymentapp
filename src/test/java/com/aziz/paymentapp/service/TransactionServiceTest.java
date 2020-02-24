package com.aziz.paymentapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.aziz.paymentapp.entity.AccountEntity;
import com.aziz.paymentapp.entity.TransactionEntity;
import com.aziz.paymentapp.model.TransactionDto;
import com.aziz.paymentapp.repository.AccountRepository;
import com.aziz.paymentapp.repository.TransactionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransactionServiceTest {

  public static final long FROM_ACCOUNT_ID = 3L;
  public static final long TO_ACCOUNT_ID = 5L;
  List<TransactionEntity> transactionEntities = new ArrayList<>();
  AccountEntity toAccount;
  private AccountEntity fromAccount;
  @Mock
  private AccountRepository accountRepository;
  @Mock
  private TransactionRepository transactionRepository;
  @InjectMocks
  private TransactionService transactionService;

  @BeforeEach
  void setup() {
    fromAccount = new AccountEntity();
    fromAccount.setId(FROM_ACCOUNT_ID);
    fromAccount.setEmail("email");
    fromAccount.setName("ahmed");
    fromAccount.setBalance(500.5);
    toAccount = new AccountEntity();
    toAccount.setId(TO_ACCOUNT_ID);
    toAccount.setEmail("email");
    toAccount.setName("ahmed");
    toAccount.setBalance(70);
    when(accountRepository.findById(FROM_ACCOUNT_ID)).thenReturn(Optional.of(fromAccount));
    when(accountRepository.findById(TO_ACCOUNT_ID)).thenReturn(Optional.of(toAccount));

    final var transactionEntity = new TransactionEntity();
    transactionEntity.setAccount(fromAccount);
    transactionEntity.setAmount(100.0);
    transactionEntities.add(transactionEntity);
    when(transactionRepository.findTransactionEntityByAccountId(3L)).thenReturn(transactionEntities);
  }

  @Test
  void getAllTransactions() {
    final List<TransactionDto> allTransactions = (List<TransactionDto>) transactionService.getAllTransactions(
        FROM_ACCOUNT_ID);
    assertEquals(1, allTransactions.size());
  }

  @Test
  void transferToAccount() {

//    transactionService.transferToAccount(FROM_ACCOUNT_ID, TO_ACCOUNT_ID, 50);
//    assertEquals(450.5, fromAccount.getBalance());
//    assertEquals(120, toAccount.getBalance());
  }
}