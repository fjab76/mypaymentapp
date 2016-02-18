package fjab.service;

import fjab.dao.TransactionDao;
import fjab.domain.Account;
import fjab.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionService {

  private TransactionDao transactionDao;

  public TransactionService(){}

  @Autowired
  public TransactionService(TransactionDao transactionDao){
    this.transactionDao = transactionDao;
  }

  /**
   * In a real case, it would make more sense to return a code and let the client of the service create the most
   * suitable message for the user (and use internationalizaton if it was required)
   * @param transaction
   * @return
   */
  public String makePayment(Transaction transaction) {

    if(transaction.getFrom().equals(transaction.getTo())){
      return "Payment could not be made as the origin and destination accounts are the same";
    }

    if(transaction.getAmount().compareTo(BigDecimal.ZERO)<0){
      return "Payment could not be made as the amount specified to transfer is negative";
    }

    if(!transaction.getFrom().decreaseBalance(transaction.getAmount())){
      return "Payment could not be made as there are not enough funds in the origin account";
    }

    transaction.getTo().increaseBalance(transaction.getAmount());

    transactionDao.save(transaction);

    return "Payment was completed successfully";
  }
}
