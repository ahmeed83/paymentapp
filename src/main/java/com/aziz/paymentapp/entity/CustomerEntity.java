package com.aziz.paymentapp.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class CustomerEntity {

  @Id
  @GeneratedValue
  private Long id;
  @NotNull
  private String name;
  @NotNull
  private String gender;
  @NotNull
  private Date date_of_birth;
}