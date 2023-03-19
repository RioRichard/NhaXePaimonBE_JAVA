package com.paimon.QLBanVePaimon.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// import nonapi.io.github.classgraph.json.Id;

@Document(collection = "buses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Buses {
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    private String bus_number;

    private String type;
    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int numberSeat;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @DocumentReference
    private List<Seat> seats;
}
