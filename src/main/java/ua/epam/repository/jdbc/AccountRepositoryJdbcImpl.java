package ua.epam.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.epam.exceptions.PersistException;
import ua.epam.mapper.Mapper;
import ua.epam.model.Account;
import ua.epam.repository.AccountRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class AccountRepositoryJdbcImpl extends JdbcAbstractRepository<Account> implements AccountRepository {

    @Autowired
    public AccountRepositoryJdbcImpl(Mapper<Account, ResultSet, PreparedStatement> mapper) throws PersistException {
        super(AccountRepositoryJdbcImpl.class, mapper);
    }
}
