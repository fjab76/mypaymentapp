package fjab.domain;

import fjab.dao.AccountDao;
import fjab.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class StringToAccountConverter implements Converter<String,Account> {

  @Autowired
  private AccountDao accountDao;

  @Override
  public Account convert(String source) {
    return accountDao.findByName(source);
  }
}
