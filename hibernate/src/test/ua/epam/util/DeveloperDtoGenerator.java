package ua.epam.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import ua.epam.converter.AccountConverter;
import ua.epam.converter.DeveloperConverter;
import ua.epam.converter.SkillConverter;
import ua.epam.dto.DeveloperDto;
import ua.epam.model.Account;
import ua.epam.model.AccountStatus;
import ua.epam.model.Developer;
import ua.epam.model.Skill;

public class DeveloperDtoGenerator {

  private static final DeveloperConverter DEVELOPER_CONVERTER = new DeveloperConverter(
      new AccountConverter(),
      new SkillConverter());

  public static DeveloperDto createDeveloperDto() {
    return DEVELOPER_CONVERTER.convert(createDeveloper());
  }

  public static List<DeveloperDto> generateDeveloperDtoList(int count) {
    if (count < 1) {
      throw new IllegalArgumentException("Count must be more then 0");
    }
    return DEVELOPER_CONVERTER.convertAll(generateDevelopers(count));
  }

  private static Developer createDeveloper() {
    Developer developer = new Developer();
    developer.setName("test name");

    Account account = new Account();
    account.setData("test account");
    account.setStatus(AccountStatus.ACTIVE);

    developer.setAccount(account);

    Set<Skill> skills = new HashSet<>();
    Skill skill = new Skill();
    skill.setName("java");
    skills.add(skill);
    developer.setSkills(skills);

    return developer;
  }

  private static List<Developer> generateDevelopers(int count) {
    return IntStream.range(0, count)
        .mapToObj(counter -> createDeveloper())
        .collect(Collectors.toList());
  }
}
