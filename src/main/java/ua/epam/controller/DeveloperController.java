package ua.epam.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.epam.model.Developer;
import ua.epam.service.DeveloperService;

import java.util.List;

@Log4j
@Controller
@RequestMapping("/developer")
public class DeveloperController {
    private DeveloperService developerService;

    @Autowired
    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @GetMapping(params = "id")
    public @ResponseBody Developer getDeveloper(Long id) {
        return developerService.getById(id);
    }

    @GetMapping
    public @ResponseBody List<Developer> getDevelopers() {
        return developerService.getAll();
    }

    @PostMapping
    public ResponseEntity postDeveloper(@RequestBody Developer developer) {
        developerService.save(developer);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity putMapping(@RequestBody Developer developer) {
        developerService.update(developer);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteDeveloper(@RequestBody Developer developer) {
        developerService.delete(developer);
        return new ResponseEntity(HttpStatus.OK);
    }
}
