package ua.epam.service;

import java.util.List;
import java.util.UUID;
import ua.epam.dto.DeveloperDto;
import ua.epam.dto.TaskDto;

public interface DeveloperService extends Service<DeveloperDto, UUID> {

  List<TaskDto> getDevelopersTasksByDeveloperId(UUID id);

  TaskDto assignTaskToDeveloperById(TaskDto taskDto, UUID developerId);
}
