package com.bonifacio.medic_app.responses;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
@JsonPropertyOrder({"message", "status", "data"})
public class Response<T> {
    private final String message;
    private final T data;
    private final boolean status;
}
