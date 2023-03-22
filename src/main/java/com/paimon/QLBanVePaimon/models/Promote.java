package com.paimon.QLBanVePaimon.models;

import java.time.LocalDateTime;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;

@Document(collection = "promote")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Promote {
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    private LocalDateTime from;
    private LocalDateTime to;
    private String type;
    private int amount;

//     @DocumentReference(lookup="{'promote_id':?#{#self._id} }")
//     @ReadOnlyProperty
//     private List<String> order;
// }
}
