package ua.epam.repository.hibernate.impl;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.model.Task;
import ua.epam.repository.hibernate.TaskDao;

@Component
public class TaskDaoImpl  implements TaskDao {

  private SessionFactory sessionFactory;

  @Autowired
  public TaskDaoImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public void save(Task task) {
    Session session = sessionFactory.getCurrentSession();
    session.save(task);
  }

  @Override
  public Task get(UUID uuid) {
    Session session = sessionFactory.getCurrentSession();
    Task task = session.get(Task.class, uuid);
    return Objects.requireNonNull(task, "Task not found by id " + uuid);
  }

  @Override
  public void delete(UUID uuid) {
    Task task = get(uuid);
    Session session = sessionFactory.getCurrentSession();
    session.delete(task);
  }

  @Override
  public List<Task> getAll() {
    Session session = sessionFactory.getCurrentSession();
    return session.createQuery("SELECT t FROM Task t", Task.class).getResultList();
  }
}
