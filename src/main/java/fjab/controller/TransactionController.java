package fjab.controller;

import fjab.domain.Transaction;
import fjab.service.AccountService;
import fjab.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TransactionController {

  private AccountService accountService;
  private TransactionService transactionService;

  @Autowired
  public TransactionController(AccountService accountService, TransactionService transactionService){
    this.accountService = accountService;
    this.transactionService = transactionService;
  }

  @RequestMapping(value = "/pay", method = RequestMethod.GET)
  public ModelAndView showForm() {

    return initForm();
  }

  @RequestMapping(value = "/pay", method = RequestMethod.POST)
  public ModelAndView submitForm(@ModelAttribute("transaction") Transaction payForm, BindingResult result) {

    ModelAndView mav = initForm();
    mav.addObject("resultMessage","Payment could not be made");

    if(!result.hasErrors()){
      String paymentStatus = transactionService.makePayment(payForm);
      mav.addObject("resultMessage",paymentStatus);
    }

    return mav;
  }

  private ModelAndView initForm(){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("pay");
    mav.addObject("transaction", new Transaction());
    mav.addObject("accounts", accountService.accountList());
    return mav;
  }

}
