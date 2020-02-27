package ua.epam.service.serviceImpl;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.epam.annotation.Timed;
import ua.epam.exceptions.PersistException;
import ua.epam.model.Account;
import ua.epam.repository.AccountRepository;
import ua.epam.service.AccountService;

import java.util.List;

@Log4j
@Service
@Timed
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(@Qualifier("accountRepositoryJdbc") AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getById(Long id) {
        return accountRepository.getById(id);
    }

    public List<Account> getAll() {
        return accountRepository.getAll();
    }

    public void save(Account account) {
        accountRepository.save(account);
    }

    public void delete(Account account) {
        accountRepository.delete(account);
    }

    public void update(Account account) {
        accountRepository.update(account);
    }
}
