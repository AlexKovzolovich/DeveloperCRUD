package ua.epam.controller;

import java.util.List;
import java.util.UUID;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.epam.dto.DeveloperDto;
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
  DeveloperDto getDeveloper(UUID id) {
    return developerService.getById(id);
  }

  @GetMapping
  public @ResponseBody
  List<DeveloperDto> getDevelopers() {
    return developerService.getAll();
  }

  @PostMapping
  public ResponseEntity postDeveloper(@RequestBody DeveloperDto developerDto) {
    developerService.save(developerDto);
    return new ResponseEntity(HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity putDeveloper(@RequestBody DeveloperDto developerDto) {
    developerService.update(developerDto);
    return new ResponseEntity(HttpStatus.OK);
  }

  @DeleteMapping
  public ResponseEntity deleteDeveloper(@RequestBody DeveloperDto developerDto) {
    developerService.delete(developerDto);
    return new ResponseEntity(HttpStatus.OK);
  }
}
