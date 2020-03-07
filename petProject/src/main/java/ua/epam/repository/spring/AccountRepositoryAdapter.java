package ua.epam.repository.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ua.epam.exceptions.PersistException;
import ua.epam.model.Account;
import ua.epam.repository.AccountRepository;

import java.util.List;

public class AccountRepositoryAdapter implements AccountRepository {
    private AccountRepositoryJpa accountRepositoryJpa;

    @Autowired
    public AccountRepositoryAdapter(@Qualifier("accountRepositoryJpa") AccountRepositoryJpa accountRepositoryJpa) {
        this.accountRepositoryJpa = accountRepositoryJpa;
    }

    @Override
    public Account getById(Long id) throws PersistException {
        return accountRepositoryJpa.getOne(id);
    }

    @Override
    public List<Account> getAll() throws PersistException {
        return accountRepositoryJpa.findAll();
    }

    @Override
    public void save(Account account) throws PersistException {
        accountRepositoryJpa.save(account);
    }

    @Override
    public void delete(Account account) throws PersistException {
        accountRepositoryJpa.delete(account);
    }

    @Override
    public void update(Account account) throws PersistException {
        accountRepositoryJpa.save(account);
    }
}
