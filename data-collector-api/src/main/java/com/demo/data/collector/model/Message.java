package com.demo.data.collector.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class Message {
    @NotNull
    @JsonProperty("message")
    private String message;

    public Message(String message) {
        super();
        this.message = message;
    }
}
