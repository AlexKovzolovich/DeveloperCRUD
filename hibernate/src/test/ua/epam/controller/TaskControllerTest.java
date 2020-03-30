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
import ua.epam.dto.TaskDto;
import ua.epam.service.TaskService;
import ua.epam.util.TaskDtoGenerator;

public class TaskControllerTest {

  private static final String URL = "/api/v1/tasks";
  private final TaskService taskService = Mockito.mock(TaskService.class);
  private final TaskController sut = new TaskController(taskService);
  private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(sut).build();
  private final Gson gson = new Gson();


  @Test
  public void testGetTask() throws Exception {
    TaskDto output = TaskDtoGenerator.createTaskDto();
    UUID id = output.getId();
    given(taskService.getById(id)).willReturn(output);

    mockMvc.perform(get(URL).param("id", id.toString()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(output.getId().toString())))
        .andExpect(jsonPath("$.description", is(output.getDescription())));
  }

  @Test
  public void testGetTasks() throws Exception {
    List<TaskDto> output = TaskDtoGenerator.generateTaskDtoList(2);
    given(taskService.getAll()).willReturn(output);

    mockMvc.perform(get(URL))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].id", is(output.get(0).getId().toString())))
        .andExpect(jsonPath("$[0].description", is(output.get(0).getDescription())))
        .andExpect(jsonPath("$[1].id", is(output.get(1).getId().toString())))
        .andExpect(jsonPath("$[1].description", is(output.get(1).getDescription())));

  }

  @Test
  public void testPostTask() throws Exception {
    TaskDto input = TaskDtoGenerator.createTaskDto();
    given(taskService.create(input)).willReturn(input);

    mockMvc.perform(post(URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(gson.toJson(input)))
        .andExpect(status().isCreated());

    then(taskService).should(times(1)).create(input);
  }

  @Test
  public void testPutTask() throws Exception {
    TaskDto input = TaskDtoGenerator.createTaskDto();
    given(taskService.update(input)).willReturn(input);

    mockMvc.perform(put(URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(gson.toJson(input)))
        .andExpect(status().isOk());

    then(taskService).should(times(1)).update(input);
  }

  @Test
  public void testDeleteTask() throws Exception {
    TaskDto input = TaskDtoGenerator.createTaskDto();
    willDoNothing().given(taskService).delete(input);

    mockMvc.perform(delete(URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(gson.toJson(input)))
        .andExpect(status().isOk());

    then(taskService).should(times(1)).delete(input);
  }
}