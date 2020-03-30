package ua.epam.repository.hibernate;

import java.util.UUID;
import ua.epam.model.Task;
import ua.epam.repository.Repository;

public interface TaskDao extends Repository<Task, UUID> {

}
