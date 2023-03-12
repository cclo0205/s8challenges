package com.demo.data.collector.model;


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
public class Response {

    @JsonProperty("success")
    private Message success;

    @JsonProperty("error")
    private ErrorMessage error;

    public Response(int code, String msg) {
        super();
        if (code >= 400) {
            this.error = new ErrorMessage(code, msg);
        } else {
            this.success = new Message(msg);
        }
    }
}
