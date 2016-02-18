package fjab.service;

import fjab.dao.AccountDao;
import fjab.dao.TransactionDao;
import fjab.domain.Account;
import fjab.domain.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

  private AccountService accountService;

  @Mock
  private AccountDao accountDao;
  @Mock
  private TransactionDao transactionDao;

  @Before
  public void setUp() throws Exception {

    accountService = new AccountService(accountDao,transactionDao);
  }


  @Test
  public void allAccountsAreRetrieved() throws Exception {

    //given
    List<Account> expectedList = Arrays.asList(new Account("name1", "email1"), new Account("name2", "email2"));
    when(accountDao.findAll()).thenReturn(expectedList);

    //when
    List<Account> actualList = accountService.accountList();

    //then
    assertEquals(expectedList,actualList);

  }

  @Test
  public void allTransactionsOfAGivenAccountAreRetrieved() throws Exception {

    //given
    List<Transaction> expectedList = Arrays.asList(new Transaction(new Account("name1", "email1"),new Account("name2", "email2"), BigDecimal.ONE),
                                                   new Transaction(new Account("name1", "email1"),new Account("name3", "email3"), BigDecimal.TEN));

    when(transactionDao.findByAccount(any())).thenReturn(expectedList);

    //when
    List<Transaction> actualList = accountService.accountTransactions(new Account("name1", "email1"));

    //then
    assertEquals(expectedList,actualList);

  }
}