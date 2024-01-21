package com.kimoi.nomore.service;

import java.time.LocalDate;

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
    public void save(AddUserRequest dto) {
        userRepository.save(User.builder()
        .userId(dto.getUserId())
        .userPwd(bCryptPasswordEncoder.encode(dto.getUserPwd()))
        .userEmail(dto.getUserEmail())
        .userJoinedYmd(LocalDate.now())
        .build());
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

    // 비밀번호 찾기(임시 비밀번호 발급 및 설정)
    public void setTempPassword(String userEmail, String tempPassword) {
        User user = userRepository.findByUserEmail(userEmail)
            .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + userEmail));

        user = User.builder().userPwd(bCryptPasswordEncoder.encode(tempPassword)).build();
        userRepository.save(user);
    }
    
}
