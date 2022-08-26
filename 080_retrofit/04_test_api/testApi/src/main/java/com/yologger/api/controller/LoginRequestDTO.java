package com.yologger.api.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {
    @JsonProperty(value = "email") private String email;
    @JsonProperty(value = "password") private String password;
}
