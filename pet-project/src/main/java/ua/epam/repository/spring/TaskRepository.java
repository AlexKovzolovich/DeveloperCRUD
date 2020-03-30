package ua.epam.repository.spring;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.epam.model.Task;

public interface TaskRepository extends JpaRepository<Task, UUID> {

}
