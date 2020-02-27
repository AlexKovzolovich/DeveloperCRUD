package ua.epam.service.serviceImpl;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.epam.annotation.Timed;
import ua.epam.exceptions.PersistException;
import ua.epam.model.Developer;
import ua.epam.repository.DeveloperRepository;
import ua.epam.service.DeveloperService;

import java.util.List;

@Log4j
@Service
@Timed
public class DeveloperServiceImpl implements DeveloperService {
    private DeveloperRepository developerRepository;

    @Autowired
    public DeveloperServiceImpl(@Qualifier("developerRepositoryJdbc") DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    public Developer getById(Long id) {
        return developerRepository.getById(id);
    }

    public List<Developer> getAll() {
        return developerRepository.getAll();
    }

    public void save(Developer developer) {
        developerRepository.save(developer);
    }

    public void delete(Developer developer) {
        developerRepository.delete(developer);
    }

    public void update(Developer developer) {
        developerRepository.update(developer);
    }
}
