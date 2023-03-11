package com.paimon.QLBanVePaimon.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
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
    private String userid;

    @Field("promote_id")
    @DocumentReference
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Promote promote;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ReadOnlyProperty
    @DocumentReference(lookup="{'orders':?#{#self._id} }")
    private Routes route;

    private String status;

    private String paymentInfo;
}
