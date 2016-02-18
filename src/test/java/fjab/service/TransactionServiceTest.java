package fjab.service;

import fjab.dao.TransactionDao;
import fjab.domain.Account;
import fjab.domain.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

  private TransactionService transactionService;
  @Mock
  private TransactionDao transactionDao;

  @Before
  public void setUp() throws Exception {

    transactionService = new TransactionService(transactionDao);
  }

  @Test
  public void whenTheTransactionIsSuccessful_moneyMovesBetweenAccounts() throws Exception {

    //given
    Transaction transaction = new Transaction(new Account("name1","email1"),new Account("name2","email2"), BigDecimal.TEN);

    //when
    String result = transactionService.makePayment(transaction);

    //then
    verify(transactionDao,times(1)).save(transaction);
    assertEquals("Payment was completed successfully", result);
    assertTrue(transaction.getFrom().getBalance().compareTo(BigDecimal.valueOf(190)) == 0);
    assertTrue(transaction.getTo().getBalance().compareTo(BigDecimal.valueOf(210))==0);

  }

  @Test
  public void whenThereIsNotEnoughMoneyInOriginAccount_transferDoesNotHappen() throws Exception {

    //given
    Transaction transaction = new Transaction(new Account("name1","email1"),new Account("name2","email2"), BigDecimal.valueOf(201));

    //when
    String result = transactionService.makePayment(transaction);

    //then
    verify(transactionDao,times(0)).save(transaction);
    assertEquals("Payment could not be made as there are not enough funds in the origin account", result);
    assertTrue(transaction.getFrom().getBalance().compareTo(BigDecimal.valueOf(200)) == 0);
    assertTrue(transaction.getTo().getBalance().compareTo(BigDecimal.valueOf(200))==0);

  }

  /**
   * Interestingly enough, this is the first test that makes us define the concept of equality for Account objects.
   * Up until now, it had not been necessary (previous tests passed because the objects returned by the mocks were
   * exactly the same references as the ones used as expected values in the assertions)
   * For our purposes, we will consider two Accounts as equal if they have the same name
   *
   * @throws Exception
   */
  @Test
  public void whenOriginAndDestinationAccountsAreTheSame_transferDoesNotHappen() throws Exception {

    //given
    Transaction transaction = new Transaction(new Account("name1","email1"),new Account("name1","email2"), BigDecimal.TEN);

    //when
    String result = transactionService.makePayment(transaction);

    //then
    verify(transactionDao,times(0)).save(transaction);
    assertEquals("Payment could not be made as the origin and destination accounts are the same", result);
    assertTrue(transaction.getFrom().getBalance().compareTo(BigDecimal.valueOf(200)) == 0);
    assertTrue(transaction.getTo().getBalance().compareTo(BigDecimal.valueOf(200))==0);

  }

  @Test
  public void whenTheSpecifiedAmountToTransferIsNegative_transferDoesNotHappen() throws Exception {

    //given
    Transaction transaction = new Transaction(new Account("name1","email1"),new Account("name2","email2"), BigDecimal.valueOf(-1));

    //when
    String result = transactionService.makePayment(transaction);

    //then
    verify(transactionDao,times(0)).save(transaction);
    assertEquals("Payment could not be made as the amount specified to transfer is negative", result);
    assertTrue(transaction.getFrom().getBalance().compareTo(BigDecimal.valueOf(200)) == 0);
    assertTrue(transaction.getTo().getBalance().compareTo(BigDecimal.valueOf(200))==0);

  }
}