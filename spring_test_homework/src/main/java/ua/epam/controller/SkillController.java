package ua.epam.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.epam.dto.SkillDto;
import ua.epam.service.SkillService;


@Controller
@RequestMapping("/api/v1/skill")
public class SkillController {

  private SkillService skillService;

  @Autowired
  public SkillController(SkillService skillService) {
    this.skillService = skillService;
  }

  @GetMapping(params = "id")
  public @ResponseBody
  SkillDto getSkill(Long id) {
    return skillService.getById(id);
  }

  @GetMapping
  public @ResponseBody
  List<SkillDto> getSkills() {
    return skillService.getAll();
  }

  @PostMapping()
  public ResponseEntity postSkill(@RequestBody SkillDto skillDto) {
    skillService.save(skillDto);
    return new ResponseEntity(HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity putSkill(@RequestBody SkillDto skillDto) {
    skillService.update(skillDto);
    return new ResponseEntity(HttpStatus.OK);
  }

  @DeleteMapping
  public ResponseEntity deleteSkill(@RequestBody SkillDto skillDto) {
    skillService.delete(skillDto);
    return new ResponseEntity(HttpStatus.OK);
  }
}
