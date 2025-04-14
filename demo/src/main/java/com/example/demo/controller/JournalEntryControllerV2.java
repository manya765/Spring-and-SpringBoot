package com.example.demo.controller;

import com.example.demo.entity.journalentry;
import com.example.demo.service.journalentryservice;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {
      @Autowired
      private journalentryservice Journalentryservice;


    @GetMapping
    public List <journalentry> getAll(){ // localhost 8080:GET
        return Journalentryservice.getAll();
    }

    @PostMapping
    public journalentry createenrty(@RequestBody journalentry jentry){ // localhost : 8080/journal POST
       jentry.setDate(LocalDateTime.now());
        Journalentryservice.saveEntry(jentry);
       return jentry;
    }

    @GetMapping("id/{Myid}")
    public ResponseEntity<journalentry> getjournalentry(@PathVariable ObjectId Myid){
        Optional<journalentry> Journalentry = Journalentryservice.findById(Myid);
        if(Journalentry.isPresent()){
            return new ResponseEntity<>(Journalentry.get(), HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/id/{id}")
    public journalentry updatejournalentry(@PathVariable ObjectId id , @RequestBody journalentry newentry){
     journalentry old = Journalentryservice.findById(id).orElse(null);
     if(old!= null){
         old.setTitle(newentry.getTitle() != null && !newentry.getTitle().equals("") ? newentry.getTitle() : old.getTitle());
         old.setContent(newentry.getContent() != null && !newentry.getContent().equals("") ? newentry.getContent() : old.getContent());

     }
     Journalentryservice.saveEntry(old);
     return old;
    }
    @DeleteMapping("/id/{id}") // ? means wildcarddd pattern it is not necessary that you have to give any entity class
    public ResponseEntity<?> deletejournalentry(@PathVariable ObjectId id){
        Journalentryservice.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
