package ua.epam.repository.spring;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.epam.model.Developer;

@Repository
@Qualifier("developerRepositoryJpa")
public interface DeveloperRepositoryJpa extends JpaRepository<Developer, Long> {
}
