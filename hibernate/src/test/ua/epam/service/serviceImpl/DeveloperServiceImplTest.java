package ua.epam.service.serviceImpl;

import static org.junit.Assert.assertEquals;

import java.util.List;
import javax.transaction.Transactional;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import ua.epam.BaseSpringIT;
import ua.epam.dto.DeveloperDto;
import ua.epam.dto.SkillDto;
import ua.epam.dto.TaskDto;
import ua.epam.service.DeveloperService;
import ua.epam.service.SkillService;
import ua.epam.service.TaskService;
import ua.epam.util.DeveloperDtoGenerator;
import ua.epam.util.SkillDtoGenerator;
import ua.epam.util.TaskDtoGenerator;

public class DeveloperServiceImplTest extends BaseSpringIT {

  @Autowired
  private DeveloperService developerService;

  @Autowired
  private SkillService skillService;

  @Autowired
  private TaskService taskService;

  @Test
  @WithMockUser
  public void testCreate() {
    DeveloperDto developerDto = DeveloperDtoGenerator.createDeveloperDto();

    DeveloperDto created = developerService.create(developerDto);

    assertEquals(developerDto, created);
  }

  @Test
  @WithMockUser
  @Transactional
  public void testGetById() {
    DeveloperDto developerDto = DeveloperDtoGenerator.createDeveloperDto();
    developerService.create(developerDto);

    DeveloperDto created = developerService.getById(developerDto.getId());

    assertEquals(developerDto, created);
  }

  @Test
  @WithMockUser(roles = {"ADMIN"})
  public void testDelete() {
    DeveloperDto developerDto = DeveloperDtoGenerator.createDeveloperDto();
    DeveloperDto created = developerService.create(developerDto);

    developerService.delete(created);
  }

  @Test
  @WithMockUser(roles = {"ADMIN"})
  @Transactional
  public void testUpdate() {
    DeveloperDto developerDto = DeveloperDtoGenerator.createDeveloperDto();
    DeveloperDto created = developerService.create(developerDto);

    created.setName("new test name");
    created.getAccountDto().setData("new test data");
    SkillDto skillDto = SkillDtoGenerator.createSkillDto();
    skillService.create(skillDto);
    created.getSkillDtos().add(skillDto);

    DeveloperDto updated = developerService.update(created);

    assertEquals(created, updated);
  }

  @Test
  @WithMockUser(roles = {"ADMIN"})
  public void testGetDevelopersTasksByDeveloperId() {
    List<TaskDto> tasks = TaskDtoGenerator.generateTaskDtoList(2);
    DeveloperDto developerDto = DeveloperDtoGenerator.createDeveloperDto();
    developerService.create(developerDto);

    for (TaskDto dto : tasks) {
      dto.setAppointedDeveloperId(developerDto.getId());
      taskService.create(dto);
    }

    List<TaskDto> loaded = developerService.getDevelopersTasksByDeveloperId(developerDto.getId());

    assertEquals(tasks, loaded);
  }
}