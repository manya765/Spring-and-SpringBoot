package com.example.demo.controller;

import com.example.demo.entity.journalentry;
import com.example.demo.entity.user;
import com.example.demo.service.journalentryservice;
import com.example.demo.service.userentryservice;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
     private userentryservice Userservice;


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody user User ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName= authentication.getName();
        user userInDb = Userservice.findByUserName(userName);
        userInDb.setUserName(User.getUserName());
        userInDb.setPassword(User.getPassword());
        Userservice.saveEntry(userInDb);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
