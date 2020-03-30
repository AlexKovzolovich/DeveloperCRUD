package ua.epam.service.serviceImpl;

import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.converter.TaskConverter;
import ua.epam.dto.TaskDto;
import ua.epam.model.Task;
import ua.epam.repository.hibernate.TaskDao;
import ua.epam.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

  private TaskConverter converter;
  private TaskDao taskDao;

  @Autowired
  public TaskServiceImpl(TaskConverter converter, TaskDao taskDao) {
    this.converter = converter;
    this.taskDao = taskDao;
  }

  @Override
  @Transactional
  public TaskDto create(TaskDto taskDto) {
    Task task = converter.unConvert(taskDto);
    taskDao.save(task);
    return converter.convert(task);
  }

  @Override
  @Transactional
  public TaskDto getById(UUID id) {
    Task task = taskDao.get(id);
    return converter.convert(task);
  }

  @Override
  @Transactional
  public List<TaskDto> getAll() {
    List<Task> list = taskDao.getAll();
    return converter.convertAll(list);
  }

  @Override
  @Transactional
  public void delete(TaskDto taskDto) {
    taskDao.delete(taskDto.getId());
  }

  @Override
  public TaskDto update(TaskDto taskDto) {
    Task persistentEntity = taskDao.get(taskDto.getId());
    Task updatedEntity = converter.unConvert(taskDto);

    performUpdate(persistentEntity, updatedEntity);

    return converter.convert(persistentEntity);
  }

  private void performUpdate(Task persistentEntity, Task updatedEntity) {
    persistentEntity.setDescription(updatedEntity.getDescription());
    persistentEntity.setAppointedDeveloper(updatedEntity.getAppointedDeveloper());
  }
}
