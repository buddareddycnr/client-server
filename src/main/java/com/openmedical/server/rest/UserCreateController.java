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
    public ResponseEntity<Customer> createUser(@RequestBody Customer customer){
        long start_time = System.currentTimeMillis();
        Customer customer1 = userService.saveUser(customer);
        long end_time = System.currentTimeMillis();
        long diff = end_time-start_time;
        if(diff>=35 && diff<=70){
            return ResponseEntity.ok(customer1);
        }else {
            try {
                Thread.sleep(70-diff);
            } catch (InterruptedException e) {
                ResponseEntity.internalServerError();
            }
        }
        return ResponseEntity.ok(customer1);
    }
}
