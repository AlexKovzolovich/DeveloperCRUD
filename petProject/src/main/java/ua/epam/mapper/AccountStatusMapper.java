package ua.epam.mapper;

import org.springframework.stereotype.Component;
import ua.epam.exceptions.PersistException;
import ua.epam.exceptions.WrongArgumentPersistentException;
import ua.epam.model.AccountStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AccountStatusMapper implements Mapper<AccountStatus, ResultSet, PreparedStatement> {
    @Override
    public List<AccountStatus> map(ResultSet resultSet) throws PersistException {
        if (resultSet == null) {
            throw new WrongArgumentPersistentException(" resultSet must not be null");
        }

        List<AccountStatus> accountStatuses = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String status = resultSet.getString("status");
                AccountStatus accountStatus = new AccountStatus(id, status);
                accountStatuses.add(accountStatus);
            }
        } catch (SQLException e) {
            throw new PersistException(e);
        }
        return accountStatuses;
    }

    @Override
    public void map(AccountStatus accountStatus, PreparedStatement preparedStatement) throws PersistException {
        if (accountStatus == null || preparedStatement == null) {
            throw new WrongArgumentPersistentException(" Wrong arguments: must not be null");
        }

        Long id = accountStatus.getId();
        String status = accountStatus.getStatus();

        try {
            preparedStatement.setString(1, status);
            if (id != null) {
                preparedStatement.setLong(2, id);
            }
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }
}
