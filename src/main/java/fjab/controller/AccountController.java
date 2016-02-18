package fjab.controller;

import fjab.domain.Account;
import fjab.domain.Transaction;
import fjab.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AccountController {

  private AccountService accountService;

  @Autowired
  public AccountController(AccountService accountService){
    this.accountService = accountService;
  }

  @RequestMapping(value = "/account/list", method = RequestMethod.GET)
  public ModelAndView accountList() {

    return initForm();
  }

  @RequestMapping(value = "/account/transactions", method = RequestMethod.POST)
  public ModelAndView accountTransactions(@ModelAttribute("account") Account account) {

    List<Transaction> transactions = accountService.accountTransactions(account);
    ModelAndView mav = initForm();
    mav.addObject("transactions", transactions);

    return mav;
  }

  private ModelAndView initForm(){

    ModelAndView mav = new ModelAndView();
    mav.setViewName("transactions");
    mav.addObject("accountForm",new Account());
    mav.addObject("accounts", accountService.accountList());
    return mav;
  }
}
