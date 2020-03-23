package com.epam.springcloud.notification;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class Notification {

  String user;
  Notifier notifyBy = Notifier.EMAIL;

  public Notification(String user) {
    this.user = user;
  }

  enum Notifier {
    EMAIL
  }
}
