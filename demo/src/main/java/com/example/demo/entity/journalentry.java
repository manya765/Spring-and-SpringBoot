package com.example.demo.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "journal_entries")
@Data // equivalent to @getter @setter @tostring @Requiredargsconstructor @EqualsandHashcode
@NoArgsConstructor // required to call it later on used to map values in spring
public class journalentry {
    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;


}