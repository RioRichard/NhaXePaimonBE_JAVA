package com.paimon.QLBanVePaimon.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "manager")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manager {
    @Id
    private String id;

    private String username;

    private String pass;

    private String email;

    private String phone;

    private String role;

}
