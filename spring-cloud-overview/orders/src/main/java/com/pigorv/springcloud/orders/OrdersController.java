package com.pigorv.springcloud.orders;

import static java.util.stream.Collectors.toList;

import com.pigorv.springcloud.orders.feignService.NotificationClient;
import com.pigorv.springcloud.orders.feignService.ProductClient;
import com.pigorv.springcloud.orders.feignService.UserClient;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class OrdersController {

  @Autowired
  UserClient userClient;
  @Autowired
  ProductClient productClient;
  @Autowired
  NotificationClient notificationClient;
  private List<Order> orderList = new ArrayList<>();

  @GetMapping
  public String health() {
    return "OK";
  }

  @PostMapping
  public ResponseEntity<Order> createNewOrder(@RequestBody Order order) {

    String user = userClient.getUser(order.getUserName());

    String product = productClient.removeOneProduct(order.getProduct());

    System.out.println(user + " : " + product);

    notificationClient.notify(order.getUserName());
    orderList.add(order);

    return new ResponseEntity<>(order, HttpStatus.CREATED);
  }

  @GetMapping("/users/{userName}")
  public List<String> getProductsForUser(@PathVariable String userName) {
    return orderList.stream()
        .filter(order -> userName.equals(order.getUserName()))
        .map(Order::getProduct)
        .collect(toList());
  }


}
