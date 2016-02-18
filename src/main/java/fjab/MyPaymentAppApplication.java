package fjab;

import fjab.domain.StringToAccountConverter;
import fjab.domain.Account;
import fjab.domain.Transaction;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class MyPaymentAppApplication{

  //In-memory database. Not thread safe. Just for demo purposes
  public static List<Account> accountDatabase = new ArrayList<>();
  public static Map<Account,List<Transaction>> transactionDatabase = new HashMap<>();

  @Bean
  public InitializingBean initDatabase(){

    return () -> {
      accountDatabase.add(new Account("Peter", "peter@gmail.com"));
      accountDatabase.add(new Account("John", "john@gmail.com"));
      accountDatabase.add(new Account("Laura", "laura@gmail.com"));
      accountDatabase.add(new Account("Chris", "chris@gmail.com"));
      accountDatabase.add(new Account("Jane", "jane@gmail.com"));

      accountDatabase.stream().forEach(account -> transactionDatabase.put(account,new ArrayList<>()));
    };
  }

  @Bean
  public StringToAccountConverter stringToAccountConverter(){
    return new StringToAccountConverter();
  }

	public static void main(String[] args) {
		SpringApplication.run(MyPaymentAppApplication.class, args);
	}
}
