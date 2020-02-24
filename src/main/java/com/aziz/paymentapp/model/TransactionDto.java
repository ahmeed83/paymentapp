package com.aziz.paymentapp.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionDto {
  private String toAccountName;
  private double amount;
}
