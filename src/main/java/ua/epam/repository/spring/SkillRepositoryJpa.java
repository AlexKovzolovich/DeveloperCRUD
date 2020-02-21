package ua.epam.repository.spring;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.epam.model.Skill;

@Repository
@Qualifier("skillRepositoryJpa")
public interface SkillRepositoryJpa extends JpaRepository<Skill, Long> {
}
