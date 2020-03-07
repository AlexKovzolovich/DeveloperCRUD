package ua.epam.resthomework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.epam.resthomework.model.User;
import ua.epam.resthomework.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public User getUserById(@RequestParam("id") Long id) {
        return userRepository.getOne(id);
    }

    @GetMapping("/{fisrtName}/{lastName}")
    public User getUserByName(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return userRepository.findUserByFirstNameAndLastName(firstName, lastName);
    }

    @PostMapping
    public ResponseEntity saveUser(@RequestBody User user) {
        userRepository.save(user);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateUser(@RequestBody User user) {
        userRepository.update(user.getFirstName(), user.getLastName(), user.getEmail(), user.getId());
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteUser(@RequestBody User user) {
        userRepository.delete(user);
        return new ResponseEntity(HttpStatus.OK);
    }
}
