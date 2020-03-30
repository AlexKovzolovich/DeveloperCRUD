package ua.epam.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import java.net.URI;
import java.util.UUID;
import javax.transaction.Transactional;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import ua.epam.BaseSpringIT;
import ua.epam.dto.DeveloperDto;
import ua.epam.util.DeveloperDtoGenerator;

public class DeveloperControllerIT extends BaseSpringIT {

  private static final String URL = "/api/v1/developers";
  private final Gson gson = new Gson();

  @Test
  @Transactional
  public void transactionCreationWorksThroughAllLayersWhenExceptionThrown() throws Exception {
    //Given
    DeveloperDto developerDto = DeveloperDtoGenerator.createDeveloperDto();
    UUID id = developerDto.getId();
    Mockito.when(restTemplate.getForObject(any(URI.class), eq(DeveloperDto.class)))
        .thenThrow(new RuntimeException("Connection error has been occurred"));

    mockMvc.perform(post(URL)
        .with(user("user").password("resu").roles("USER"))
        .with(csrf())
        .characterEncoding("UTF-8")
        .contentType(MediaType.APPLICATION_JSON)
        .content(gson.toJson(developerDto)))
        .andExpect(status().isCreated());

    mockMvc.perform(get(URL).param("id", id.toString())
        .with(user("user").password("resu").roles("USER")))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(developerDto.getId().toString())))
        .andExpect(jsonPath("$.name", is(developerDto.getName())))

        .andExpect(
            jsonPath("$.accountDto.id", is(developerDto.getAccountDto().getId().toString())))

        .andExpect(
            jsonPath("$.accountDto.status",
                is(developerDto.getAccountDto().getStatus())))

        .andExpect(jsonPath("$.skillDtos[0].id",
            is(developerDto.getSkillDtos().iterator().next().getId().toString())));
  }

  @Test
  @Transactional
  public void transactionCreationWorksThroughAllLayers() throws Exception {
    //Given
    DeveloperDto developerDto = DeveloperDtoGenerator.createDeveloperDto();
    UUID id = developerDto.getId();
    Mockito.when(restTemplate.getForObject(any(URI.class), eq(DeveloperDto.class)))
        .thenReturn(developerDto);

    mockMvc.perform(post(URL)
        .with(user("user").password("resu").roles("USER"))
        .with(csrf())
        .characterEncoding("UTF-8")
        .contentType(MediaType.APPLICATION_JSON)
        .content(gson.toJson(developerDto)))
        .andExpect(status().isCreated());

    mockMvc.perform(get(URL).param("id", id.toString())
        .with(user("user").password("resu").roles("USER")))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(developerDto.getId().toString())))
        .andExpect(jsonPath("$.name", is(developerDto.getName())))

        .andExpect(
            jsonPath("$.accountDto.id", is(developerDto.getAccountDto().getId().toString())))

        .andExpect(
            jsonPath("$.accountDto.status",
                is(developerDto.getAccountDto().getStatus())))

        .andExpect(jsonPath("$.skillDtos[0].id",
            is(developerDto.getSkillDtos().iterator().next().getId().toString())));
  }
}
