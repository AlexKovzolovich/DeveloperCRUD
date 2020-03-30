package ua.epam.service.serviceImpl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ua.epam.annotation.Timed;
import ua.epam.converter.DeveloperConverter;
import ua.epam.converter.TaskConverter;
import ua.epam.dto.DeveloperDto;
import ua.epam.dto.TaskDto;
import ua.epam.model.Account;
import ua.epam.model.Developer;
import ua.epam.model.Skill;
import ua.epam.model.Task;
import ua.epam.repository.hibernate.DeveloperDao;
import ua.epam.repository.hibernate.TaskDao;
import ua.epam.service.DeveloperService;

@Service
@Timed
public class DeveloperServiceImpl implements DeveloperService {

  private DeveloperConverter developerConverter;
  private TaskConverter taskConverter;
  private DeveloperDao developerDao;
  private TaskDao taskDao;

  @Autowired
  public DeveloperServiceImpl(DeveloperConverter developerConverter,
      TaskConverter taskConverter, DeveloperDao developerDao, TaskDao taskDao) {
    this.developerConverter = developerConverter;
    this.taskConverter = taskConverter;
    this.developerDao = developerDao;
    this.taskDao = taskDao;
  }

  @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
  @Override
  public DeveloperDto create(DeveloperDto developerDto) {
    Developer developer = developerConverter.unConvert(developerDto);
    developerDao.save(developer);

    return developerConverter.convert(developer);
  }

  @Override
  public DeveloperDto getById(UUID id) {
    Developer developer = developerDao.get(id);
    return developerConverter.convert(developer);
  }

  @Override
  public List<DeveloperDto> getAll() {
    List<Developer> developers = developerDao.getAll();
    return developerConverter.convertAll(developers);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @Override
  public void delete(DeveloperDto developerDto) {
    developerDao.delete(developerDto.getId());
  }

  @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
  @Override
  public DeveloperDto update(DeveloperDto developerDto) {
    Developer persistent = developerDao.get(developerDto.getId());
    Developer updated = developerConverter.unConvert(developerDto);

    performUpdate(persistent, updated);

    return developerConverter.convert(persistent);
  }

  @Override
  public List<TaskDto> getDevelopersTasksByDeveloperId(UUID id) {
    return taskConverter.convertAll(developerDao.getDeveloperTasksByDeveloperId(id));
  }

  @Override
  public TaskDto assignTaskToDeveloperById(TaskDto taskDto, UUID developerId) {
    Task task = taskConverter.unConvert(taskDto);
    Developer developer = developerDao.get(developerId);
    task.setAppointedDeveloper(developer);
    taskDao.save(task);
    return taskConverter.convert(task);
  }

  private void performUpdate(Developer persistent, Developer updated) {
    persistent.setName(updated.getName());

    updateAccount(persistent.getAccount(), updated.getAccount());
    updateSkills(persistent.getSkills(), updated.getSkills());
  }

  private void updateAccount(Account persistent, Account updated) {
    persistent.setStatus(updated.getStatus());
    persistent.setData(updated.getData());
  }

  private void updateSkills(Set<Skill> persisted, Set<Skill> updated) {
    Map<UUID, Skill> toUpdate = updated.stream()
        .collect(Collectors.toMap(Skill::getId, Function.identity()));

    Iterator<Skill> persistedIterator = persisted.iterator();
    while (persistedIterator.hasNext()) {
      Skill skill = persistedIterator.next();

      if (!toUpdate.containsKey(skill.getId())) {
        persistedIterator.remove();
      }

      if (toUpdate.containsKey(skill.getId())) {
        Skill updatedSkill = toUpdate.get(skill.getId());
        skill.setName(updatedSkill.getName());
        toUpdate.remove(skill.getId());
      }
    }

    persisted.addAll(toUpdate.values());
  }

}
