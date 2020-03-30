package ua.epam.service.serviceImpl;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ua.epam.annotation.Timed;
import ua.epam.converter.AccountConverter;
import ua.epam.dto.AccountDto;
import ua.epam.model.Account;
import ua.epam.repository.hibernate.AccountDao;
import ua.epam.service.AccountService;

@Service
@Timed
public class AccountServiceImpl implements AccountService {

  private AccountConverter converter;
  private AccountDao accountDao;

  @Autowired
  public AccountServiceImpl(AccountConverter accountConverter,
      AccountDao accountDao) {
    this.converter = accountConverter;
    this.accountDao = accountDao;
  }


  @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
  @Override
  public AccountDto create(AccountDto accountDto) {
    Account account = converter.unConvert(accountDto);
    accountDao.save(account);

    return converter.convert(account);
  }

  @Override
  public AccountDto getById(UUID id) {
    Account account = accountDao.get(id);
    return converter.convert(account);
  }

  @Override
  public List<AccountDto> getAll() {
    List<Account> accounts = accountDao.getAll();
    return converter.convertAll(accounts);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @Override
  public void delete(AccountDto accountDto) {
    accountDao.delete(accountDto.getId());
  }

  @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
  @Override
  public AccountDto update(AccountDto accountDto) {
    Account persisted = accountDao.get(accountDto.getId());
    Account updated = converter.unConvert(accountDto);

    persisted.setStatus(updated.getStatus());
    persisted.setData(updated.getData());

    return converter.convert(persisted);
  }
}
