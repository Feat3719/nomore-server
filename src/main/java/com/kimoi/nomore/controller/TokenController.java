package com.kimoi.nomore.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kimoi.nomore.config.jwt.JwtFactory;
import com.kimoi.nomore.config.jwt.JwtProperties;
import com.kimoi.nomore.domain.RefreshToken;
import com.kimoi.nomore.domain.User;
import com.kimoi.nomore.dto.jwt.CreateAccessTokenRequest;
import com.kimoi.nomore.dto.jwt.CreateAccessTokenResponse;
import com.kimoi.nomore.repository.UserRepository;
import com.kimoi.nomore.repository.jwt.RefreshTokenRepository;
import com.kimoi.nomore.service.TokenService;

import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequiredArgsConstructor
@RestController
public class TokenController {
    
    private final TokenService tokenService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    JwtProperties jwtProperties;

    @PostMapping("/api/token")
    public ResponseEntity<CreateAccessTokenResponse> createNewAccessToken
            (@RequestBody CreateAccessTokenRequest request) {
        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());
        System.out.println(newAccessToken);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateAccessTokenResponse(newAccessToken));
    }

    
}
