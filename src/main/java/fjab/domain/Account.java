package fjab.domain;

import java.math.BigDecimal;

public class Account {

  public static final BigDecimal INITIAL_BALANCE = BigDecimal.valueOf(200);

  private String name;
  private String emailAddress;
  private BigDecimal balance = BigDecimal.valueOf(200);

  public Account(){}

  public Account(String name, String emailAddress) {
    this.name = name;
    this.emailAddress = emailAddress;
  }

  /**
   * Any logic to modify the balance of an Account should be implemented in this class as this class knows about the
   * overdraft limitations and therefore how to implement the modification logic correctly. In this case, this method
   * knows that decreasing the balance must be done through the setBalance method to make sure that no overdraft will
   * happen
   * @param amount
   * @return
   */
  public boolean decreaseBalance(BigDecimal amount){
    return setBalance(balance.subtract(amount));
  }

  public void increaseBalance(BigDecimal amount){
    balance = balance.add(amount);
  }

  /**
   * This method implements the logic to prevent overdraft.
   * As this logic is specific for Account objects, it makes sense to encapsulate it inside this class and as part
   * of the method that modifies the balance.
   * @param balance
   * @return
   */
  public boolean setBalance(BigDecimal balance){
    if(balance.compareTo(BigDecimal.ZERO)<0){
      return false;
    }
    else{
      this.balance = balance;
      return true;
    }

  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public String getName() {
    return name;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public BigDecimal getBalance(){
    return balance;
  }


  /**
   * For our purposes, we will consider two Accounts as equal if they have the same name
   * @param o
   * @return
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Account account = (Account) o;

    return name.equals(account.name);

  }

  @Override
  public int hashCode() {
    return name!=null?name.hashCode():1;
  }
}
