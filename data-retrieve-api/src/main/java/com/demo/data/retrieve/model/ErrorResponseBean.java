package com.demo.data.retrieve.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ErrorResponseBean {
    @JsonProperty("code")
    private int code;

    @JsonProperty("message")
    private String message;

    public ErrorResponseBean(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }
}