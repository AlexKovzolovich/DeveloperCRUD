package ua.epam.repository.hibernate;

import java.util.UUID;
import ua.epam.model.Account;
import ua.epam.repository.Repository;

public interface AccountDao extends Repository<Account, UUID> {

}
