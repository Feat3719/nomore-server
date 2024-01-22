package com.kimoi.nomore.controller;

import org.springframework.web.bind.annotation.RestController;

import com.kimoi.nomore.dto.UserDto.UserSignInRequest;
import com.kimoi.nomore.dto.TokenDto.CreateAccessTokenRequest;
import com.kimoi.nomore.dto.TokenDto.CreateAccessTokenResponse;
import com.kimoi.nomore.dto.TokenDto.GetRefreshToken;
import com.kimoi.nomore.dto.UserDto.CreateUserRequest;
import com.kimoi.nomore.dto.UserDto.DeleteUserRequest;
import com.kimoi.nomore.dto.UserDto.UserIdRequest;
import com.kimoi.nomore.service.AuthService;
import com.kimoi.nomore.service.TokenService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

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
        String refreshToken = authService.signIn(userSignInRequest).getRefreshToken();
        String accessToken = authService.signIn(userSignInRequest).getAccessToken();
        ResponseCookie cookie = ResponseCookie
                .from("refreshToken", refreshToken)
                .path("/")
                .httpOnly(true)
                // .secure(true) // HTTPS 환경에서만 사용
                .maxAge(24 * 60 * 60) // 쿠키 유효 시간 (예: 1일)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Set-Cookie", cookie.toString())
                .body(new CreateAccessTokenResponse(accessToken));
    }

    // 아이디 중복 확인
    @GetMapping("/check-avilability-userid")
    public ResponseEntity<?> getUserId(@RequestBody UserIdRequest request) {
        boolean isAvailable = authService.isUserIdAvailable(request);

        return ResponseEntity.ok(isAvailable);
    }

    // 회원가입
    @PostMapping("/user")
    public ResponseEntity<?> userSignUp(@RequestBody CreateUserRequest addUserRequest) {
        authService.save(addUserRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body("SUCCESS : Sign Up Completed");
    }

    // 로그아웃
    @PostMapping("/signout")
    public ResponseEntity<?> userSignOut(@RequestBody GetRefreshToken request) {
        authService.userSignOut(request);
        return ResponseEntity.status(HttpStatus.OK).body("SUCCESS : SignOut");
    }

    // 회원 탈퇴
    @DeleteMapping("/user")
    public ResponseEntity<?> deleteUser(@RequestBody DeleteUserRequest request) {
        authService.deleteUser(request);
        return ResponseEntity.status(HttpStatus.OK).body("SUCCESS : SignOut");
    }

}
