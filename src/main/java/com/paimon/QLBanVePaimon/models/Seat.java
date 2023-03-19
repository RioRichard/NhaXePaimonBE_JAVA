package com.paimon.QLBanVePaimon.models;

import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("seats")
public class Seat {
    
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    private String name;


    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String status;

    
    
    
}
