package ua.epam.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.experimental.UtilityClass;
import ua.epam.converter.AccountConverter;
import ua.epam.dto.AccountDto;
import ua.epam.model.Account;
import ua.epam.model.AccountStatus;

@UtilityClass
public class AccountDtoGenerator {

  private static final AccountConverter ACCOUNT_CONVERTER = new AccountConverter();

  public static AccountDto createAccountDto() {
    return ACCOUNT_CONVERTER.convert(createAccount());
  }

  public static List<AccountDto> generateAccountDtoList(int count) {
    if (count < 1) {
      throw new IllegalArgumentException("Count must be more then 0");
    }

    return ACCOUNT_CONVERTER.convertAll(generateAccounts(count));
  }

  private static Account createAccount() {
    Account account = new Account();
    account.setData("test account data ");
    account.setStatus(AccountStatus.ACTIVE);

    return account;
  }

  private static List<Account> generateAccounts(int count) {
    return IntStream.range(0, count)
        .mapToObj(counter -> createAccount())
        .collect(Collectors.toList());
  }
}
