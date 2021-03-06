package ua.epam.service.serviceImpl;

import java.util.List;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ua.epam.annotation.Timed;
import ua.epam.converter.AccountConverter;
import ua.epam.dto.AccountDto;
import ua.epam.repository.spring.AccountRepositoryJpa;
import ua.epam.service.AccountService;

@Log4j
@Service
@Timed
public class AccountServiceImpl implements AccountService {

  private AccountRepositoryJpa accountRepository;
  private AccountConverter accountConverter;

  @Autowired
  public AccountServiceImpl(AccountRepositoryJpa accountRepository,
      AccountConverter accountConverter) {
    this.accountRepository = accountRepository;
    this.accountConverter = accountConverter;
  }

  @Override
  public AccountDto getById(Long id) {
    return accountConverter.convert(accountRepository.findById(id).orElseThrow(
        () -> new RuntimeException("Can`t find account by id = " + id)));
  }

  @Override
  public List<AccountDto> getAll() {
    return accountConverter.convertAll(accountRepository.findAll());
  }

  @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
  @Override
  public void save(AccountDto accountDto) {
    accountRepository.save(accountConverter.unConvert(accountDto));
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @Override
  public void delete(AccountDto accountDto) {
    accountRepository.delete(accountConverter.unConvert(accountDto));
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @Override
  public void update(AccountDto accountDto) {
    save(accountDto);
  }
}
