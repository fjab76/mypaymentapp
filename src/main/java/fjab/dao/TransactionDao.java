package fjab.dao;

import fjab.domain.Account;
import fjab.domain.Transaction;
import org.springframework.stereotype.Component;
import static fjab.MyPaymentAppApplication.transactionDatabase;
import java.util.List;


@Component
public class TransactionDao {

  public List<Transaction> findByAccount(Account account) {

    return transactionDatabase.get(account);
  }

  public void save(Transaction transaction) {

    transactionDatabase.get(transaction.getFrom()).add(transaction);
    transactionDatabase.get(transaction.getTo()).add(transaction);
  }
}
