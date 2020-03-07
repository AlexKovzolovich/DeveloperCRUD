package ua.epam.mapper;

import org.springframework.stereotype.Component;
import ua.epam.exceptions.PersistException;
import ua.epam.exceptions.WrongArgumentPersistentException;
import ua.epam.model.Skill;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SkillMapper implements Mapper<Skill, ResultSet, PreparedStatement> {
    @Override
    public List<Skill> map(ResultSet resultSet) throws PersistException {
        if (resultSet == null) {
            throw new WrongArgumentPersistentException(" resultSet must not be null");
        }

        List<Skill> result = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                result.add(new Skill(id, name));
            }
        } catch (SQLException e) {
            throw new PersistException(e);
        }

        return result;
    }

    @Override
    public void map(Skill skill, PreparedStatement preparedStatement) throws PersistException {
        if (skill == null || preparedStatement == null) {
            throw new WrongArgumentPersistentException("Wrong argument: preparedStatement and object must not be null");
        }

        Long id = skill.getId();
        String name = skill.getName();

        try {
            preparedStatement.setString(1, name);
            if (id != null) {
                preparedStatement.setLong(2, id);
            }
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }
}
