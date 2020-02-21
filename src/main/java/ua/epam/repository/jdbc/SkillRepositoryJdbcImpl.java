package ua.epam.repository.jdbc;

import org.springframework.stereotype.Repository;
import ua.epam.exceptions.PersistException;
import ua.epam.mapper.Mapper;
import ua.epam.model.Skill;
import ua.epam.repository.SkillRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class SkillRepositoryJdbcImpl extends JdbcAbstractRepository<Skill> implements SkillRepository {

    public SkillRepositoryJdbcImpl(Mapper<Skill, ResultSet, PreparedStatement> mapper) throws PersistException {
        super(SkillRepositoryJdbcImpl.class, mapper);
    }
}
