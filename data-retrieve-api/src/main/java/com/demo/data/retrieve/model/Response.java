package com.demo.data.retrieve.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class Response {

    @JsonProperty("totalPages")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public int totalPages;
    @JsonProperty("page")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public int page;
    @JsonProperty("size")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public int size;
    @JsonProperty("transactions")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<Transaction> transactions;

    @JsonProperty("error")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public ErrorResponseBean error;

    public Response(int totalPages, int page, int size, List<Transaction> transactions){
        super();
        this.totalPages = totalPages;
        this.page = page;
        this.size = size;
        this.transactions = transactions;
    }

    public Response(ErrorResponseBean error){
        super();
        this.error = error;
    }
}