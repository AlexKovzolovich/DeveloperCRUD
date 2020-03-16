package ua.epam.service.serviceImpl;

import java.util.List;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.annotation.Timed;
import ua.epam.model.Account;
import ua.epam.repository.spring.AccountRepositoryJpa;
import ua.epam.service.AccountService;

@Log4j
@Service
@Timed
public class AccountServiceImpl implements AccountService {

  private AccountRepositoryJpa accountRepository;

  @Autowired
  public AccountServiceImpl(AccountRepositoryJpa accountRepository) {
    this.accountRepository = accountRepository;
  }

  public Account getById(Long id) {
    return accountRepository.getOne(id);
  }

  public List<Account> getAll() {
    return accountRepository.findAll();
  }

  public void save(Account account) {
    accountRepository.save(account);
  }

  public void delete(Account account) {
    accountRepository.delete(account);
  }

  public void update(Account account) {
    accountRepository.save(account);
  }
}
