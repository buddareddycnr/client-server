
package com.openmedical.server.rest;

import com.openmedical.entity.Customer;
import com.openmedical.server.service.UserService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;
    private final Bucket bucket;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        Bandwidth bandwidth = Bandwidth.classic(5, Refill.greedy(5, Duration.ofSeconds(1)));
        this.bucket = Bucket4j.builder().addLimit(bandwidth).build();
    }
    @GetMapping(value = "/retrieveAll",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> getAll(){
        long start_time = System.currentTimeMillis();
        List<Customer> users = userService.getUsers();
        long end_time = System.currentTimeMillis();
        long diff=end_time-start_time;
        if(diff>250 && diff<500){
            return ResponseEntity.ok(users);
        }else {
            try {
                Thread.sleep(500-diff);
            } catch (InterruptedException e) {
                ResponseEntity.internalServerError();
            }
        }
        return ResponseEntity.ok(users);
    }
}
