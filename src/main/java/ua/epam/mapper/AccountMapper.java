package ua.epam.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.exceptions.PersistException;
import ua.epam.exceptions.WrongArgumentPersistentException;
import ua.epam.model.Account;
import ua.epam.model.AccountStatus;
import ua.epam.repository.AccountStatusRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AccountMapper implements Mapper<Account, ResultSet, PreparedStatement> {
    private AccountStatusRepository accountStatusRepository;

    @Autowired
    public AccountMapper(AccountStatusRepository accountStatusRepository) {
        this.accountStatusRepository = accountStatusRepository;
    }

    @Override
    public List<Account> map(ResultSet resultSet) throws PersistException {
        if (resultSet == null) {
            throw new WrongArgumentPersistentException(" resultSet must not be null");
        }

        List<Account> result = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String data = resultSet.getString("data");
                Long statusId = resultSet.getLong("status");
                AccountStatus accountStatus = accountStatusRepository.getById(statusId);
                result.add(new Account(id, data, accountStatus));
            }
        } catch (SQLException e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    public void map(Account account, PreparedStatement preparedStatement) throws PersistException {
        if (account == null || preparedStatement == null) {
            throw new WrongArgumentPersistentException(" Wrong argument: preparedStatement and object must not be null");
        }

        Long id = account.getId();
        String data = account.getData();
        long statusId = account.getStatus().getId();

        try {
            preparedStatement.setString(1, data);
            preparedStatement.setLong(2, statusId);
            if (id != null) {
                preparedStatement.setLong(3, id);
            }
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }
}
