package ua.epam.repository.hibernate.impl;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.model.AccountStatus;
import ua.epam.repository.hibernate.AccountStatusDao;

@Component
public class AccountStatusDaoImpl implements AccountStatusDao {

  private SessionFactory sessionFactory;

  @Autowired
  public AccountStatusDaoImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public void save(AccountStatus accountStatus) {
    Session session = sessionFactory.getCurrentSession();
    session.save(accountStatus);
  }

  @Override
  public AccountStatus get(UUID uuid) {
    Session session = sessionFactory.getCurrentSession();
    AccountStatus accountStatus = session.get(AccountStatus.class, uuid);
    return Objects.requireNonNull(accountStatus, "AccountStatus not found by id " + uuid);
  }

  @Override
  public List<AccountStatus> getAll() {
    Session session = sessionFactory.getCurrentSession();
    return session.createQuery("SELECT a FROM AccountStatus a", AccountStatus.class).getResultList();
  }

  @Override
  public void delete(UUID uuid) {
    AccountStatus toDelete = get(uuid);
    Session session = sessionFactory.getCurrentSession();
    session.delete(toDelete);
  }
}
