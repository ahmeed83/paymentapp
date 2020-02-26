package com.aziz.paymentapp.model;

import java.util.Date;
import lombok.Data;

@Data
public class CustomerDto {
  private String name;
  private String gender;
  private Date date_of_birth;
}
