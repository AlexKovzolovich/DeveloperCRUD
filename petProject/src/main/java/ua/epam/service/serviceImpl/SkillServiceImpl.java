package ua.epam.service.serviceImpl;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.epam.annotation.Timed;
import ua.epam.exceptions.PersistException;
import ua.epam.model.Skill;
import ua.epam.repository.SkillRepository;
import ua.epam.service.SkillService;

import java.util.List;

@Log4j
@Service
@Timed
public class SkillServiceImpl implements SkillService {
    private SkillRepository skillRepository;

    @Autowired
    public SkillServiceImpl(@Qualifier("skillRepositoryJdbc") SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public Skill getById(Long id) {
        return skillRepository.getById(id);
    }

    public List<Skill> getAll() {
        return skillRepository.getAll();
    }

    public void save(Skill skill) {
        skillRepository.save(skill);
    }

    public void delete(Skill skill) {
        skillRepository.delete(skill);
    }

    public void update(Skill skill) {
        skillRepository.update(skill);
    }
}
