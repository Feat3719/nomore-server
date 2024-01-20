package com.kimoi.nomore.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kimoi.nomore.domain.User;
import com.kimoi.nomore.dto.user.AddUserRequest;
import com.kimoi.nomore.dto.user.IsUserIdAvailableRequest;
import com.kimoi.nomore.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    // 회원가입
    public String save(AddUserRequest dto) {
        return userRepository.save(User.builder()
        .userId(dto.getUserId())
        .userPwd(bCryptPasswordEncoder.encode(dto.getUserPwd()))
        .userEmail(dto.getUserEmail())
        .build()).getUserId();
    }
    // 아이디 찾기
    public User findByUserId(String userId){
        return userRepository.findByUserId(userId)
                .orElseThrow(()-> new IllegalArgumentException("Unexpected user"));
    }

    // 이메일 찾기
    public User findByUserEmail(String email){
        return userRepository.findByUserEmail(email)
                .orElseThrow(()-> new IllegalArgumentException("Unexpected user"));
    }

    // 아이디 중복 확인
    public boolean isUserIdAvailable(IsUserIdAvailableRequest isUserIdAvailableRequest) {
        return !userRepository.existsByUserId(isUserIdAvailableRequest.getUserId());
    }

    
}
