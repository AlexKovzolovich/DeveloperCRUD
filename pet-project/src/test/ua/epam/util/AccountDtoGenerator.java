package ua.epam.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.experimental.UtilityClass;
import ua.epam.converter.AccountConverter;
import ua.epam.converter.AccountStatusConverter;
import ua.epam.dto.AccountDto;
import ua.epam.model.Account;
import ua.epam.model.AccountStatus;

@UtilityClass
public class AccountDtoGenerator {

  private static final AccountConverter ACCOUNT_CONVERTER = new AccountConverter(
      new AccountStatusConverter());

  public static AccountDto createAccountDto(long id) {
    return ACCOUNT_CONVERTER.convert(createAccount(id));
  }

  public static List<AccountDto> generateAccountDtoList(int count) {
    if (count < 1) {
      throw new IllegalArgumentException("Count must be more then 0");
    }

    return ACCOUNT_CONVERTER.convertAll(generateAccounts(count));
  }

  private static Account createAccount(long id) {
    Account account = new Account();
    account.setId(id);
    account.setData("test account data");
    account.setStatus(new AccountStatus(1L, "active"));

    return account;
  }

  private static List<Account> generateAccounts(int count) {
    return IntStream.range(0, count)
        .mapToObj(AccountDtoGenerator::createAccount)
        .collect(Collectors.toList());
  }
}
