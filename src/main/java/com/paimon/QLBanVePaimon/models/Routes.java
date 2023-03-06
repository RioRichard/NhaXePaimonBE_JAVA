package com.paimon.QLBanVePaimon.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Routes {
    
    @Id
    private String id;

    @Field("from_Id")
    @DocumentReference
    private String from_id;

    // @Field("from_Id")
    @DocumentReference

    private Bases from;
}
