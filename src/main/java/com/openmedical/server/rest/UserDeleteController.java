package com.openmedical.server.rest;

import com.openmedical.server.service.UserService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequestMapping("/api/user")
public class UserDeleteController {
    private UserService userService;
    private final Bucket bucket;

    @Autowired
    public UserDeleteController(UserService userService) {
        this.userService = userService;
        Bandwidth bandwidth = Bandwidth.classic(2, Refill.greedy(2, Duration.ofSeconds(1)));
        this.bucket = Bucket4j.builder().addLimit(bandwidth).build();
    }
    @DeleteMapping(value = "/delete")
    public ResponseEntity deleteUser(@RequestParam("emailId") String emailId){
        long start_time = System.currentTimeMillis();
        userService.deleteUser(emailId);
        long end_time = System.currentTimeMillis();
        long diff = end_time-start_time;
        if(diff>2000){

        }
        if(diff>=150 && diff<=300){
            return new ResponseEntity(emailId, HttpStatus.ACCEPTED);
        }else {
            try {
                Thread.sleep(300-diff);
            } catch (InterruptedException e) {
                ResponseEntity.internalServerError();
            }
        }
        return new ResponseEntity(emailId, HttpStatus.ACCEPTED);
    }
}

