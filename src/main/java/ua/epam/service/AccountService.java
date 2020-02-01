package ua.epam.service;

import lombok.extern.log4j.Log4j;
import ua.epam.exceptions.PersistException;
import ua.epam.model.Account;
import ua.epam.repository.AccountRepository;

import java.util.List;

@Log4j
public class AccountService {
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getById(Long id) {
        try {
            return accountRepository.getById(id);
        } catch (PersistException e) {
            log.error("Receiving account with id=" + id, e);
        }
        return null;
    }

    public List<Account> getAll() {
        try {
            return accountRepository.getAll();
        } catch (PersistException e) {
            log.error("Receiving all accounts", e);
        }
        return null;
    }

    public void save(Account account) {
        try {
            accountRepository.save(account);
        } catch (PersistException e) {
            log.error("Saving account id=" + account.getId(), e);
        }
    }

    public void delete(Account account) {
        try {
            accountRepository.delete(account);
        } catch (PersistException e) {
            log.error("Deleting account id=" + account.getId(), e);
        }
    }

    public void update(Account account) {
        try {
            accountRepository.update(account);
        } catch (PersistException e) {
            log.error("Updating account id=" + account.getId(), e);
        }
    }
}
