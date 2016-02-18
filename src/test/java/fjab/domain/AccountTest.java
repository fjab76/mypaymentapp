package fjab.domain;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import static fjab.domain.Account.INITIAL_BALANCE;

import static org.junit.Assert.*;

/**
 * Created by franciscoalvarez on 17/02/2016.
 */
public class AccountTest {

  @Test
  public void newAccountHasStartingBalanceOf200(){

    //given
    Account account = new Account();

    //then
    Assert.assertTrue(account.getBalance().compareTo(INITIAL_BALANCE)==0);

  }

  @Test
  public void balanceIsNotChangedIfResultsInOverdraft(){

    //given
    Account account = new Account();

    //when
    boolean isBalanceChanged = account.setBalance(BigDecimal.valueOf(-1));

    //then
    Assert.assertFalse(isBalanceChanged);
    Assert.assertTrue(account.getBalance().compareTo(INITIAL_BALANCE) == 0);

  }

  @Test
  public void _200PoundsMinus10PoundsIs190Pounds(){

    //given
    Account account = new Account();

    //when
    boolean isBalanceChanged = account.decreaseBalance(BigDecimal.TEN);

    //then
    Assert.assertTrue(isBalanceChanged);
    Assert.assertTrue(account.getBalance().compareTo(BigDecimal.valueOf(190)) == 0);

  }

  @Test
  public void _200PoundsMinus1000PoundsCannotHappen(){

    //given
    Account account = new Account();

    //when
    boolean isBalanceChanged = account.decreaseBalance(BigDecimal.valueOf(1000));

    //then
    Assert.assertFalse(isBalanceChanged);
    Assert.assertTrue(account.getBalance().compareTo(INITIAL_BALANCE)==0);

  }

  @Test
  public void _200PoundsPlus10PoundsIs210Pounds(){

    //given
    Account account = new Account();

    //when
    account.increaseBalance(BigDecimal.valueOf(10));

    //then
    Assert.assertTrue(account.getBalance().compareTo(BigDecimal.valueOf(210))==0);

  }


}