package ua.epam.repository.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.epam.model.Account;

@Repository
public interface AccountRepositoryJpa extends JpaRepository<Account, Long> {

}
