package com.kimoi.nomore.service;

import com.kimoi.nomore.config.jwt.TokenProvider;
import com.kimoi.nomore.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public String createNewAccessToken(String refreshToken){
        if(!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("Unexpected token");
        }

        String userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userService.findByUserId(userId);

        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }
}
