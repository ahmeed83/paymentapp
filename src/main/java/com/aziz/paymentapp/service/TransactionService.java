package com.aziz.paymentapp.service;

import com.aziz.paymentapp.entity.AccountEntity;
import com.aziz.paymentapp.entity.TransactionEntity;
import com.aziz.paymentapp.model.TransactionDto;
import com.aziz.paymentapp.repository.AccountRepository;
import com.aziz.paymentapp.repository.TransactionRepository;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TransactionService {

  private final TransactionRepository transactionRepository;
  private final AccountRepository accountRepository;

  /**
   * Constructor.
   *
   * @param transactionRepository transactionRepository
   * @param accountRepository accountRepository
   */
  public TransactionService(TransactionRepository transactionRepository,
                            AccountRepository accountRepository) {
    this.transactionRepository = transactionRepository;
    this.accountRepository = accountRepository;
  }

  /**
   * Get all the transactions for a given account.
   *
   * @param accountId the id of the given account
   * @return list of the transaction of that given account
   */
  public Iterable<TransactionDto> getAllTransactions(final Long accountId) {
    AccountEntity selectAccount = accountRepository.findById(accountId)
                                                   .orElseThrow(() -> new IllegalStateException("Account not found!"));
    final List<TransactionDto> transactionDtos = new ArrayList<>();
    final List<TransactionEntity> transactionEntities = transactionRepository.findTransactionEntityByAccountId(
        selectAccount.getId());
    transactionEntities.forEach(transactionEntity -> {
      final TransactionDto transactionDto = TransactionDto.builder()
                                                          .toAccountName(transactionEntity.getToAccountName())
                                                          .amount(transactionEntity.getAmount())
                                                          .build();
      transactionDtos.add(transactionDto);
    });
    return transactionDtos;
  }

  /**
   * Save new transaction.
   *
   * @param accountEntity accountEntity
   * @param toAccountName toAccountName
   * @param amount amount
   */
  public void saveNewTransaction(AccountEntity accountEntity,
                                 String toAccountName,
                                 double amount) {
    TransactionEntity transactionEntity = new TransactionEntity();
    transactionEntity.setAmount(amount);
    transactionEntity.setToAccountName(toAccountName);
    transactionEntity.setAccount(accountEntity);
    transactionRepository.save(transactionEntity);
  }
}
