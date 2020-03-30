package ua.epam.service.serviceImpl;

import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import ua.epam.converter.TaskConverter;
import ua.epam.dto.TaskDto;
import ua.epam.model.Task;
import ua.epam.repository.hibernate.TaskDao;
import ua.epam.service.TaskService;

public class TaskServiceImpl implements TaskService {

  private TaskConverter converter;
  private TaskDao taskDao;

  @Override
  @Transactional
  public TaskDto getById(UUID id) {
    Task task = taskDao.get(id);
    TaskDto dto = converter.convert(task);
    return dto;
  }

  @Override
  @Transactional
  public List<TaskDto> getAll() {
    List<Task> list = taskDao.getAll();
    return converter.convertAll(list);
  }

  @Override
  @Transactional
  public void save(TaskDto taskDto) {
    Task task = converter.unConvert(taskDto);
    taskDao.save(task);
  }

  @Override
  @Transactional
  public void delete(TaskDto taskDto) {
    taskDao.delete(taskDto.getId());
  }

  @Override
  public void update(TaskDto taskDto) {

  }
}
