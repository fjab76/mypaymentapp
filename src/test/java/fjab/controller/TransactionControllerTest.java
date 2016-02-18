package fjab.controller;

import fjab.domain.Account;
import fjab.domain.Transaction;
import fjab.service.AccountService;
import fjab.service.TransactionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TransactionControllerTest {

  private TransactionController controller;
  @Mock
  private AccountService accountService;
  @Mock
  private TransactionService transactionService;
  @Mock
  private BindingResult bindingResult;

  @Before
  public void setUp(){
    controller = new TransactionController(accountService,transactionService);
  }

  @Test
  public void viewToDisplayPaymentFormIsCalled_pay(){

    //when
    ModelAndView mav = controller.showForm();

    //then
    Assert.assertEquals("View name should be 'pay'","pay",mav.getViewName());
  }

  @Test
  public void modelToDisplayPaymentFormContainsAListOfAccounts(){

    //given
    List<Account> expectedList = Arrays.asList(new Account(),new Account());
    when(accountService.accountList()).thenReturn(expectedList);

    //when
    ModelAndView mav = controller.showForm();

    //then
    Assert.assertTrue("Model should contain a key called 'accounts'", mav.getModel().containsKey("accounts"));
    Assert.assertEquals("The key 'transactions' should be a transaction",expectedList,mav.getModel().get("accounts"));;
  }

  @Test
  public void modelToDisplayPaymentFormContainsATransactionToBackTheForm(){

    //when
    ModelAndView mav = controller.showForm();

    //then
    Assert.assertTrue("Model should contain a key called 'transaction'", mav.getModel().containsKey("transaction"));
    Assert.assertTrue("The key 'transaction' should be a Transaction", mav.getModel().get("transaction") instanceof Transaction);
  }

  @Test
  public void viewToDisplayResultOfPaymentIsCalled_pay(){

    //when
    ModelAndView mav = controller.submitForm(new Transaction(),bindingResult);

    //then
    Assert.assertEquals("View name should be 'pay'","pay",mav.getViewName());
  }

  @Test
  public void modelToDisplayResultOfPaymentContainsAListOfAccounts(){

    //given
    List<Account> expectedList = Arrays.asList(new Account(),new Account());
    when(accountService.accountList()).thenReturn(expectedList);

    //when
    ModelAndView mav = controller.submitForm(new Transaction(), bindingResult);

    //then
    Assert.assertTrue("Model should contain a key called 'accounts'", mav.getModel().containsKey("accounts"));
    Assert.assertEquals("The key 'transactions' should be a transaction",expectedList,mav.getModel().get("accounts"));;
  }

  @Test
  public void modelToDisplayResultOfPaymentContainsATransactionToBackTheForm(){

    //when
    ModelAndView mav = controller.submitForm(new Transaction(), bindingResult);

    //then
    Assert.assertTrue("Model should contain a key called 'transaction'", mav.getModel().containsKey("transaction"));
    Assert.assertTrue("The key 'transaction' should be a Transaction", mav.getModel().get("transaction") instanceof Transaction);
  }

  @Test
  public void whenThereAreNoErrorsInThePayForm_modelToDisplayResultOfPaymentContainsAMessageReturnedByService(){

    //when
    ModelAndView mav = controller.submitForm(new Transaction(), bindingResult);

    //then
    verify(transactionService,times(1)).makePayment(any());
    Assert.assertTrue("Model should contain a key called 'resultMessage'", mav.getModel().containsKey("resultMessage"));
  }

  @Test
  public void whenThereAreErrorsInThePayForm_modelToDisplayResultOfPaymentContainsAMessageSetByController(){

    //given
    when(bindingResult.hasErrors()).thenReturn(true);

    //when
    ModelAndView mav = controller.submitForm(new Transaction(), bindingResult);

    //then
    verify(transactionService,times(0)).makePayment(any());
    Assert.assertTrue("Model should contain a key called 'resultMessage'", mav.getModel().containsKey("resultMessage"));
  }

}