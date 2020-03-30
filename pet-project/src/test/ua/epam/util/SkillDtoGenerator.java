package ua.epam.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.experimental.UtilityClass;
import ua.epam.converter.SkillConverter;
import ua.epam.dto.SkillDto;
import ua.epam.model.Skill;

@UtilityClass
public class SkillDtoGenerator {

  private static final SkillConverter SKILL_CONVERTER = new SkillConverter();

  public static SkillDto createSkillDto(long id) {
    return SKILL_CONVERTER.convert(createSkill(id));
  }

  public static List<SkillDto> generateSkillDtoList(int count) {
    if (count < 1) {
      throw new IllegalArgumentException("Count must be more then 0");
    }
    return SKILL_CONVERTER.convertAll(generateSkills(count));
  }

  private static Skill createSkill(long id) {
    Skill skill = new Skill();
    skill.setId(id);
    skill.setName("test skill");

    return skill;
  }

  private static List<Skill> generateSkills(int count) {
    return IntStream.range(0, count)
        .mapToObj(SkillDtoGenerator::createSkill)
        .collect(Collectors.toList());
  }
}
