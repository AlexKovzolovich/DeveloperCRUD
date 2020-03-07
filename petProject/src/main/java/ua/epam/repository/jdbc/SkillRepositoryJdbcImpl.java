package ua.epam.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ua.epam.exceptions.PersistException;
import ua.epam.mapper.Mapper;
import ua.epam.model.Skill;
import ua.epam.repository.SkillRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
@Qualifier("skillRepositoryJdbc")
public class SkillRepositoryJdbcImpl extends JdbcAbstractRepository<Skill> implements SkillRepository {

    @Autowired
    public SkillRepositoryJdbcImpl(Mapper<Skill, ResultSet, PreparedStatement> mapper) throws PersistException {
        super(SkillRepositoryJdbcImpl.class, mapper);
    }
}
