package ua.epam.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ua.epam.exceptions.PersistException;
import ua.epam.exceptions.WrongArgumentPersistentException;
import ua.epam.model.Account;
import ua.epam.model.Developer;
import ua.epam.repository.AccountRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class DeveloperMapper implements Mapper <Developer, ResultSet, PreparedStatement> {
    private AccountRepository accountRepository;

    @Autowired
    public DeveloperMapper(@Qualifier("accountRepositoryJdbc") AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Developer> map(ResultSet resultSet) throws PersistException {
        if (resultSet == null) {
            throw new WrongArgumentPersistentException(" resultSet must not be null");
        }

        List<Developer> result = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                Long accountId = resultSet.getLong("account");
                Account account = accountRepository.getById(accountId);
                result.add(new Developer(id, name, account, null));
            }
        } catch (SQLException e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    public void map(Developer developer, PreparedStatement preparedStatement) throws PersistException {
        if (developer == null || preparedStatement == null) {
            throw new WrongArgumentPersistentException("Wrong argument: preparedStatement and object must not be null");
        }
        Long developerId = developer.getId();
        String name = developer.getName();
        long accountStatus = developer.getAccount().getStatus().getId();

        try {
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, accountStatus);
            if (developerId != null) {
                preparedStatement.setLong(3, developerId);
            }
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }
}
