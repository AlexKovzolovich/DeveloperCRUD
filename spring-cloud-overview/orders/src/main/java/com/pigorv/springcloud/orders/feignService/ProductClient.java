package com.pigorv.springcloud.orders.feignService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(value = "products")
public interface ProductClient {

  @PutMapping("/{productName}")
  String removeOneProduct(@PathVariable String productName);
}
