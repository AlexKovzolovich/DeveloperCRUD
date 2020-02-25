package ua.epam.repository.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ua.epam.exceptions.PersistException;
import ua.epam.model.Developer;
import ua.epam.repository.DeveloperRepository;

import java.util.List;


public class DeveloperRepositoryAdapter implements DeveloperRepository {
    private DeveloperRepositoryJpa developerRepositoryJpa;

    @Autowired
    public DeveloperRepositoryAdapter(@Qualifier("developerRepositoryJpa") DeveloperRepositoryJpa developerRepositoryJpa) {
        this.developerRepositoryJpa = developerRepositoryJpa;
    }

    @Override
    public Developer getById(Long id) throws PersistException {
        return developerRepositoryJpa.getOne(id);
    }

    @Override
    public List<Developer> getAll() throws PersistException {
        return developerRepositoryJpa.findAll();
    }

    @Override
    public void save(Developer developer) throws PersistException {
        developerRepositoryJpa.save(developer);
    }

    @Override
    public void delete(Developer developer) throws PersistException {
        developerRepositoryJpa.delete(developer);
    }

    @Override
    public void update(Developer developer) throws PersistException {
        developerRepositoryJpa.save(developer);
    }
}
