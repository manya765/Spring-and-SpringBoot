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

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {
    @Autowired
    private journalentryservice Journalentryservice;
    @Autowired
    private userentryservice userService;

    @GetMapping("/{userName}")
    public ResponseEntity<?> getallJournalentriesofuser(@PathVariable String userName) {
        user User = userService.findByUserName(userName);

        if (User == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        System.out.println(User.getUserName()); // optional: for debug
        List<journalentry> all = User.getJournalEntries();

        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }

        return new ResponseEntity<>("No journal entries found", HttpStatus.NOT_FOUND);
    }


    @PostMapping("{userName}")
    public ResponseEntity<journalentry> createenrty(@RequestBody journalentry jentry, @PathVariable String userName) { // localhost : 8080/journal POST
        try {

            Journalentryservice.saveEntry(jentry, userName);
            return new ResponseEntity<>(jentry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{Myid}")
    public ResponseEntity<journalentry> getjournalentry(@PathVariable ObjectId Myid) {
        Optional<journalentry> Journalentry = Journalentryservice.findById(Myid);
        if (Journalentry.isPresent()) {
            return new ResponseEntity<>(Journalentry.get(), HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //@PutMapping("/id/{id}")
    // public journalentry updatejournalentry(@PathVariable ObjectId id , @RequestBody journalentry newentry){
    //  journalentry old = Journalentryservice.findById(id).orElse(null);
    // if(old!= null){
    //     old.setTitle(newentry.getTitle() != null && !newentry.getTitle().equals("") ? newentry.getTitle() : old.getTitle());
    //   old.setContent(newentry.getContent() != null && !newentry.getContent().equals("") ? newentry.getContent() : old.getContent());


    // Journalentryservice.saveEntry(old, User);
    // return old;
    //}
    @DeleteMapping("id/{userName}/{Id}") // ? means wildcarddd pattern it is not necessary that you have to give any entity class
    public ResponseEntity<?> deletejournalentry(@PathVariable ObjectId Id,@PathVariable String userName) {
        Journalentryservice.deleteById(Id,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
