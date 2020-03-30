package ua.epam.dto;

import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeveloperDto {
  
  private UUID id;
  
  private String name;
  
  private AccountDto accountDto;
  
  private Set<SkillDto> skillDtos;

}
