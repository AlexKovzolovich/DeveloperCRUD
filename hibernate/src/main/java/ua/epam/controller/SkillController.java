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
import ua.epam.dto.SkillDto;
import ua.epam.service.SkillService;


@Controller
@RequestMapping("/api/v1/skills")
public class SkillController {

  private SkillService skillService;

  @Autowired
  public SkillController(SkillService skillService) {
    this.skillService = skillService;
  }

  @GetMapping(params = "id")
  public @ResponseBody
  SkillDto getSkill(String id) {
    UUID uuid = UUID.fromString(id);
    return skillService.getById(uuid);
  }

  @GetMapping
  public @ResponseBody
  List<SkillDto> getSkills() {
    return skillService.getAll();
  }

  @PostMapping()
  public ResponseEntity<SkillDto> postSkill(@RequestBody SkillDto skillDto) {
    return new ResponseEntity<>(skillService.create(skillDto), HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity<SkillDto> putSkill(@RequestBody SkillDto skillDto) {
    return new ResponseEntity<>(skillService.update(skillDto), HttpStatus.OK);
  }

  @DeleteMapping
  @ResponseStatus(HttpStatus.OK)
  public void deleteSkill(@RequestBody SkillDto skillDto) {
    skillService.delete(skillDto);
  }
}
