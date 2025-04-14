package com.example.demo.service;

import com.example.demo.entity.journalentry;
import com.example.demo.repository.journalentryrepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class journalentryservice {
     @Autowired
    private journalentryrepo JournalEntryrepo;

     public void saveEntry(journalentry JournalEntry){
         JournalEntryrepo.save(JournalEntry);
     }

     public List<journalentry> getAll(){
         return JournalEntryrepo.findAll();
     }

     public Optional<journalentry> findById(ObjectId id){ // optional means it may or may not return an id
         return JournalEntryrepo.findById(id);
     }

     public void deleteById(ObjectId id){
        JournalEntryrepo.deleteById(id);

     }
}
// controller --> sevice --> repository