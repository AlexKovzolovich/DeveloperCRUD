package ua.epam.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import ua.epam.BaseSpringIT;

public class SecretControllerTestIT extends BaseSpringIT {

  @Test
  public void shouldRedirectToLoginPage() throws Exception {
    mockMvc.perform(get("/secret"))
        .andExpect(status().isFound());
  }

  @Test
  public void shouldGetForbiddenResponse() throws Exception {
    mockMvc.perform(get("/secret").with(user("user").password("resu").roles("USER")))
        .andExpect(status().isForbidden());
  }

  @Test
  public void shouldGetSecretResource() throws Exception {
    mockMvc.perform(get("/secret").with(user("admin").password("nimda").roles("ADMIN")))
        .andExpect(status().isOk());
  }
}