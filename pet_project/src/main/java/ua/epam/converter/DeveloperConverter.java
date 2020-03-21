package ua.epam.converter;

import java.util.HashSet;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.dto.DeveloperDto;
import ua.epam.model.Developer;

@Component
public class DeveloperConverter implements Converter<Developer, DeveloperDto> {

  private AccountConverter accountConverter;
  private SkillConverter skillConverter;

  @Autowired
  public DeveloperConverter(AccountConverter accountStatusConverter,
      SkillConverter skillConverter) {
    this.accountConverter = accountStatusConverter;
    this.skillConverter = skillConverter;
  }

  @Override
  public DeveloperDto convert(Developer developer) {
    if (Objects.isNull(developer)) {
      throw new IllegalArgumentException("Can`t convert developer to dto, developer is null");
    }

    if (Objects.isNull(developer.getAccount())) {
      throw new IllegalArgumentException(
          "Can`t convert developer to dto, developer`s account is null");
    }

    if (Objects.isNull(developer.getAccount().getStatus())) {
      throw new IllegalArgumentException(
          "Can`t convert developer to dto, developer`s account status is null");
    }

    if (Objects.isNull(developer.getSkills())) {
      throw new IllegalArgumentException(
          "Can`t convert developer to dto, developer`s set of skills is null");
    }

    DeveloperDto dto = new DeveloperDto();
    dto.setId(developer.getId());
    dto.setName(developer.getName());
    dto.setAccountDto(accountConverter.convert(developer.getAccount()));
    dto.setSkillDtos(new HashSet<>(skillConverter.convertAll(developer.getSkills())));

    return dto;
  }

  @Override
  public Developer unConvert(DeveloperDto dto) {
    if (Objects.isNull(dto)) {
      throw new IllegalArgumentException("Can`t convert dto to developer, dto is null");
    }

    if (Objects.isNull(dto.getAccountDto())) {
      throw new IllegalArgumentException("Can`t convert dto to developer, dto`s account is null");
    }

    if (Objects.isNull(dto.getAccountDto().getAccountStatusDto())) {
      throw new IllegalArgumentException(
          "Can`t convert dto to developer, dto`s account status is null");
    }

    if (Objects.isNull(dto.getSkillDtos())) {
      throw new IllegalArgumentException(
          "Can`t convert dto to developer, dto`s set of skills is null");
    }

    Developer developer = new Developer();
    developer.setId(dto.getId());
    developer.setName(dto.getName());
    developer.setAccount(accountConverter.unConvert(dto.getAccountDto()));
    developer.setSkills(new HashSet<>(skillConverter.unConvertAll(dto.getSkillDtos())));

    return developer;
  }
}
