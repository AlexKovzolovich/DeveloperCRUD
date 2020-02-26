package ua.epam.controller;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.epam.model.Developer;
import ua.epam.service.DeveloperService;
import ua.epam.service.serviceImpl.DeveloperServiceImpl;

import java.util.List;

@Log4j
@Controller
@RequestMapping("/developer")
public class DeveloperController {
    private DeveloperService developerService;
    private final Gson gson = new Gson();
    private final String redirectToGet = "redirect:/developer";

    @Autowired
    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @GetMapping
    @ResponseBody
    public String getDevelopers(@RequestParam(value = "id", required = false) Long id) {
        StringBuilder stringBuilder = new StringBuilder();
        if (id == null) {
            List<Developer> developers = developerService.getAll();
            stringBuilder.append(gson.toJson(developers));
        }
        else {
            Developer developer = developerService.getById(id);
            stringBuilder.append(gson.toJson(developer));
        }

        return stringBuilder.toString();
    }

    @PostMapping
    public String postDeveloper(HttpEntity<String> httpEntity) {
        String json = httpEntity.getBody();
        Developer developer = gson.fromJson(json, Developer.class);
        developerService.save(developer);
        return redirectToGet;
    }

    @PutMapping
    public String putMapping(HttpEntity<String> httpEntity) {
        String json = httpEntity.getBody();
        Developer developer = gson.fromJson(json, Developer.class);
        developerService.update(developer);
        return redirectToGet;
    }

    @DeleteMapping
    public String deleteDeveloper(HttpEntity<String> httpEntity) {
        String json = httpEntity.getBody();
        Developer developer = gson.fromJson(json, Developer.class);
        developerService.delete(developer);
        return redirectToGet;
    }
}
