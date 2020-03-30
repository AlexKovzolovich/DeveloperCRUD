package ua.epam.repository.hibernate;

import java.util.UUID;
import ua.epam.model.Developer;
import ua.epam.repository.EntityRepository;

public interface DeveloperDao extends EntityRepository<Developer, UUID> {

}
