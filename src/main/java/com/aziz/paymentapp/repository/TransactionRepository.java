package com.aziz.paymentapp.repository;

import com.aziz.paymentapp.entity.TransactionEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {

  List<TransactionEntity> findTransactionEntityByAccountId(Long accountId);
}
