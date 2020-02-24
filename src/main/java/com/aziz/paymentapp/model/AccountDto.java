package com.aziz.paymentapp.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDto {
  private Long id;
  private String name;
  private String email;
  private double balance = 200;
}
