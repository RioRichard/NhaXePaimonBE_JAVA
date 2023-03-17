package com.paimon.QLBanVePaimon.models;

import java.util.List;

import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @MongoId(targetType = FieldType.OBJECT_ID)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    @Field("seats")
    @DocumentReference
    @JsonIgnoreProperties(value = "seat", allowSetters = false)
    private List<Seat> seat;

    @ReadOnlyProperty
    @DocumentReference(lookup = "{'routes':?#{#self.id} }")
    // @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonIgnoreProperties(value = "route", allowSetters = false)

    private Routes route;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<String> seatId;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String routeId;

    @Field("user_id")
    @DocumentReference(lookup = "{ 'userId' : ?#{#target} }")
    private String userId;

    // @Transient
    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    // private String userId;

    private String status;
    private String paymentInfo;
}
