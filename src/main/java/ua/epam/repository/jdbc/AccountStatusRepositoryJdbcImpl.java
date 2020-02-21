package ua.epam.repository.jdbc;

import org.springframework.stereotype.Repository;
import ua.epam.exceptions.PersistException;
import ua.epam.mapper.Mapper;
import ua.epam.model.AccountStatus;
import ua.epam.repository.AccountStatusRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class AccountStatusRepositoryJdbcImpl extends JdbcAbstractRepository<AccountStatus> implements AccountStatusRepository {

    public AccountStatusRepositoryJdbcImpl(Mapper<AccountStatus, ResultSet, PreparedStatement> mapper) throws PersistException {
        super(AccountStatusRepositoryJdbcImpl.class, mapper);
    }
}
