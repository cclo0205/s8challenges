package com.demo.data.retrieve.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value={"timestamp"})
public class Transaction {
    @Id
    private String id;
    @JsonProperty("uId")
    @Indexed
    @NonNull
    private String uId;
    @JsonProperty("money")
    private Money money;
    @JsonProperty("IBAN")
    @Indexed
    @NonNull
    private String IBAN;
    @JsonProperty("date")
    private String date;
    @JsonProperty("timestamp")
    private long timestamp;
    @JsonProperty("description")
    private String description;
}
