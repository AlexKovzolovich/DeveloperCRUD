package ua.epam.repository.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ua.epam.exceptions.PersistException;
import ua.epam.model.Skill;
import ua.epam.repository.SkillRepository;

import java.util.List;

public class SkillRepositoryAdapter implements SkillRepository {
    private SkillRepositoryJpa skillRepositoryJpa;

    @Autowired
    public SkillRepositoryAdapter(@Qualifier("skillRepositoryJpa") SkillRepositoryJpa skillRepositoryJpa) {
        this.skillRepositoryJpa = skillRepositoryJpa;
    }



    @Override
    public Skill getById(Long id) throws PersistException {
        return skillRepositoryJpa.getOne(id);
    }

    @Override
    public List<Skill> getAll() throws PersistException {
        return skillRepositoryJpa.findAll();
    }

    @Override
    public void save(Skill skill) throws PersistException {
        skillRepositoryJpa.save(skill);
    }

    @Override
    public void delete(Skill skill) throws PersistException {
        skillRepositoryJpa.delete(skill);
    }

    @Override
    public void update(Skill skill) throws PersistException {
        skillRepositoryJpa.save(skill);
    }
}
