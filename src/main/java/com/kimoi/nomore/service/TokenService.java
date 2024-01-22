package com.kimoi.nomore.service;

import com.kimoi.nomore.config.jwt.TokenProvider;
import com.kimoi.nomore.domain.RefreshToken;
import com.kimoi.nomore.domain.User;
import com.kimoi.nomore.repository.jwt.RefreshTokenRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final AuthService authService;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    // 엑세스 토큰 발급
    public String createNewAccessToken(String refreshToken) {
        if (!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("유효한 토큰이 아닙니다.");
        }
        String userId = this.findByRefreshToken(refreshToken).getUserId();
        User user = authService.findByUserId(userId);

        return tokenProvider.generateToken(user, Duration.ofMinutes(30));
    }

    // 리프레쉬 토큰 찾기
    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected token"));
    }
}
