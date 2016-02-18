package fjab.service;

import fjab.MyPaymentAppApplication;
import fjab.dao.AccountDao;
import fjab.dao.TransactionDao;
import fjab.domain.Account;
import fjab.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class AccountService {

  private AccountDao accountDao;
  private TransactionDao transactionDao;

  @Autowired
  public AccountService(AccountDao accountDao, TransactionDao transactionDao){
    this.accountDao = accountDao;
    this.transactionDao = transactionDao;
  }

  public List<Account> accountList() {

    return accountDao.findAll();

  }

  public List<Transaction> accountTransactions(Account account) {

    return transactionDao.findByAccount(account);
  }
}
