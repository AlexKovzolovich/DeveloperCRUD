package ua.epam.service.serviceImpl;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.annotation.Timed;
import ua.epam.model.Developer;
import ua.epam.repository.spring.DeveloperRepositoryJpa;
import ua.epam.service.DeveloperService;

import java.util.List;

@Log4j
@Service
@Timed
public class DeveloperServiceImpl implements DeveloperService {
    private DeveloperRepositoryJpa developerRepository;

    @Autowired
    public DeveloperServiceImpl(DeveloperRepositoryJpa developerRepository) {
        this.developerRepository = developerRepository;
    }

    public Developer getById(Long id) {
        return developerRepository.getOne(id);
    }

    public List<Developer> getAll() {
        return developerRepository.findAll();
    }

    public void save(Developer developer) {
        developerRepository.save(developer);
    }

    public void delete(Developer developer) {
        developerRepository.delete(developer);
    }

    public void update(Developer developer) {
        developerRepository.save(developer);
    }
}
