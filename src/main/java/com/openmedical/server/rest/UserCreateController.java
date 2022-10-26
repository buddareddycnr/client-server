package com.openmedical.server.rest;

import com.openmedical.server.service.UserService;
import com.openmedical.entity.Customer;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequestMapping("/api/user")
public class UserCreateController {
    private UserService userService;
    private final Bucket bucket;
    @Autowired
    public UserCreateController(UserService userService) {
        this.userService = userService;
        Bandwidth bandwidth = Bandwidth.classic(10, Refill.greedy(10, Duration.ofSeconds(1)));
        this.bucket = Bucket4j.builder().addLimit(bandwidth).build();
    }
    @PostMapping(value = "/save",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(timeout = 71,timeoutString = "Request Timeout")
    public ResponseEntity<Customer> createUser(@RequestBody Customer customer){
        Customer customer1 = userService.saveUser(customer);
        return ResponseEntity.ok(customer1);
    }
}
