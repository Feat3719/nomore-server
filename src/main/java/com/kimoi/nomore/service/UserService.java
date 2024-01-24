package com.kimoi.nomore.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kimoi.nomore.domain.User;
import com.kimoi.nomore.dto.UserDto.GetUserInfoRequest;
import com.kimoi.nomore.dto.UserDto.GetUserInfoResponse;
import com.kimoi.nomore.dto.UserDto.SaveUserInfoRequest;
import com.kimoi.nomore.exception.NotFoundErrorException;
import com.kimoi.nomore.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthService authService;
    private final UserRepository userRepository;

    // 돈의 값이 NULL 인지 검증
    public void enoughMoney(String userId) {
        User user = authService.findByUserId(userId);
        Optional.ofNullable(user.getUserMoney())
                .orElseThrow(() -> new NotFoundErrorException("FAIL : MONEY IS NULL"));
    }

    // 유저 정보 조회
    public GetUserInfoResponse getUserInfo(GetUserInfoRequest request) {
        User user = authService.findByUserId(request.getUserId());

        return GetUserInfoResponse.builder()
                .userId(user.getUserId())
                .userNickName(user.getUserNickName())
                .userEmail(user.getUserEmail())
                .userTel(user.getUserTel())
                .userAddress(user.getUserAddress())
                .userGender(user.getUserGender())
                .userBirth(user.getUserBirth())
                .userFamilyCounts(user.getUserFamilyCounts())
                .build();

    }

    // 유저 정보 저장
    @Transactional
    public void updateUserInfo(SaveUserInfoRequest request) {
        User user = authService.findByUserId(request.getUserId());
        user.setUser(bCryptPasswordEncoder.encode(
                request.getUserPwd()),
                request.getUserNickName(),
                request.getUserTel(),
                request.getUserAddress(),
                request.getUserGender(),
                request.getUserBirth(),
                request.getUserFamilyCounts());

        userRepository.save(user);
    }
}
