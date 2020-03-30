package ua.epam.service.serviceImpl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import ua.epam.BaseSpringIT;
import ua.epam.dto.DeveloperDto;
import ua.epam.dto.TaskDto;
import ua.epam.service.DeveloperService;
import ua.epam.service.TaskService;
import ua.epam.util.DeveloperDtoGenerator;
import ua.epam.util.TaskDtoGenerator;

public class TaskServiceImplTest extends BaseSpringIT {

  @Autowired
  private TaskService taskService;

  @Autowired
  private DeveloperService developerService;

  @Test
  @WithMockUser
  public void testCreate() {
    DeveloperDto developerDto = DeveloperDtoGenerator.createDeveloperDto();
    developerService.create(developerDto);

    TaskDto taskDto = TaskDtoGenerator.createTaskDto();
    taskDto.setAppointedDeveloperId(developerDto.getId());

    TaskDto createdTask = taskService.create(taskDto);

    assertEquals(taskDto, createdTask);
  }

  @Test
  @WithMockUser
  public void testGetById() {
    DeveloperDto developerDto = DeveloperDtoGenerator.createDeveloperDto();
    developerService.create(developerDto);

    TaskDto taskDto = TaskDtoGenerator.createTaskDto();
    taskDto.setAppointedDeveloperId(developerDto.getId());

    taskService.create(taskDto);

    TaskDto loaded = taskService.getById(taskDto.getId());

    assertEquals(taskDto, loaded);
  }

  @Test
  @WithMockUser(roles = {"ADMIN"})
  public void testDelete() {
    DeveloperDto developerDto = DeveloperDtoGenerator.createDeveloperDto();
    developerService.create(developerDto);

    TaskDto taskDto = TaskDtoGenerator.createTaskDto();
    taskDto.setAppointedDeveloperId(developerDto.getId());

    TaskDto created = taskService.create(taskDto);

    taskService.delete(created);
  }

  @Test
  @WithMockUser(roles = {"ADMIN"})
  public void testUpdate() {
    DeveloperDto developerDto = DeveloperDtoGenerator.createDeveloperDto();
    developerService.create(developerDto);

    TaskDto taskDto = TaskDtoGenerator.createTaskDto();
    taskDto.setAppointedDeveloperId(developerDto.getId());

    TaskDto created = taskService.create(taskDto);

    created.setDescription("new test description");

    TaskDto updated = taskService.update(created);

    assertEquals(created, updated);
  }
}