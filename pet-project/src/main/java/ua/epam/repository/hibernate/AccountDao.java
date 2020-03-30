package ua.epam.repository.hibernate;

import java.util.UUID;
import ua.epam.model.Account;
import ua.epam.repository.EntityRepository;

public interface AccountDao extends EntityRepository<Account, UUID> {

}
