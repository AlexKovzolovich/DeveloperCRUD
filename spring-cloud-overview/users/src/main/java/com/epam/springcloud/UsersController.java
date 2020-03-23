package com.epam.springcloud;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class UsersController {

  @Autowired
  OrderClient orderClient;
  private Map<String, User> registeredUsers = new HashMap<>();

  @GetMapping
  public String health() {
    return "OK";
  }

  @PostMapping
  public User createUser() {
    User user = new User(RandomStringUtils.randomAlphabetic(13));
    registeredUsers.put(user.getName(), user);

    return user;
  }

  @GetMapping("/{userName}")
  public ResponseEntity<User> getUser(@PathVariable String userName) {
    User user = registeredUsers.get(userName);

    if (user == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @GetMapping("/{userName}/products")
  public List getProductsByUser(@PathVariable String userName) {
    return orderClient.getProductsForUser(userName);
  }

  private List<String> getDefaultProducts(String value) {
    return Arrays.asList("one", "two");
  }
}
