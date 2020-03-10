package ua.epam.repository.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.epam.model.AccountStatus;

@Repository
public interface AccountStatusRepositoryJpa extends JpaRepository<AccountStatus, Long> {
}
