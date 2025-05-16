package com.example.demo.repository;

import com.example.demo.entity.journalentry;
import com.example.demo.entity.user;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface userRepository extends MongoRepository<user, ObjectId> {
    user findUserByUserName(String userName);
}
