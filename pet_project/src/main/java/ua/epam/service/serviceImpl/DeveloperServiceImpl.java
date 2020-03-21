package ua.epam.service.serviceImpl;

import java.util.List;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ua.epam.annotation.Timed;
import ua.epam.converter.DeveloperConverter;
import ua.epam.dto.DeveloperDto;
import ua.epam.exceptions.AlreadyExistsException;
import ua.epam.exceptions.NotExistException;
import ua.epam.repository.spring.DeveloperRepositoryJpa;
import ua.epam.service.DeveloperService;

@Log4j
@Service
@Timed
public class DeveloperServiceImpl implements DeveloperService {

  private DeveloperRepositoryJpa developerRepository;
  private DeveloperConverter developerConverter;

  @Autowired
  public DeveloperServiceImpl(DeveloperRepositoryJpa developerRepository,
      DeveloperConverter developerConverter) {
    this.developerRepository = developerRepository;
    this.developerConverter = developerConverter;
  }

  @Override
  public DeveloperDto getById(Long id) {
    return developerConverter.convert(developerRepository.findById(id).orElseThrow(
        NotExistException::new));
  }

  @Override
  public List<DeveloperDto> getAll() {
    return developerConverter.convertAll(developerRepository.findAll());
  }

  @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
  @Override
  public void save(DeveloperDto developerDto) {
    if (developerRepository.findById(developerDto.getId()).isPresent()) {
      throw new AlreadyExistsException();
    }
    developerRepository.save(developerConverter.unConvert(developerDto));
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @Override
  public void delete(DeveloperDto developerDto) {
    developerRepository.findById(developerDto.getId()).orElseThrow(NotExistException::new);
    developerRepository.delete(developerConverter.unConvert(developerDto));
  }

  @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
  @Override
  public void update(DeveloperDto developerDto) {
    save(developerDto);
  }
}
