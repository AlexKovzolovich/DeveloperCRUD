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
import java.util.UUID;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ua.epam.dto.AccountDto;
import ua.epam.service.AccountService;
import ua.epam.util.AccountDtoGenerator;

public class AccountControllerTest {

  private static final String URL = "/api/v1/accounts";
  private final AccountService accountService = Mockito.mock(AccountService.class);
  private final AccountController sut = new AccountController(accountService);
  private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(sut).build();
  private final Gson gson = new Gson();

  @Test
  public void testGetAccount() throws Exception {
    AccountDto output = AccountDtoGenerator.createAccountDto();
    UUID id = output.getId();
    given(accountService.getById(id)).willReturn(output);

    mockMvc.perform(get(URL).param("id", id.toString()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(output.getId().toString())))
        .andExpect(jsonPath("$.data", is(output.getData())))
        .andExpect(
            jsonPath("$.status", is(output.getStatus())));
  }

  @Test
  public void testGetAccounts() throws Exception {
    List<AccountDto> output = AccountDtoGenerator.generateAccountDtoList(2);
    given(accountService.getAll()).willReturn(output);

    mockMvc.perform(get(URL))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].id", is(output.get(0).getId().toString())))
        .andExpect(jsonPath("$[1].id", is(output.get(1).getId().toString())))
        .andExpect(jsonPath("$[0].data", is(output.get(0).getData())))
        .andExpect(jsonPath("$[1].data", is(output.get(1).getData())))
        .andExpect(jsonPath("$[0].status",
            is(output.get(0).getStatus())))
        .andExpect(jsonPath("$[1].status",
            is(output.get(1).getStatus())));
  }

  @Test
  public void testPostAccount() throws Exception {
    AccountDto input = AccountDtoGenerator.createAccountDto();
    given(accountService.create(input)).willReturn(input);

    mockMvc.perform(post(URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(gson.toJson(input)))
        .andExpect(status().isCreated());

    then(accountService).should(times(1)).create(input);
  }

  @Test
  public void testPutAccount() throws Exception {
    AccountDto input = AccountDtoGenerator.createAccountDto();
    given(accountService.update(input)).willReturn(input);

    mockMvc.perform(put(URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(gson.toJson(input)))
        .andExpect(status().isOk());

    then(accountService).should(times(1)).update(input);
  }

  @Test
  public void testDeleteAccount() throws Exception {
    AccountDto input = AccountDtoGenerator.createAccountDto();
    willDoNothing().given(accountService).delete(input);

    mockMvc.perform(delete(URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(gson.toJson(input)))
        .andExpect(status().isOk());

    then(accountService).should(times(1)).delete(input);
  }
}