package ua.epam.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ua.epam.dto.AccountDto;
import ua.epam.service.AccountService;

@Controller
@RequestMapping("/api/v1/accounts")
public class AccountController {

  private AccountService accountService;

  @Autowired
  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @GetMapping(params = "id")
  public @ResponseBody
  AccountDto getAccount(String id) {
    UUID uuid = UUID.fromString(id);
    return accountService.getById(uuid);
  }

  @GetMapping
  public @ResponseBody
  List<AccountDto> getAccounts() {
    return accountService.getAll();
  }

  @PostMapping
  public ResponseEntity<AccountDto> postAccount(@RequestBody AccountDto accountDto) {
    return new ResponseEntity<>(accountService.create(accountDto), HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity<AccountDto> putAccount(@RequestBody AccountDto accountDto) {
    return new ResponseEntity<>(accountService.update(accountDto), HttpStatus.OK);
  }

  @DeleteMapping
  @ResponseStatus(HttpStatus.OK)
  public void deleteAccount(@RequestBody AccountDto accountDto) {
    accountService.delete(accountDto);
  }
}
