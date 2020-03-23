package ua.epam.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import java.util.List;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ua.epam.dto.AccountDto;
import ua.epam.service.AccountService;
import ua.epam.util.AccountDtoGenerator;

public class AccountControllerTest {

  private static final String URL = "/api/v1/account";
  private final AccountService accountService = Mockito.mock(AccountService.class);
  private final AccountController sut = new AccountController(accountService);
  private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(sut).build();
  private final Gson gson = new Gson();

  @Test
  public void getAccountTest() throws Exception {
    AccountDto output = AccountDtoGenerator.createAccountDto(0);
    given(accountService.getById(0L)).willReturn(output);

    mockMvc.perform(get(URL).param("id", "0"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(output.getId().intValue())))
        .andExpect(jsonPath("$.data", is(output.getData())))
        .andExpect(
            jsonPath("$.accountStatusDto.id", is(output.getAccountStatusDto().getId().intValue())))
        .andExpect(
            jsonPath("$.accountStatusDto.status", is(output.getAccountStatusDto().getStatus())));
  }

  @Test
  public void getAccountsTest() throws Exception {
    List<AccountDto> output = AccountDtoGenerator.generateAccountDtoList(2);
    given(accountService.getAll()).willReturn(output);

    mockMvc.perform(get(URL))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].id", is(output.get(0).getId().intValue())))
        .andExpect(jsonPath("$[1].id", is(output.get(1).getId().intValue())))
        .andExpect(jsonPath("$[0].data", is(output.get(0).getData())))
        .andExpect(jsonPath("$[1].data", is(output.get(1).getData())))
        .andExpect(jsonPath("$[0].accountStatusDto.id",
            is(output.get(0).getAccountStatusDto().getId().intValue())))
        .andExpect(jsonPath("$[1].accountStatusDto.id",
            is(output.get(1).getAccountStatusDto().getId().intValue())))
        .andExpect(jsonPath("$[0].accountStatusDto.status",
            is(output.get(0).getAccountStatusDto().getStatus())))
        .andExpect(jsonPath("$[1].accountStatusDto.status",
            is(output.get(1).getAccountStatusDto().getStatus())));
  }

  @Test
  public void postAccountTest() throws Exception {
    AccountDto input = AccountDtoGenerator.createAccountDto(0);
    willDoNothing().given(accountService).save(input);

    mockMvc.perform(post(URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(gson.toJson(input)))
        .andExpect(status().isCreated());

    then(accountService).should(times(1)).save(input);
  }

  @Test
  public void putAccountTest() throws Exception {
    AccountDto input = AccountDtoGenerator.createAccountDto(0);
    willDoNothing().given(accountService).update(input);

    mockMvc.perform(put(URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(gson.toJson(input)))
        .andExpect(status().isOk());

    then(accountService).should(times(1)).update(input);
  }

  @Test
  public void deleteAccountTest() throws Exception {
    AccountDto input = AccountDtoGenerator.createAccountDto(0);
    willDoNothing().given(accountService).delete(input);

    mockMvc.perform(delete(URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(gson.toJson(input)))
        .andExpect(status().isOk());

    then(accountService).should(times(1)).delete(input);
  }
}