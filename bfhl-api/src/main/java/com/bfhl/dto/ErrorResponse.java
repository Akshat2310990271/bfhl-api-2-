package com.bfhl.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {

    @JsonProperty("is_success")
    private boolean isSuccess;

    @JsonProperty("error")
    private String error;

    public ErrorResponse(String error) {
        this.isSuccess = false;
        this.error = error;
    }

    public boolean isSuccess() { return isSuccess; }
    public String getError() { return error; }
}
