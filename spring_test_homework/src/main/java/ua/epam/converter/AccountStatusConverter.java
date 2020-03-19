package ua.epam.converter;

import java.util.Objects;
import org.springframework.stereotype.Component;
import ua.epam.dto.AccountStatusDto;
import ua.epam.model.AccountStatus;

@Component
public class AccountStatusConverter implements Converter<AccountStatus, AccountStatusDto> {

  @Override
  public AccountStatusDto convert(AccountStatus accountStatus) {
    if (Objects.isNull(accountStatus)) {
      throw new IllegalArgumentException(
          "Can`t convert accountStatus to dto, account status is null");
    }
    AccountStatusDto dto = new AccountStatusDto();
    dto.setId(accountStatus.getId());
    dto.setStatus(accountStatus.getStatus());
    return dto;
  }

  @Override
  public AccountStatus unConvert(AccountStatusDto dto) {
    if (Objects.isNull(dto)) {
      throw new IllegalArgumentException(
          "Can`t convert accountStatusDto to accountStatus, dto is null");
    }
    AccountStatus accountStatus = new AccountStatus();
    accountStatus.setId(dto.getId());
    accountStatus.setStatus(dto.getStatus());
    return accountStatus;
  }
}
