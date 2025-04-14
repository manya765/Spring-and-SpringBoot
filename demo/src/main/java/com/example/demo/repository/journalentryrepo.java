package com.example.demo.repository;

import com.example.demo.entity.journalentry;
import org.bson.types.ObjectId;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface journalentryrepo extends MongoRepository<journalentry, ObjectId> {
}
