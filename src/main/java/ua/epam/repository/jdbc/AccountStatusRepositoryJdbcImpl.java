package ua.epam.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ua.epam.exceptions.PersistException;
import ua.epam.mapper.Mapper;
import ua.epam.model.AccountStatus;
import ua.epam.repository.AccountStatusRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
@Qualifier("accountStatusRepositoryJdbc")
public class AccountStatusRepositoryJdbcImpl extends JdbcAbstractRepository<AccountStatus> implements AccountStatusRepository {

    @Autowired
    public AccountStatusRepositoryJdbcImpl(Mapper<AccountStatus, ResultSet, PreparedStatement> mapper) throws PersistException {
        super(AccountStatusRepositoryJdbcImpl.class, mapper);
    }
}
