package ua.epam.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.epam.model.Account;
import ua.epam.service.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {

  private AccountService accountService;

  @Autowired
  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @GetMapping(params = "id")
  public @ResponseBody
  Account getAccount(Long id) {
    return accountService.getById(id);
  }

  @GetMapping
  public @ResponseBody
  List<Account> getAccounts() {
    return accountService.getAll();
  }

  @PostMapping
  public ResponseEntity postAccount(@RequestBody Account account) {
    accountService.save(account);
    return new ResponseEntity(HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity putAccount(@RequestBody Account account) {
    accountService.update(account);
    return new ResponseEntity(HttpStatus.OK);
  }

  @DeleteMapping
  public ResponseEntity deleteAccount(@RequestBody Account account) {
    accountService.delete(account);
    return new ResponseEntity(HttpStatus.OK);
  }
}
