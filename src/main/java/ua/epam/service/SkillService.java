package ua.epam.service;

import lombok.extern.log4j.Log4j;
import ua.epam.exceptions.PersistException;
import ua.epam.model.Skill;
import ua.epam.repository.SkillRepository;

import java.util.List;

@Log4j
public class SkillService {
    private SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public Skill getById(Long id) {
        try {
            return skillRepository.getById(id);
        } catch (PersistException e) {
            log.error("Receiving skill with id=" + id, e);
        }
        return null;
    }

    public List<Skill> getAll() {
        try {
            return skillRepository.getAll();
        } catch (PersistException e) {
            log.error("Receiving all skills", e);
        }
        return null;
    }

    public void save(Skill skill) {
        try {
            skillRepository.save(skill);
        } catch (PersistException e) {
            log.error("Saving skill id=" + skill.getId(),e);
        }
    }

    public void delete(Skill skill) {
        try {
            skillRepository.delete(skill);
        } catch (PersistException e) {
            log.error("Deleting skill id=" + skill.getId(), e);
        }
    }

    public void update(Skill skill) {
        try {
            skillRepository.update(skill);
        } catch (PersistException e) {
            log.error("Updating skill id=" + skill.getId(), e);
        }
    }
}
