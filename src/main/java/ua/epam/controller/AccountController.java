package ua.epam.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.epam.model.Account;
import ua.epam.service.AccountService;
import ua.epam.service.serviceImpl.AccountServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/api/v1/account")
public class AccountController {
    private AccountService accountService;
    private final Gson gson = new Gson();
    private final String redirectToGet = "redirect:/api/v1/account";

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    @ResponseBody
    public String getAccounts(@RequestParam(value = "id", required = false) Long id) {
        StringBuilder stringBuilder = new StringBuilder();
        if (id == null) {
            List<Account> accounts = accountService.getAll();
            stringBuilder.append(gson.toJson(accounts));
        }
        else {
            Account account = accountService.getById(id);
            stringBuilder.append(gson.toJson(account));
        }

        return stringBuilder.toString();
    }

    @PostMapping
    public String postAccount(HttpEntity<String> httpEntity) {
        String json = httpEntity.getBody();
        Account account = gson.fromJson(json, Account.class);
        accountService.save(account);
        return redirectToGet;
    }

    @PutMapping
    public String putAccount(HttpEntity<String> httpEntity) {
        String json = httpEntity.getBody();
        Account account = gson.fromJson(json, Account.class);
        accountService.update(account);
        return redirectToGet;
    }

    @DeleteMapping
    public String deleteAccount(HttpEntity<String> httpEntity) {
        String json = httpEntity.getBody();
        Account account = gson.fromJson(json, Account.class);
        accountService.delete(account);
        return redirectToGet;
    }
}
