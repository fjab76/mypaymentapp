package fjab.dao;

import fjab.domain.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static fjab.MyPaymentAppApplication.accountDatabase;


@Component
public class AccountDao {

  public List<Account> findAll() {

    return accountDatabase;
  }

  public Account findByName(String name){
    for(int j=0;j<accountDatabase.size();j++){
      if(name.equals(accountDatabase.get(j).getName())){
        return accountDatabase.get(j);
      }
    }
    return null;
  }
}
