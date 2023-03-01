package com.paimon.QLBanVePaimon.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "bases")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bases {
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    private String name;

    private String address;

}
