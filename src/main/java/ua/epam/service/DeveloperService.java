package ua.epam.service;

import lombok.extern.log4j.Log4j;
import ua.epam.exceptions.PersistException;
import ua.epam.model.Developer;
import ua.epam.repository.DeveloperRepository;

import java.util.List;

@Log4j
public class DeveloperService {
    private DeveloperRepository developerRepository;

    public DeveloperService(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    public Developer getById(Long id) {
        try {
            return developerRepository.getById(id);
        } catch (PersistException e) {
            log.error("Receiving developer id=" + id, e);
        }
        return null;
    }

    public List<Developer> getAll() {
        try {
            return developerRepository.getAll();
        } catch (PersistException e) {
            log.error("Receiving all developers", e);
        }
        return null;
    }

    public void save(Developer developer) {
        try {
            developerRepository.save(developer);
        } catch (PersistException e) {
            log.error("Saving developer id=" + developer.getId(), e);
        }
    }

    public void delete(Developer developer) {
        try {
            developerRepository.delete(developer);
        } catch (PersistException e) {
            log.error("Deleting developer id=" + developer.getId(), e);
        }
    }

    public void update(Developer developer) {
        try {
            developerRepository.update(developer);
        } catch (PersistException e) {
            log.error("Updating developer id=" + developer.getId(), e);
        }
    }
}
