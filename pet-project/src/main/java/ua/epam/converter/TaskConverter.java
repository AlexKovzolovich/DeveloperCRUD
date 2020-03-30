package ua.epam.converter;

import java.util.Objects;
import org.springframework.stereotype.Component;
import ua.epam.dto.TaskDto;
import ua.epam.model.Task;

@Component
public class TaskConverter implements Converter<Task, TaskDto> {

  @Override
  public TaskDto convert(Task task) {
    if (Objects.isNull(task)) {
      throw new IllegalArgumentException("Can`t convert task to dto, task is null");
    }
    
    TaskDto dto = new TaskDto();
    dto.setId(task.getId());
    dto.setDescription(task.getDescription());
    
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
    
    return task;
  }
}
