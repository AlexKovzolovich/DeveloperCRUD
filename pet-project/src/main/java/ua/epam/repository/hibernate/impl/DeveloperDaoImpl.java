package ua.epam.repository.hibernate.impl;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.model.Developer;
import ua.epam.repository.hibernate.DeveloperDao;

@Component
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
}
