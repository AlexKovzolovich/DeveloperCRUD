package ua.epam.service.serviceImpl;

import java.util.List;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ua.epam.annotation.Timed;
import ua.epam.converter.SkillConverter;
import ua.epam.dto.SkillDto;
import ua.epam.exceptions.AlreadyExistsException;
import ua.epam.exceptions.NotExistException;
import ua.epam.repository.spring.SkillRepositoryJpa;
import ua.epam.service.SkillService;

@Log4j
@Service
@Timed
public class SkillServiceImpl implements SkillService {

  private SkillRepositoryJpa skillRepository;
  private SkillConverter skillConverter;

  @Autowired
  public SkillServiceImpl(SkillRepositoryJpa skillRepository,
      SkillConverter skillConverter) {
    this.skillRepository = skillRepository;
    this.skillConverter = skillConverter;
  }

  @Override
  public SkillDto getById(Long id) {
    return skillConverter.convert(skillRepository.findById(id).orElseThrow(NotExistException::new));
  }

  @Override
  public List<SkillDto> getAll() {
    return skillConverter.convertAll(skillRepository.findAll());
  }

  @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
  @Override
  public void save(SkillDto skillDto) {
    if (skillRepository.findById(skillDto.getId()).isPresent()) {
      throw new AlreadyExistsException();
    }
    skillRepository.save(skillConverter.unConvert(skillDto));
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @Override
  public void delete(SkillDto skillDto) {
    skillRepository.findById(skillDto.getId()).orElseThrow(NotExistException::new);
    skillRepository.delete(skillConverter.unConvert(skillDto));
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMIN')")
  @Override
  public void update(SkillDto skillDto) {
    save(skillDto);
  }
}
