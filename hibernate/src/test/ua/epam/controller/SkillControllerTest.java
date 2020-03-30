package ua.epam.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.times;
import static org.mockito.BDDMockito.willDoNothing;
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
import ua.epam.dto.SkillDto;
import ua.epam.service.SkillService;
import ua.epam.util.SkillDtoGenerator;

public class SkillControllerTest {

  private final static String URL = "/api/v1/skills";
  private final SkillService skillService = Mockito.mock(SkillService.class);
  private final SkillController sut = new SkillController(skillService);
  private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(sut).build();
  private final Gson gson = new Gson();

  @Test
  public void testGetSkill() throws Exception {
    SkillDto output = SkillDtoGenerator.createSkillDto();
    UUID id = output.getId();
    given(skillService.getById(id)).willReturn(output);

    mockMvc.perform(get(URL).param("id", id.toString()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(output.getId().toString())))
        .andExpect(jsonPath("$.name", is(output.getName())));
  }

  @Test
  public void testGetSkills() throws Exception {
    List<SkillDto> output = SkillDtoGenerator.generateSkillDtoList(2);
    given(skillService.getAll()).willReturn(output);

    mockMvc.perform(get(URL))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].id", is(output.get(0).getId().toString())))
        .andExpect(jsonPath("$[1].id", is(output.get(1).getId().toString())))
        .andExpect(jsonPath("$[0].name", is(output.get(0).getName())))
        .andExpect(jsonPath("$[1].name", is(output.get(1).getName())));
  }

  @Test
  public void testPostSkill() throws Exception {
    SkillDto input = SkillDtoGenerator.createSkillDto();
    given(skillService.create(input)).willReturn(input);

    mockMvc.perform(post(URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(gson.toJson(input)))
        .andExpect(status().isCreated());

    then(skillService).should(times(1)).create(input);
  }

  @Test
  public void testPutSkill() throws Exception {
    SkillDto input = SkillDtoGenerator.createSkillDto();
    given(skillService.update(input)).willReturn(input);

    mockMvc.perform(put(URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(gson.toJson(input)))
        .andExpect(status().isOk());

    then(skillService).should(times(1)).update(input);
  }

  @Test
  public void testDeleteSkill() throws Exception {
    SkillDto input = SkillDtoGenerator.createSkillDto();
    willDoNothing().given(skillService).delete(input);

    mockMvc.perform(delete(URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(gson.toJson(input)))
        .andExpect(status().isOk());

    then(skillService).should(times(1)).delete(input);
  }
}