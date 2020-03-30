package ua.epam.converter;

import java.util.Objects;
import org.springframework.stereotype.Component;
import ua.epam.dto.AccountDto;
import ua.epam.model.Account;
import ua.epam.model.AccountStatus;

@Component
public class AccountConverter implements Converter<Account, AccountDto> {

  @Override
  public AccountDto convert(Account account) {
    if (Objects.isNull(account)) {
      throw new IllegalArgumentException("Can`t convert account to dto, account is null");
    }

    if (Objects.isNull(account.getStatus())) {
      throw new IllegalArgumentException("Can`t convert account to dto, accountStatus is null");
    }

    AccountDto dto = new AccountDto();
    dto.setId(account.getId());
    dto.setData(account.getData());
    dto.setStatus(account.getStatus().name());
    return dto;
  }

  @Override
  public Account unConvert(AccountDto dto) {
    if (Objects.isNull(dto)) {
      throw new IllegalArgumentException("Can`t convert dto to account, dto is null");
    }

    if (Objects.isNull(dto.getStatus())) {
      throw new IllegalArgumentException("Can`t convert dto to account, status is null");
    }

    Account account = new Account();
    account.setId(dto.getId());
    account.setData(dto.getData());
    account.setStatus(AccountStatus.valueOf(dto.getStatus()));
    return account;
  }
}
