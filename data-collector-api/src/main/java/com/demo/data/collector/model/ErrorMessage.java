package com.demo.data.collector.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ErrorMessage {
    @JsonProperty("code")
    private int code;

    @NotNull
    @JsonProperty("message")
    private String message;

    public ErrorMessage(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }
}
