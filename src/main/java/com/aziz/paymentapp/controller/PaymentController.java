package com.aziz.paymentapp.controller;

import com.aziz.paymentapp.model.PaymentDto;
import com.aziz.paymentapp.service.PaymentService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/payment")
public class PaymentController {

  private final PaymentService paymentService;

  public PaymentController(PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  @PostMapping
  public ResponseEntity transfer(@RequestBody @Valid PaymentDto paymentDto) {
    paymentService.transferToAccount(paymentDto.getFromAccountId(),
                                     paymentDto.getToAccountId(),
                                     paymentDto.getAmount());
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
