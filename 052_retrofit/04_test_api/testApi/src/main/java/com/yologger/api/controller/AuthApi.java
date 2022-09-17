package com.yologger.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthApi {

    @PostMapping("/login")
    ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO requestDTO) {

        LoginResponseDTO response = LoginResponseDTO.builder()
                .userId(1L)
                .accessToken("dummy_access_token")
                .refreshToken("dummy_refresh_token")
                .build();
        return new ResponseEntity<LoginResponseDTO>(response, HttpStatus.OK);
    }

//    @PostMapping("/login")
//    ResponseEntity<ErrorResponseDTO> login(@RequestBody LoginRequestDTO requestDTO) {
//
//        ErrorResponseDTO response = ErrorResponseDTO.builder()
//                .code("AUTH_00")
//                .message("Invalid email")
//                .build();
//
//        return new ResponseEntity<ErrorResponseDTO>(response, HttpStatus.BAD_REQUEST);
//    }
}
