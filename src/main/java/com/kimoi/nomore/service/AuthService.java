package com.kimoi.nomore.service;

import java.time.Duration;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kimoi.nomore.config.jwt.TokenProvider;
import com.kimoi.nomore.domain.User;
import com.kimoi.nomore.dto.auth.UserSignInRequest;
import com.kimoi.nomore.dto.jwt.CreateTokensResponse;
import com.kimoi.nomore.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public CreateTokensResponse signIn(UserSignInRequest userSignInRequest) {
        // 아이디와 비밀번호 체크
        User user = this.userRepository.findByUserId(userSignInRequest.getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("아이디가 존재하지 않습니다."));
        if (!passwordEncoder.matches(userSignInRequest.getUserPwd(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // Refresh Token 발급 + DB에 저장
        String refreshToken = tokenProvider.makeRefreshToken(user);
        String accessToken = tokenProvider.generateToken(user, Duration.ofMinutes(30));
        return CreateTokensResponse.builder()
                .refreshToken(refreshToken)
                .accessToken(accessToken)
                .build();
    }

}