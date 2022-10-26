
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class FetchUserController {

    private UserService userService;
    private final Bucket bucket;
    @Autowired
    public FetchUserController(UserService userService) {
        this.userService = userService;
        Bandwidth bandwidth = Bandwidth.classic(5, Refill.greedy(5, Duration.ofSeconds(1)));
        this.bucket = Bucket4j.builder().addLimit(bandwidth).build();
    }
    @GetMapping(value = "/fetchAll",produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(timeout = 501)
    public ResponseEntity<List<Customer>> getAll(){
        List<Customer> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }
}
