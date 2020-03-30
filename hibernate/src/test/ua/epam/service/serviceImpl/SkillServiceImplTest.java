package ua.epam.service.serviceImpl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import ua.epam.BaseSpringIT;
import ua.epam.dto.SkillDto;
import ua.epam.service.SkillService;
import ua.epam.util.SkillDtoGenerator;

public class SkillServiceImplTest extends BaseSpringIT {

  @Autowired
  private SkillService skillService;

  @Test
  @WithMockUser
  public void testCreate() {
    SkillDto skillDto = SkillDtoGenerator.createSkillDto();

    SkillDto createdSkill = skillService.create(skillDto);

    assertEquals(skillDto, createdSkill);
  }

  @Test
  @WithMockUser
  public void testGetById() {
    SkillDto skillDto = SkillDtoGenerator.createSkillDto();
    skillService.create(skillDto);

    SkillDto created = skillService.getById(skillDto.getId());

    assertEquals(skillDto, created);
  }

  @Test
  @WithMockUser(roles = {"ADMIN"})
  public void testDelete() {
    SkillDto skillDto = SkillDtoGenerator.createSkillDto();
    SkillDto created = skillService.create(skillDto);

    skillService.delete(created);
  }

  @Test
  @WithMockUser(roles = {"ADMIN"})
  public void testUpdate() {
    SkillDto skillDto = SkillDtoGenerator.createSkillDto();
    SkillDto created = skillService.create(skillDto);

    created.setName("new test name");

    SkillDto updated = skillService.update(created);

    assertEquals(created, updated);
  }
}