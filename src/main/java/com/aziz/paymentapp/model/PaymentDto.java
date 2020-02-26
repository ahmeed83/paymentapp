package com.aziz.paymentapp.model;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentDto {

  @NotNull
  private Long fromAccountId;
  @NotNull
  private Long ToAccountId;
  @NotNull
  private double amount;
}
