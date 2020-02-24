package com.aziz.paymentapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@Entity
@Table(name = "account")
@DynamicInsert
@DynamicUpdate
public class AccountEntity {

  @Id
  @GeneratedValue
  private Long id;
  @NotNull
  private String name;
  @NotNull
  private String email;
  @NotNull
  private double balance;
  @ManyToOne
  @JoinColumn(name = "customer_id")
  private CustomerEntity customer;
}
