package ua.epam.repository.hibernate.impl;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.model.Skill;
import ua.epam.repository.hibernate.SkillDao;

@Component
public class SkillDaoImpl implements SkillDao {

  private SessionFactory sessionFactory;

  @Autowired
  public SkillDaoImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public void save(Skill skill) {
    Session session = sessionFactory.getCurrentSession();
    session.save(skill);
  }

  @Override
  public Skill get(UUID uuid) {
    Session session = sessionFactory.getCurrentSession();
    Skill skill = session.get(Skill.class, uuid);
    return Objects.requireNonNull(skill, "Skill not found by id " + uuid);
  }

  @Override
  public List<Skill> getAll() {
    Session session = sessionFactory.getCurrentSession();
    return session.createQuery("SELECT s FROM Skill s", Skill.class).getResultList();
  }

  @Override
  public void delete(UUID id) {
    Skill toDelete = get(id);
    Session session = sessionFactory.getCurrentSession();
    session.delete(toDelete);
  }
}
