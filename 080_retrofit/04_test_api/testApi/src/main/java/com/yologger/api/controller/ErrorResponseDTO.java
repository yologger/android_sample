package com.yologger.api.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResponseDTO {
    @JsonProperty("code") private String code;
    @JsonProperty("message") private String message;
}
