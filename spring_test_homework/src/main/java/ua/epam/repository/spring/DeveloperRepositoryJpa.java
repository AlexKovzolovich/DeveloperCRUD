package ua.epam.repository.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.epam.model.Developer;

@Repository
public interface DeveloperRepositoryJpa extends JpaRepository<Developer, Long> {

}
