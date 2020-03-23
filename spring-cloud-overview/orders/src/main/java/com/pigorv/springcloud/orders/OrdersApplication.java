package com.pigorv.springcloud.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
public class OrdersApplication {

  public static void main(String[] args) {
    SpringApplication.run(OrdersApplication.class, args);
  }

  @Bean
  protected RestTemplate getRestTemplate() {
    return new RestTemplate();
  }
}
