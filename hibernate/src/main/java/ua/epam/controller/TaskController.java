package ua.epam.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ua.epam.dto.TaskDto;
import ua.epam.service.TaskService;

@Controller
@RequestMapping("/api/v1/tasks")
public class TaskController {

  private TaskService taskService;

  @Autowired
  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @GetMapping(params = "id")
  public @ResponseBody
  TaskDto getTask(String id) {
    UUID uuid = UUID.fromString(id);
    return taskService.getById(uuid);
  }

  @GetMapping
  public @ResponseBody
  List<TaskDto> getTasks() {
    return taskService.getAll();
  }

  @PostMapping
  public ResponseEntity<TaskDto> postTask(@RequestBody TaskDto taskDto) {
    return new ResponseEntity<>(taskService.create(taskDto), HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity<TaskDto> putTask(@RequestBody TaskDto taskDto) {
    return new ResponseEntity<>(taskService.update(taskDto), HttpStatus.OK);
  }

  @DeleteMapping
  @ResponseStatus(HttpStatus.OK)
  public void deleteTask(@RequestBody TaskDto taskDto) {
    taskService.delete(taskDto);
  }
}
