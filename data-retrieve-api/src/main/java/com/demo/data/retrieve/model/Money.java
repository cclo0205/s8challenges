package com.demo.data.retrieve.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Money {
    @JsonProperty("amount")
    private int amount;
    @JsonProperty("currency")
    private String currency;
}
