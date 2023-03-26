package com.paimon.QLBanVePaimon.models;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    // @JsonIgnoreProperties(value = "seat", allowSetters = false)
    private List<Seat> seat;

    @DocumentReference
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Routes route;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<String> seatId;

    @Transient
    private String routeId;

    @Field("userId")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ObjectId user;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String userId;
    private String status;
    private String paymentInfo;
    
    @JsonIgnore
    public void setRoute(Routes route) {
        this.route = route;
    }

    @JsonIgnore
    public void setSeat(List<Seat> seat) {
        this.seat = seat;
    }

    @JsonIgnore
    public ObjectId getUser() {
        return user;
    }
    
    
}
