package com.ferovac.backend.dto;

import lombok.Getter;

@Getter
public class ApiResponseWrapper<T> {
    private final String status;
    private final String message;
    private final T response;

    public ApiResponseWrapper(String status, String message, T response) {
        this.status = status;
        this.message = message;
        this.response = response;
    }

}
