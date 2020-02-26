package ua.epam.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.epam.model.Skill;
import ua.epam.service.SkillService;
import ua.epam.service.serviceImpl.SkillServiceImpl;

import java.util.List;


@Controller
@RequestMapping("/api/v1/skill")
public class SkillController {
    private SkillService skillService;
    private final Gson gson = new Gson();
    private final String redirectToGet ="redirect:/api/vi/skill";

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    @ResponseBody
    public String getSkills(@RequestParam(value = "id", required = false) Long id) {
        StringBuilder stringBuilder = new StringBuilder();
        if (id == null) {
            List<Skill> skills = skillService.getAll();
            System.out.println(skills);
            stringBuilder.append(gson.toJson(skills));
        }
        else {
            Skill skill = skillService.getById(id);
            System.out.println("id: " + id + " skill: " + skill);
            stringBuilder.append(skill);
        }

        return stringBuilder.toString();
    }

    @PostMapping
    public String postSkill(HttpEntity<String> httpEntity) {
        String json = httpEntity.getBody();
        Skill skill = gson.fromJson(json, Skill.class);
        skillService.save(skill);
        return redirectToGet;
    }

    @PutMapping
    public String putSkill(HttpEntity<String> httpEntity) {
        String json = httpEntity.getBody();
        Skill skill = gson.fromJson(json, Skill.class);
        skillService.update(skill);
        return redirectToGet;
    }

    @DeleteMapping
    public String deleteSkill(HttpEntity<String> httpEntity) {
        String json = httpEntity.getBody();
        Skill skill = gson.fromJson(json, Skill.class);
        skillService.delete(skill);
        return redirectToGet;
    }
}
