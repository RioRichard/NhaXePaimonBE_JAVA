package com.paimon.QLBanVePaimon.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.context.annotation.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "manager")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manager {
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)

    private String pass;

    private String email;

    private String phone;



    private String role;

}
