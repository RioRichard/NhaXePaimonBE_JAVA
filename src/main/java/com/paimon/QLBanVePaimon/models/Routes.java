package com.paimon.QLBanVePaimon.models;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Routes {

    @Id
    private String id;

    @Field("from_Id")
    @DocumentReference
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)

    private Bases from;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String from_id;

    @Field("to_Id")
    @DocumentReference
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)

    private Bases to;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String to_id;

    private LocalDateTime departure;

    private LocalDateTime arrival;

    @Field("bus_id")
    @DocumentReference
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)

    private Buses bus;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String bus_id;

    @Field("staff_id")
    @DocumentReference
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)

    private Staffs staff;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String staff_id;

    @Field("extra_Staff_id")
    @DocumentReference
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)

    private Staffs extraStaff;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String extraStaffId;

    @Field("orders")
    private List<String> orders;

    private float price;

}
