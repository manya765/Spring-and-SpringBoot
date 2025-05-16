package com.example.demo.controller;

import com.example.demo.entity.user;
import com.example.demo.service.userentryservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")

    public class PublicController {

    @Autowired  private userentryservice Userservice;
        @GetMapping("/health-check")
        public String healthcheck(){
            return "ok";
        }
    @PostMapping("/create-user")
    private void createuser (@RequestBody user User){
        Userservice.saveEntry(User);
    }
    }

