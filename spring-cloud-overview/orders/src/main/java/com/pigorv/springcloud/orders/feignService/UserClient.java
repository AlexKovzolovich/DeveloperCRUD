package com.pigorv.springcloud.orders.feignService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "users")
public interface UserClient {

  @GetMapping("/{userName}")
  String getUser(@PathVariable String userName);

}
