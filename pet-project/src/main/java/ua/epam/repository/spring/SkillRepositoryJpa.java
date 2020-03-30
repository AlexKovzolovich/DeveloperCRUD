package ua.epam.repository.spring;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.epam.model.Skill;

@Repository
public interface SkillRepositoryJpa extends JpaRepository<Skill, UUID> {

}
