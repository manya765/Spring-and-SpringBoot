package com.example.demo.service;

import com.example.demo.entity.journalentry;
import com.example.demo.entity.user;
import com.example.demo.repository.journalentryrepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class journalentryservice {
     @Autowired
    private journalentryrepo JournalEntryrepo;

    @Autowired
    private userentryservice userService;

    @Transactional
     public void saveEntry(journalentry JournalEntry, String userName){
         user User = userService.findByUserName(userName);
         JournalEntry.setDate(LocalDateTime.now());
        journalentry saved = JournalEntryrepo.save(JournalEntry);
        User.getJournalEntries().add(saved);
        userService.saveEntry(User);
     }

     public List<journalentry> getAll(){
         return JournalEntryrepo.findAll();
     }

     public Optional<journalentry> findById(ObjectId id){ // optional means it may or may not return an id
         return JournalEntryrepo.findById(id);
     }

     public void deleteById(ObjectId id,String userName){
         user User = userService.findByUserName(userName);
       //  User.getJournalEntries().removeIf(x -> x.getId().equals(id));
         userService.saveEntry(User);
         JournalEntryrepo.deleteById(id);
     }
}
// controller --> sevice --> repository