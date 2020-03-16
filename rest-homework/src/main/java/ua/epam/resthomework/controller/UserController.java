package ua.epam.resthomework.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.epam.resthomework.model.User;
import ua.epam.resthomework.repository.UserRepository;

@RestController
public class UserController {

  private UserRepository userRepository;

  @Autowired
  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("/user/{id}")
  public User getUserById(@PathVariable("id") Long id) {
    return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
  }

  @GetMapping("/user")
  public List<User> getUsers() {
    return userRepository.findAll();
  }

  @GetMapping("/user/{firstName}/{lastName}")
  public User getUserByName(@PathVariable("firstName") String firstName,
      @PathVariable("lastName") String lastName) {
    return userRepository.findUserByFirstNameAndLastName(firstName, lastName);
  }

  @PostMapping("/user")
  public ResponseEntity saveUser(@RequestBody User user) {
    userRepository.save(user);
    return new ResponseEntity(HttpStatus.OK);
  }

  @DeleteMapping("/user")
  public ResponseEntity deleteUser(@RequestBody User user) {
    userRepository.delete(user);
    return new ResponseEntity(HttpStatus.OK);
  }
}
