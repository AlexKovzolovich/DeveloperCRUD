package ua.epam.repository.hibernate;

import java.util.UUID;
import ua.epam.model.Task;
import ua.epam.repository.EntityRepository;

public interface TaskDao extends EntityRepository<Task, UUID> {

}
