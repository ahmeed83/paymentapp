package com.aziz.paymentapp.service;

import com.aziz.paymentapp.entity.AccountEntity;
import com.aziz.paymentapp.repository.AccountRepository;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 * Class will be responsible to do the transfer from one account to another.
 */
@Service
@Transactional
public class PaymentService {

  private final AccountRepository accountRepository;
  private final TransactionService transactionService;

  public PaymentService(AccountRepository accountRepository,
                        TransactionService transactionService) {
    this.accountRepository = accountRepository;
    this.transactionService = transactionService;
  }

  /**
   * Method that transfer a given account from one account to another.
   *
   * @param fromAccountId id of the from account
   * @param toAccountId id of the to account
   * @param amount the amount
   */
  public void transferToAccount(final Long fromAccountId,
                                final Long toAccountId,
                                final double amount) {
    if (amount <= 0) {
      throw new IllegalStateException("Can not transfer zero or less than zero");
    } else {
      AccountEntity selectedFromAccount = accountRepository.findById(fromAccountId)
                                                           .orElseThrow(() -> new IllegalStateException(
                                                               "From Account not found!"));
      AccountEntity selectedToAccount = accountRepository.findById(toAccountId)
                                                         .orElseThrow(() -> new IllegalStateException(
                                                             "To Account not found!"));
      cutAmountFromAccount(amount, selectedFromAccount);
      addAmountToAccount(amount, selectedToAccount);
      transactionService.saveNewTransaction(selectedFromAccount, selectedToAccount.getName(), amount);
    }
  }

  /**
   * This method will check if the from account has enough money, if so it will decrees its balance.
   *
   * @param amount amount given
   */
  private void cutAmountFromAccount(double amount,
                                    final AccountEntity selectedFromAccount) {
    if (selectedFromAccount.getBalance() < amount) {
      throw new IllegalStateException(String.format("Selected account %s does not have enough money",
                                                    selectedFromAccount.getName()));
    }
    double newFromAccountBalance = selectedFromAccount.getBalance() - amount;
    updateNewAccount(selectedFromAccount, newFromAccountBalance);
  }

  /**
   * This method woill add the amount to the TO account
   *
   * @param amount amount given
   * @param selectedToAccount selectedToAccount
   */
  private void addAmountToAccount(double amount,
                                  AccountEntity selectedToAccount) {
    double newToAccountBalance = selectedToAccount.getBalance() + amount;
    updateNewAccount(selectedToAccount, newToAccountBalance);
  }

  /**
   * Save the new account detaild to the database.
   *
   * @param accountEntity accountEntity.
   * @param newAccountBalance new account balance.
   */
  private void updateNewAccount(AccountEntity accountEntity,
                                double newAccountBalance) {
    AccountEntity accountEntityNew = new AccountEntity();
    accountEntityNew.setBalance(newAccountBalance);

    accountEntityNew.setId(accountEntity.getId());
    accountEntityNew.setEmail(accountEntity.getEmail());
    accountEntityNew.setName(accountEntity.getName());
    accountEntityNew.setCustomer(accountEntity.getCustomer());
    accountRepository.save(accountEntityNew);
  }
}
