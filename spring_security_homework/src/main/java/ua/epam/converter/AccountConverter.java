package ua.epam.converter;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.dto.AccountDto;
import ua.epam.model.Account;

@Component
public class AccountConverter implements Converter<Account, AccountDto> {

  private AccountStatusConverter accountStatusConverter;

  @Autowired
  public AccountConverter(AccountStatusConverter accountStatusConverter) {
    this.accountStatusConverter = accountStatusConverter;
  }

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
    dto.setAccountStatusDto(accountStatusConverter.convert(account.getStatus()));
    return null;
  }

  @Override
  public Account unConvert(AccountDto dto) {
    if (Objects.isNull(dto)) {
      throw new IllegalArgumentException("Can`t convert dto to account, dto is null");
    }

    if (Objects.isNull(dto.getAccountStatusDto())) {
      throw new IllegalArgumentException("Can`t convert dto to account, accountStatusDto is null");
    }

    Account account = new Account();
    account.setId(dto.getId());
    account.setData(dto.getData());
    account.setStatus(accountStatusConverter.unConvert(dto.getAccountStatusDto()));
    return account;
  }
}
