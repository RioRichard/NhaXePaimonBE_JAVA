package com.paimon.QLBanVePaimon.models;

import java.util.List;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @MongoId(targetType = FieldType.OBJECT_ID)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)

    private String password;

    private String name;

    private String email;

    private String phone;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @DocumentReference(lazy = true,collection = "orders" )
    private List<Orders> orders;
}
