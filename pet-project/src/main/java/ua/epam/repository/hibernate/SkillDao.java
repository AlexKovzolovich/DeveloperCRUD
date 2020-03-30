package ua.epam.repository.hibernate;

import java.util.UUID;
import ua.epam.model.Skill;
import ua.epam.repository.EntityRepository;

public interface SkillDao extends EntityRepository<Skill, UUID> {
}
