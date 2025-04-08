package com.example.demo.controller;
import com.example.demo.entity.journalentry;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    private Map<Long,journalentry> journalEntries= new HashMap<>();
    @GetMapping
    public List <journalentry> getAll(){ // localhost 8080:GET
        return new ArrayList<>(journalEntries.values());
    }
    @PostMapping
    public boolean createenrty(@RequestBody journalentry jentry){ // localhost : 8080/journal POST
       journalEntries.put(jentry.getId(), jentry);
       return true;
    }
}
