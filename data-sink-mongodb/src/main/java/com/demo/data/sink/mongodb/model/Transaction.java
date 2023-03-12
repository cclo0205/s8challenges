package com.demo.data.sink.mongodb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Document
@Getter
@Setter
@NoArgsConstructor
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

    public void genTimestamp() {
        try {
            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(this.date);
            this.timestamp = date.getTime();
        } catch (ParseException e) {
            //parse error, ignore timestamp
        }
    }
}
