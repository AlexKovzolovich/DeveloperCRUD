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
import ua.epam.dto.DeveloperDto;
import ua.epam.service.DeveloperService;
import ua.epam.util.DeveloperDtoGenerator;

public class DeveloperControllerTest {

  private static final String URL = "/api/v1/developer";
  private final DeveloperService developerService = Mockito.mock(DeveloperService.class);
  private final DeveloperController sut = new DeveloperController(developerService);
  private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(sut).build();
  private final Gson gson = new Gson();

  @Test
  public void getDeveloperTest() throws Exception {
    DeveloperDto output = DeveloperDtoGenerator.createDeveloperDto(0);
    given(developerService.getById(0L)).willReturn(output);

    mockMvc.perform(get(URL).param("id", "0"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(output.getId().intValue())))
        .andExpect(jsonPath("$.name", is(output.getName())))
        .andExpect(jsonPath("$.accountDto.id", is(output.getAccountDto().getId().intValue())))
        .andExpect(jsonPath("$.skillDtos[0].id",
            is(output.getSkillDtos().iterator().next().getId().intValue())))
        .andExpect(
            jsonPath("$.skillDtos[0].name", is(output.getSkillDtos().iterator().next().getName())));
  }

  @Test
  public void getDevelopersTest() throws Exception {
    List<DeveloperDto> output = DeveloperDtoGenerator.generateDeveloperDtoList(2);
    given(developerService.getAll()).willReturn(output);

    mockMvc.perform(get(URL))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))

        .andExpect(jsonPath("$[0].id", is(output.get(0).getId().intValue())))
        .andExpect(jsonPath("$[0].name", is(output.get(0).getName())))
        .andExpect(
            jsonPath("$[0].accountDto.id", is(output.get(0).getAccountDto().getId().intValue())))
        .andExpect(jsonPath("$[0].skillDtos[0].id",
            is(output.get(0).getSkillDtos().iterator().next().getId().intValue())))
        .andExpect(jsonPath("$[0].skillDtos[0].name",
            is(output.get(0).getSkillDtos().iterator().next().getName())))

        .andExpect(jsonPath("$[1].id", is(output.get(1).getId().intValue())))
        .andExpect(jsonPath("$[1].name", is(output.get(1).getName())))
        .andExpect(
            jsonPath("$[1].accountDto.id", is(output.get(1).getAccountDto().getId().intValue())))
        .andExpect(jsonPath("$[1].skillDtos[0].id",
            is(output.get(1).getSkillDtos().iterator().next().getId().intValue())))
        .andExpect(jsonPath("$[1].skillDtos[0].name",
            is(output.get(1).getSkillDtos().iterator().next().getName())));
  }

  @Test
  public void postDeveloperTest() throws Exception {
    DeveloperDto input = DeveloperDtoGenerator.createDeveloperDto(0);
    willDoNothing().given(developerService).save(input);

    mockMvc.perform(post(URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(gson.toJson(input)))
        .andExpect(status().isCreated());

    then(developerService).should(times(1)).save(input);
  }

  @Test
  public void putDeveloperTest() throws Exception {
    DeveloperDto input = DeveloperDtoGenerator.createDeveloperDto(0);
    willDoNothing().given(developerService).update(input);

    mockMvc.perform(put(URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(gson.toJson(input)))
        .andExpect(status().isOk());

    then(developerService).should(times(1)).update(input);
  }

  @Test
  public void deleteDeveloperTest() throws Exception {
    DeveloperDto input = DeveloperDtoGenerator.createDeveloperDto(0);
    willDoNothing().given(developerService).delete(input);

    mockMvc.perform(delete(URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(gson.toJson(input)))
        .andExpect(status().isOk());

    then(developerService).should(times(1)).delete(input);
  }
}