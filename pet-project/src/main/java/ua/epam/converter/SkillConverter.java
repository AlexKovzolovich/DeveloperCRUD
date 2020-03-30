package ua.epam.converter;

import java.util.Objects;
import org.springframework.stereotype.Component;
import ua.epam.dto.SkillDto;
import ua.epam.model.Skill;

@Component
public class SkillConverter implements Converter<Skill, SkillDto> {

  @Override
  public SkillDto convert(Skill skill) {
    if (Objects.isNull(skill)) {
      throw new IllegalArgumentException("Can`t convert skill to dto, skill is null");
    }

    SkillDto dto = new SkillDto();
    dto.setId(skill.getId());
    dto.setName(skill.getName());
    return dto;
  }

  @Override
  public Skill unConvert(SkillDto dto) {
    if (Objects.isNull(dto)) {
      throw new IllegalArgumentException("Can`t convert dto to skill, dto is null");
    }

    Skill skill = new Skill();
    skill.setId(dto.getId());
    skill.setName(dto.getName());
    return skill;
  }
}
