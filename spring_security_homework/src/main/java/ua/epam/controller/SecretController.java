package ua.epam.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/secret")
public class SecretController {

  @Secured("ROLE_ADMIN")
  @GetMapping
  public String getSecretPage() {
    return "secret";
  }
}
