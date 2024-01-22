package com.kimoi.nomore.dto;

import java.time.LocalDate;

import com.kimoi.nomore.domain.User;

import lombok.Getter;

public class UserDto {

    // 로그인 UserDto
    @Getter
    public class UserSignInRequest {
        private String userId;
        private String userPwd;

    }

    // 회원 가입 UserDto
    @Getter
    public class CreateUserRequest {
        private String userId;
        private String userPwd;
        private String userEmail;
        private LocalDate userJoinedYmd;

        public User toEntity() {
            return User.builder()
                    .userId(this.userId)
                    .userPwd(this.userPwd)
                    .userEmail(this.userEmail)
                    .userJoinedYmd(this.userJoinedYmd)
                    .build();
        }
    }

    // 비밀번호 찾기 UserDto
    @Getter
    public class FindUserPwdRequest {
        private String userId;
        private String userEmail;

    }

    // 아이디 중복 확인 UserDto
    @Getter
    public class UserIdRequest {
        private String userId;
    }


    // 비밀번호 검증 UserDto
    @Getter
    public class VerifyPassword {
        private String userPwd;
    }

    // 회원탈퇴 UserDto
    @Getter
    public class DeleteUserRequest {
        private String refreshToken;
        private String userPwd;
    }

}
