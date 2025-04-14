package com.example.demo.controller;

import com.example.demo.entity.journalentry;
import com.example.demo.entity.user;
import com.example.demo.service.journalentryservice;
import com.example.demo.service.userentryservice;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
     private userentryservice Userservice;

    @GetMapping
    public List<user> getAllUsers(){
        return Userservice.getAll();
    }
    @PostMapping
    private void createuser (@RequestBody user User){
        Userservice.saveEntry(User);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody user User , @PathVariable String userName){
       user userInDb = Userservice.findByUserName(userName);
       if(userInDb != null){
           userInDb.setUserName(User.getUserName());
           userInDb.setPassword(User.getPassword());
           Userservice.saveEntry(userInDb);
       }
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
