package ua.epam.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.epam.model.User;
import ua.epam.service.UserService;

@RestController
public class UserController {

  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/user/{id}")
  public User getUserById(@PathVariable("id") Long id) {
    return userService.getUserById(id);
  }

  @GetMapping("/user")
  public List<User> getUsers() {
    return userService.getUsers();
  }

}
