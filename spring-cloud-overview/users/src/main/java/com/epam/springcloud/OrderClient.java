package com.epam.springcloud;

import java.util.Arrays;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "orders", fallback = OrderClient.OrderClientImpl.class)
@Primary
public interface OrderClient {

  @GetMapping("/{name}")
  List<String> getProductsForUser(@PathVariable String name);

  @Component
  class OrderClientImpl implements OrderClient {

    @Override
    public List<String> getProductsForUser(String name) {
      return Arrays.asList("nothing");
    }
  }
}
