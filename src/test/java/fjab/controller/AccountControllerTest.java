package fjab.controller;

import fjab.domain.Account;
import fjab.domain.Transaction;
import fjab.service.AccountService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {

  private AccountController controller;
  @Mock
  private AccountService accountService;

  @Before
  public void setUp() {
    controller = new AccountController(accountService);
  }

  @Test
  public void viewToDisplayListOfAccountsIsCalled_transactions() throws Exception {

    //when
    ModelAndView mav = controller.accountList();

    //then
    Assert.assertEquals("View name should be 'transactions'", "transactions", mav.getViewName());
  }

  @Test
  public void modelToDisplayListOfAccountsContainsAListOfAccounts() {

    //given
    List<Account> expectedList = Arrays.asList(new Account(), new Account());
    when(accountService.accountList()).thenReturn(expectedList);

    //when
    ModelAndView mav = controller.accountList();

    //then
    Assert.assertTrue("Model should contain a key called 'accounts'", mav.getModel().containsKey("accounts"));
    Assert.assertEquals("The key 'accounts' should be a list of accounts", expectedList, mav.getModel().get("accounts"));
    ;
  }

  @Test
  public void modelToDisplayListOfAccountsContainsAnAccountToBackTheForm() {

    //when
    ModelAndView mav = controller.accountList();

    //then
    Assert.assertTrue("Model should contain a key called 'accountForm'", mav.getModel().containsKey("accountForm"));
    Assert.assertTrue("The key 'accountForm' should be an Account", mav.getModel().get("accountForm") instanceof Account);
  }

  @Test
  public void viewToDisplayListOfTransactionsForAnAccountIsCalled_transactions() throws Exception {

    //when
    ModelAndView mav = controller.accountTransactions(new Account());

    //then
    Assert.assertEquals("View name should be 'transactions'", "transactions", mav.getViewName());
  }

  @Test
  public void modelToDisplayListOfTransactionsForAnAccountContainsAListOfAccounts() {

    //given
    List<Account> expectedList = Arrays.asList(new Account(), new Account());
    when(accountService.accountList()).thenReturn(expectedList);

    //when
    ModelAndView mav = controller.accountTransactions(new Account());

    //then
    Assert.assertTrue("Model should contain a key called 'accounts'", mav.getModel().containsKey("accounts"));
    Assert.assertEquals("The key 'accounts' should be a list of accounts", expectedList, mav.getModel().get("accounts"));
    ;
  }

  @Test
  public void modelToDisplayListOfTransactionsForAnAccountContainsAnAccountToBackTheForm() {

    //when
    ModelAndView mav = controller.accountTransactions(new Account());

    //then
    Assert.assertTrue("Model should contain a key called 'accountForm'", mav.getModel().containsKey("accountForm"));
    Assert.assertTrue("The key 'accountForm' should be an Account", mav.getModel().get("accountForm") instanceof Account);
  }

  @Test
  public void modelToDisplayListOfTransactionsForAnAccountContainsAListOfTransactions() {

    //given
    List<Transaction> expectedList = Arrays.asList(new Transaction(), new Transaction());
    when(accountService.accountTransactions(any())).thenReturn(expectedList);

    //when
    ModelAndView mav = controller.accountTransactions(new Account());

    //then
    Assert.assertTrue("Model should contain a key called 'transactions'", mav.getModel().containsKey("transactions"));
    Assert.assertEquals("The key 'transactions' should be a list of transactions", expectedList, mav.getModel().get("transactions"));
    ;
  }

}