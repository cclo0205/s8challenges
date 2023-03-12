package com.demo.data.collector.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Request {

    @JsonProperty("uId")
    private String uId;

    @JsonProperty("money")
    private Money money;

    @JsonProperty("IBAN")
    private String IBAN;

    @JsonProperty("date")
    private String date;

    @JsonProperty("description")
    private String description;
}
