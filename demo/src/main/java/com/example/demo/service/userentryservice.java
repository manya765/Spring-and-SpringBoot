package com.example.demo.service;

import com.example.demo.entity.journalentry;
import com.example.demo.entity.user;
import com.example.demo.repository.journalentryrepo;
import com.example.demo.repository.userRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class userentryservice {
     @Autowired
    private userRepository UserRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

     public void saveEntry(user User){
         User.setPassword(passwordEncoder.encode(User.getPassword()));
         User.setRoles(Arrays.asList("USER"));
         UserRepository.save(User);
     }

     public List<user> getAll(){
         return UserRepository.findAll();
     }

     public Optional<user> findById(ObjectId id){ // optional means it may or may not return an id
         return UserRepository.findById(id);
     }

     public void deleteById(ObjectId id){
        UserRepository.deleteById(id);

     }
     public user findByUserName(String userName){
         return UserRepository.findUserByUserName(userName);
     }
}
// controller --> sevice --> repository