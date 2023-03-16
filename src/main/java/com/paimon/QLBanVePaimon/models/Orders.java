package com.paimon.QLBanVePaimon.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    @Field("user_id")
    @DBRef
    // private Users users;

    // @Transient
    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String userId;

    // @Field("promote_id")
    // @DocumentReference
    // private Promote promote;

    // @Transient
    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    // String promoteId;

    @Field("seats")
    @DocumentReference
    private List<Seat> seat;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    List<String> seatId;

    @ReadOnlyProperty
    @DocumentReference(lookup="{'routes':?#{#self.id} }"  )
    private Routes route;

    private String status;

    private String paymentInfo;
}
