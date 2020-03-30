package ua.epam.repository.hibernate;

import java.util.List;
import java.util.UUID;
import ua.epam.model.Developer;
import ua.epam.model.Task;
import ua.epam.repository.Repository;

public interface DeveloperDao extends Repository<Developer, UUID> {

  List<Task> getDeveloperTasksByDeveloperId(UUID id);
}
