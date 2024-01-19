package com.kimoi.nomore.controller;

import org.springframework.web.bind.annotation.RestController;

import com.kimoi.nomore.dto.jwt.CreateAccessTokenRequest;
import com.kimoi.nomore.dto.jwt.CreateAccessTokenResponse;
import com.kimoi.nomore.service.TokenService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequiredArgsConstructor
@RestController
public class TokenController {
    
    private final TokenService tokenService;

    @PostMapping("/api/token")
    public ResponseEntity<CreateAccessTokenResponse> createNewAccessToken
            (@RequestBody CreateAccessTokenRequest request) {
        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateAccessTokenResponse(newAccessToken));
    }
    
    
}
