package com.epam.springcloud.notification;

import java.util.HashSet;
import java.util.Set;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class NotificationController {

  private final Set<Notification> notifications = new HashSet<>();

  @PostMapping("/{user}")
  public Notification notify(@PathVariable String user) {
    Notification notification = new Notification(user);
    notifications.add(notification);
    return notification;
  }

  @GetMapping
  public Set<Notification> getNotifications() {
    return notifications;
  }
}
