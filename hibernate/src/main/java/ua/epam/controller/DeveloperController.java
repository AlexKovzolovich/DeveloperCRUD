package ua.epam.controller;

import java.util.List;
import java.util.UUID;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ua.epam.dto.DeveloperDto;
import ua.epam.dto.TaskDto;
import ua.epam.service.DeveloperService;

@Log4j
@Controller
@RequestMapping("/api/v1/developers")
public class DeveloperController {

  private DeveloperService developerService;

  @Autowired
  public DeveloperController(DeveloperService developerService) {
    this.developerService = developerService;
  }

  @GetMapping(params = "id")
  public @ResponseBody
  DeveloperDto getDeveloper(String id) {
    UUID uuid = UUID.fromString(id);
    return developerService.getById(uuid);
  }

  @GetMapping
  public @ResponseBody
  List<DeveloperDto> getDevelopers() {
    return developerService.getAll();
  }

  @PostMapping
  public ResponseEntity<DeveloperDto> postDeveloper(@RequestBody DeveloperDto developerDto) {
    return new ResponseEntity<>(developerService.create(developerDto), HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity<DeveloperDto> putDeveloper(@RequestBody DeveloperDto developerDto) {
    return new ResponseEntity<>(developerService.update(developerDto), HttpStatus.OK);
  }

  @DeleteMapping
  @ResponseStatus(HttpStatus.OK)
  public void deleteDeveloper(@RequestBody DeveloperDto developerDto) {
    developerService.delete(developerDto);
  }

  @GetMapping("/{id}/tasks")
  public @ResponseBody
  List<TaskDto> getDeveloperTasks(@PathVariable String id) {
    UUID uuid = UUID.fromString(id);
    return developerService.getDevelopersTasksByDeveloperId(uuid);
  }

  @PostMapping(value = "/{id}/tasks")
  public ResponseEntity<TaskDto> addTask(@RequestBody TaskDto taskDto, @PathVariable String id) {
    return new ResponseEntity<>(
        developerService.assignTaskToDeveloperById(taskDto, UUID.fromString(id)),
        HttpStatus.CREATED);
  }
}
