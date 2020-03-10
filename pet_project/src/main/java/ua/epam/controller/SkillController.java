package ua.epam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.epam.model.Skill;
import ua.epam.service.SkillService;

import java.util.List;


@Controller
@RequestMapping("/skill")
public class SkillController {
    private SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping(params = "id")
    public @ResponseBody Skill getSkill(Long id) {
        return skillService.getById(id);
    }

    @GetMapping
    public @ResponseBody List<Skill> getSkills() {
        return skillService.getAll();
    }

    @PostMapping
    public ResponseEntity postSkill(@RequestBody Skill skill) {
        skillService.save(skill);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity putSkill(@RequestBody Skill skill) {
        skillService.update(skill);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteSkill(@RequestBody Skill skill) {
        skillService.delete(skill);
        return new ResponseEntity(HttpStatus.OK);
    }
}
