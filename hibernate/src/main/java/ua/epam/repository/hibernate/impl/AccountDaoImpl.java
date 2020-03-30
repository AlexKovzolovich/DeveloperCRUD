package ua.epam.repository.hibernate.impl;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.epam.model.Account;
import ua.epam.repository.hibernate.AccountDao;

@Repository
@Transactional
public class AccountDaoImpl implements AccountDao {

  private SessionFactory sessionFactory;

  @Autowired
  public AccountDaoImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public void save(Account account) {
    Session session = sessionFactory.getCurrentSession();
    session.save(account);
  }

  @Override
  public Account get(UUID id) {
    Session session = sessionFactory.getCurrentSession();
    Account account = session.get(Account.class, id);
    return Objects.requireNonNull(account, "Account not found by id " + id);
  }

  @Override
  public List<Account> getAll() {
    Session session = sessionFactory.getCurrentSession();
    return session.createQuery("SELECT a FROM Account a", Account.class).getResultList();
  }

  @Override
  public void delete(UUID id) {
    Account account = get(id);
    Session session = sessionFactory.getCurrentSession();
    session.delete(account);
  }
}
