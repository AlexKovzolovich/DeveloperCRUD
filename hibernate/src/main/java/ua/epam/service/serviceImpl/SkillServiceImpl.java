package ua.epam.service.serviceImpl;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ua.epam.annotation.Timed;
import ua.epam.converter.SkillConverter;
import ua.epam.dto.SkillDto;
import ua.epam.model.Skill;
import ua.epam.repository.hibernate.SkillDao;
import ua.epam.service.SkillService;

@Service
@Timed
public class SkillServiceImpl implements SkillService {

  private SkillConverter converter;
  private SkillDao skillDao;

  @Autowired
  public SkillServiceImpl(SkillConverter skillConverter,
      SkillDao skillDao) {
    this.converter = skillConverter;
    this.skillDao = skillDao;
  }

  @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
  @Override
  public SkillDto create(SkillDto skillDto) {
    Skill skill = converter.unConvert(skillDto);
    skillDao.save(skill);

    return converter.convert(skill);
  }

  @Override
  public SkillDto getById(UUID id) {
    Skill skill = skillDao.get(id);
    return converter.convert(skill);
  }

  @Override
  public List<SkillDto> getAll() {
    List<Skill> skills = skillDao.getAll();
    return converter.convertAll(skills);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @Override
  public void delete(SkillDto skillDto) {
    skillDao.delete(skillDto.getId());
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  @Override
  public SkillDto update(SkillDto skillDto) {
    Skill persistent = skillDao.get(skillDto.getId());
    Skill updated = converter.unConvert(skillDto);

    performUpdate(persistent, updated);

    return converter.convert(persistent);
  }

  private void performUpdate(Skill persistent, Skill updated) {
    persistent.setName(updated.getName());
  }
}
