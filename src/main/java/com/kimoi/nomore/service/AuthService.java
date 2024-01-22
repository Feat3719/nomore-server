package com.kimoi.nomore.service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kimoi.nomore.config.jwt.TokenProvider;
import com.kimoi.nomore.domain.RefreshToken;
import com.kimoi.nomore.domain.User;
import com.kimoi.nomore.dto.UserDto.UserSignInRequest;
import com.kimoi.nomore.dto.EmailDto.EmailPostRequest;
import com.kimoi.nomore.dto.TokenDto.CreateTokensResponse;
import com.kimoi.nomore.dto.TokenDto.GetRefreshToken;
import com.kimoi.nomore.dto.UserDto.CreateUserRequest;
import com.kimoi.nomore.dto.UserDto.DeleteUserRequest;
import com.kimoi.nomore.dto.UserDto.FindUserPwdRequest;
import com.kimoi.nomore.dto.UserDto.UserIdRequest;
import com.kimoi.nomore.exception.NotFoundErrorException;
import com.kimoi.nomore.repository.BuyRepository;
import com.kimoi.nomore.repository.CartRepository;
import com.kimoi.nomore.repository.UserRepository;
import com.kimoi.nomore.repository.jwt.RefreshTokenRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final CartRepository cartRepository;
    private final BuyRepository buyRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 로그인(RT, AT 발급)
    @Transactional
    public CreateTokensResponse signIn(UserSignInRequest userSignInRequest) {
        // 아이디와 비밀번호 체크
        User user = this.userRepository.findByUserId(userSignInRequest.getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("아이디가 존재하지 않습니다."));
        if (!bCryptPasswordEncoder.matches(userSignInRequest.getUserPwd(), user.getPassword())) {
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

    // 회원가입
    @Transactional
    public void save(CreateUserRequest dto) {
        User user = User.builder()
                .userId(dto.getUserId())
                .userPwd(bCryptPasswordEncoder.encode(dto.getUserPwd()))
                .userEmail(dto.getUserEmail())
                .userJoinedYmd(LocalDate.now())
                .build();

        userRepository.save(user);
    }

    // 아이디로 회원 찾기
    public User findByUserId(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundErrorException("Unexpected user"));
    }

    // 이메일로 회원 찾기
    private User findByUserEmail(String email) {
        return userRepository.findByUserEmail(email)
                .orElseThrow(() -> new NotFoundErrorException("Unexpected user"));
    }

    // RefreshToken 찾기
    private RefreshToken findRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new NotFoundErrorException("리프레시 토큰을 찾을 수 없음"));

    }

    // RefreshToken 으로 회원 찾기
    private User findUserByRefreshToken(String refreshToken) {
        return userRepository
                .findByUserId(refreshTokenRepository.findByRefreshToken(refreshToken).get().getUserId())
                .orElseThrow(() -> new NotFoundErrorException("리프레시 토큰을 찾을 수 없음"));
    }

    // 아이디 중복 확인
    public boolean isUserIdAvailable(UserIdRequest userIdRequest) {
        return !userRepository.existsByUserId(userIdRequest.getUserId());
    }

    // 비밀번호 찾기(임시 비밀번호 발급 및 설정)
    @Transactional
    public void setTempPassword(String userEmail, String tempPassword) {
        User user = this.findByUserEmail(userEmail);

        user.updatePassword(bCryptPasswordEncoder.encode(tempPassword));
        userRepository.save(user);
    }

    // 비밀번호 찾기
    public FindUserPwdRequest findUserPwd(FindUserPwdRequest findUserPwdRequest) {
        // 아이디 체크
        User user = userRepository.findByUserId(findUserPwdRequest.getUserId())
                .orElseThrow(() -> new NotFoundErrorException("해당 아이디가 존재하지 않습니다."));
        // 이메일 체크
        if (user.getUserEmail().equals(findUserPwdRequest.getUserEmail())) {
            return findUserPwdRequest;
        } else {
            throw new NotFoundErrorException("이메일이 올바르지 않습니다.");
        }
    }

    // 아이디 찾기
    public String findUserId(EmailPostRequest emailPostRequest) {
        User user = this.findByUserEmail(emailPostRequest.getUserEmail());

        return user.getUserId();
    }

    // 로그아웃
    public void userSignOut(GetRefreshToken request) {
        RefreshToken refreshToken = this.findRefreshToken(request.getRefreshToken());
    
        if (refreshToken != null) {
            refreshTokenRepository.delete(refreshToken);
        } else {
            throw new NotFoundErrorException("삭제할 수 없음");
        }
    }

    // 회원 탈퇴
    @Transactional
    public void deleteUser(DeleteUserRequest request) {

        String userId = this.findUserByRefreshToken(request.getRefreshToken()).getUserId();
        cartRepository.deleteAllByCartUserId(userId);
        buyRepository.deleteAllByBuyMbrId(userId);
        refreshTokenRepository.deleteByUserId(userId);
    }
}