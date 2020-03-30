package ua.epam.repository.hibernate.impl;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.epam.model.Developer;
import ua.epam.model.Task;
import ua.epam.repository.hibernate.DeveloperDao;

@Repository
@Transactional
public class DeveloperDaoImpl implements DeveloperDao {

  private SessionFactory sessionFactory;

  @Autowired
  public DeveloperDaoImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public void save(Developer developer) {
    Session session = sessionFactory.getCurrentSession();
    session.save(developer);
  }

  @Override
  public Developer get(UUID uuid) {
    Session session = sessionFactory.getCurrentSession();
    Developer developer = session.get(Developer.class, uuid);
    return Objects.requireNonNull(developer, "Developer not found by id " + uuid);
  }

  @Override
  public List<Developer> getAll() {
    Session session = sessionFactory.getCurrentSession();
    return session.createQuery("SELECT d FROM Developer d", Developer.class).getResultList();
  }

  @Override
  public void delete(UUID uuid) {
    Developer developer = get(uuid);
    Session session = sessionFactory.getCurrentSession();
    session.delete(developer);
  }

  @Override
  public List<Task> getDeveloperTasksByDeveloperId(UUID id) {
    Session session = sessionFactory.getCurrentSession();
    Query<Task> query = session
        .createQuery("SELECT t FROM Task t WHERE t.appointedDeveloper.id = :id", Task.class);
    query.setParameter("id", id);

    return query.list();
  }
}
