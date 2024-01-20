package com.kimoi.nomore.controller;

import org.springframework.web.bind.annotation.RestController;

import com.kimoi.nomore.dto.auth.UserSignInRequest;
import com.kimoi.nomore.dto.jwt.CreateAccessTokenRequest;
import com.kimoi.nomore.dto.jwt.CreateAccessTokenResponse;
import com.kimoi.nomore.dto.user.AddUserRequest;
import com.kimoi.nomore.dto.user.IsUserIdAvailableRequest;
import com.kimoi.nomore.service.AuthService;
import com.kimoi.nomore.service.TokenService;
import com.kimoi.nomore.service.UserService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;
    private final TokenService tokenService;

    // AccessToken 발급 : RefreshToken 을 보유하고 있고 AccessToken 이 없는 경우
    @PostMapping("/token")
    public ResponseEntity<CreateAccessTokenResponse> createNewAccessToken(
            @RequestBody CreateAccessTokenRequest request) {
        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());
        return ResponseEntity.status(HttpStatus.CREATED).body(new CreateAccessTokenResponse(newAccessToken));
    }

    // 로그인
    @PostMapping("/signin")
    public ResponseEntity<?> userSignIn(@RequestBody UserSignInRequest userSignInRequest,
            HttpServletResponse response) {

        ResponseCookie cookie = ResponseCookie
                .from("refreshToken", authService.signIn(userSignInRequest).getAccessToken())
                .path("/")
                .httpOnly(true)
                // .secure(true) // HTTPS 환경에서만 사용
                .maxAge(24 * 60 * 60) // 쿠키 유효 시간 (예: 1일)
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signIn(userSignInRequest).getAccessToken());
    }

    // 아이디 중복 확인
    @GetMapping("/userid")
    public ResponseEntity<?> getUserId(@RequestParam IsUserIdAvailableRequest request) {
        boolean isAvailable = userService.isUserIdAvailable(request);

        return ResponseEntity.ok(isAvailable);
    }

    // 회원가입
    @PostMapping("/user")
    public ResponseEntity<?> userSignUp(@RequestBody AddUserRequest addUserRequest) {
        userService.save(addUserRequest);

        return ResponseEntity.ok("회원가입 완료");
    }

    // @GetMapping("path")
    // public SomeData getMethodName(@RequestParam String param) {
    //     return new SomeData();
    // }
    

}
