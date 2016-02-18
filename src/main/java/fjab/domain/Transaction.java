package fjab.domain;

import java.math.BigDecimal;

public class Transaction {

  private Account from;
  private Account to;
  private BigDecimal amount;

  public Transaction(){}

  public Transaction(Account from, Account to, BigDecimal amount) {
    this.from = from;
    this.to = to;
    this.amount = amount;
  }

  public Account getFrom() {
    return from;
  }

  public Account getTo() {
    return to;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setFrom(Account from) {
    this.from = from;
  }

  public void setTo(Account to) {
    this.to = to;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }
}
