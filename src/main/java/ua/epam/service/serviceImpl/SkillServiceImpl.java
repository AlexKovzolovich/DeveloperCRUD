package ua.epam.service.serviceImpl;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.annotation.Timed;
import ua.epam.model.Skill;
import ua.epam.repository.spring.SkillRepositoryJpa;
import ua.epam.service.SkillService;

import java.util.List;

@Log4j
@Service
@Timed
public class SkillServiceImpl implements SkillService {
    private SkillRepositoryJpa skillRepository;

    @Autowired
    public SkillServiceImpl(SkillRepositoryJpa skillRepository) {
        this.skillRepository = skillRepository;
    }

    public Skill getById(Long id) {
        return skillRepository.getOne(id);
    }

    public List<Skill> getAll() {
        return skillRepository.findAll();
    }

    public void save(Skill skill) {
        skillRepository.save(skill);
    }

    public void delete(Skill skill) {
        skillRepository.delete(skill);
    }

    public void update(Skill skill) {
        skillRepository.save(skill);
    }
}
