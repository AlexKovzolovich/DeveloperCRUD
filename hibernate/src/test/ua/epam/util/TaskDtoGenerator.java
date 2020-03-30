package ua.epam.util;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import ua.epam.dto.TaskDto;

public class TaskDtoGenerator {

  public static TaskDto createTaskDto() {
    TaskDto taskDto = new TaskDto();
    taskDto.setId(UUID.randomUUID());
    taskDto.setDescription("test task");
    taskDto.setAppointedDeveloperId(UUID.randomUUID());

    return taskDto;
  }

  public static List<TaskDto> generateTaskDtoList(int count) {
    return IntStream.range(0, count)
        .mapToObj(counter -> createTaskDto())
        .collect(Collectors.toList());
  }
}
