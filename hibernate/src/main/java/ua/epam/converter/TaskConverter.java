package ua.epam.converter;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.dto.TaskDto;
import ua.epam.model.Task;
import ua.epam.repository.hibernate.DeveloperDao;

@Component
public class TaskConverter implements Converter<Task, TaskDto> {

  private DeveloperDao developerDao;

  @Autowired
  public TaskConverter(DeveloperDao developerDao) {
    this.developerDao = developerDao;
  }

  @Override
  public TaskDto convert(Task task) {
    if (Objects.isNull(task)) {
      throw new IllegalArgumentException("Can`t convert task to dto, task is null");
    }

    TaskDto dto = new TaskDto();
    dto.setId(task.getId());
    dto.setDescription(task.getDescription());
    dto.setAppointedDeveloperId(task.getAppointedDeveloper().getId());

    return dto;
  }

  @Override
  public Task unConvert(TaskDto dto) {
    if (Objects.isNull(dto)) {
      throw new IllegalArgumentException("Can`t convert dto to task, dto is null");
    }

    Task task = new Task();
    task.setId(dto.getId());
    task.setDescription(dto.getDescription());
    task.setAppointedDeveloper(developerDao.get(dto.getAppointedDeveloperId()));

    return task;
  }
}
