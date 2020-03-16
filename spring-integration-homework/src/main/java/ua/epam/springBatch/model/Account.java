package ua.epam.springBatch.model;

import java.math.BigDecimal;

public class Account {

  private static final BigDecimal margin = new BigDecimal(10);
  private Long id;
  private BigDecimal balance;
  private String email;

  public Account(Long id, BigDecimal balance, String email) {
    this.id = id;
    this.balance = balance;
    this.email = email;
  }

  public boolean isLowBalance() {
    return balance.compareTo(margin) < 0;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public String getEmail() {
    return email;
  }
}
