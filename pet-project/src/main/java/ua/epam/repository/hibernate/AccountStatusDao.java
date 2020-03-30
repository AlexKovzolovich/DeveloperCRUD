package ua.epam.repository.hibernate;

import java.util.UUID;
import ua.epam.model.AccountStatus;
import ua.epam.repository.EntityRepository;

public interface AccountStatusDao extends EntityRepository<AccountStatus, UUID> {
}
