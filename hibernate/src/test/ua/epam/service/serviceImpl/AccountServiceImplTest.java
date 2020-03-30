package ua.epam.service.serviceImpl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import ua.epam.BaseSpringIT;
import ua.epam.dto.AccountDto;
import ua.epam.model.AccountStatus;
import ua.epam.service.AccountService;
import ua.epam.util.AccountDtoGenerator;

public class AccountServiceImplTest extends BaseSpringIT {

  @Autowired
  private AccountService accountService;

  @Test
  @WithMockUser
  public void testCreate() {
    AccountDto accountDto = AccountDtoGenerator.createAccountDto();

    AccountDto created = accountService.create(accountDto);

    assertEquals(accountDto, created);
  }

  @Test
  @WithMockUser
  public void testGetById() {
    AccountDto accountDto = AccountDtoGenerator.createAccountDto();
    accountService.create(accountDto);

    AccountDto created = accountService.getById(accountDto.getId());

    assertEquals(accountDto, created);
  }

  @Test
  @WithMockUser(roles = {"ADMIN"})
  public void testDelete() {
    AccountDto accountDto = AccountDtoGenerator.createAccountDto();
    AccountDto created = accountService.create(accountDto);

    accountService.delete(created);
  }

  @Test
  @WithMockUser(roles = {"ADMIN"})
  public void testUpdate() {
    AccountDto accountDto = AccountDtoGenerator.createAccountDto();
    AccountDto created = accountService.create(accountDto);

    created.setData("new test data");
    created.setStatus(AccountStatus.BANNED.name());

    AccountDto updated = accountService.update(created);

    assertEquals(created, updated);
  }
}