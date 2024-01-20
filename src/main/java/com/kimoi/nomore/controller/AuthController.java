package com.kimoi.nomore.controller;

import org.springframework.web.bind.annotation.RestController;

import com.kimoi.nomore.dto.auth.UserSignInRequest;
import com.kimoi.nomore.dto.jwt.CreateAccessTokenRequest;
import com.kimoi.nomore.dto.jwt.CreateAccessTokenResponse;
import com.kimoi.nomore.dto.jwt.CreateTokensResponse;
import com.kimoi.nomore.service.AuthService;
import com.kimoi.nomore.service.TokenService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    
    private final AuthService authService;
    private final TokenService tokenService;
    
    // AccessToken 발급 : RefreshToken 을 보유하고 있고 AccessToken 이 없는 경우
    @PostMapping("/api/token")
    public ResponseEntity<CreateAccessTokenResponse> createNewAccessToken
            (@RequestBody CreateAccessTokenRequest request) {
        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());
        return ResponseEntity.status(HttpStatus.CREATED).body(new CreateAccessTokenResponse(newAccessToken));
    }

    // 로그인
    @PostMapping("/api/signin")
    public ResponseEntity<?> userSignIn(@RequestBody UserSignInRequest userSignInRequest, HttpServletResponse response) {
        
        ResponseCookie cookie = ResponseCookie.from("refreshToken", authService.signIn(userSignInRequest).getAccessToken() )
                .path("/")
                .httpOnly(true)
                // .secure(true) // HTTPS 환경에서만 사용
                .maxAge(24 * 60 * 60) // 쿠키 유효 시간 (예: 1일)
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signIn(userSignInRequest).getAccessToken());
    }
    
    

}
