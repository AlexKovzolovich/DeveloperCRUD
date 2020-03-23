package com.pigorv.springcloud.orders.feignService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "notifications")
public interface NotificationClient {

  @PostMapping("/{user}")
  String notify(@PathVariable String user);
}
