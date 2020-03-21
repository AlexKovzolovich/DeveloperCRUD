package ua.epam.dto;

import java.util.Objects;
import java.util.Set;

public class DeveloperDto {

  private Long id;
  private String name;
  private AccountDto accountDto;
  private Set<SkillDto> skillDtos;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public AccountDto getAccountDto() {
    return accountDto;
  }

  public void setAccountDto(AccountDto accountDto) {
    this.accountDto = accountDto;
  }

  public Set<SkillDto> getSkillDtos() {
    return skillDtos;
  }

  public void setSkillDtos(Set<SkillDto> skillDtos) {
    this.skillDtos = skillDtos;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DeveloperDto that = (DeveloperDto) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(name, that.name) &&
        Objects.equals(accountDto, that.accountDto) &&
        Objects.equals(skillDtos, that.skillDtos);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, accountDto, skillDtos);
  }
}
