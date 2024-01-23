package com.kimoi.nomore.dto;

import java.time.LocalDate;
import com.kimoi.nomore.domain.User;
import lombok.Getter;

public class UserDto {

    @Getter
    public static class UserIdRequest {
        private String userId;
    }

    // 로그인 Dto
    @Getter
    public static class UserSignInRequest {
        private String userId;
        private String userPwd;

    }

    // 회원 가입 Dto
    @Getter
    public static class CreateUserRequest {
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

    // 비밀번호 찾기 Dto
    @Getter
    public static class FindUserPwdRequest {
        private String userId;
        private String userEmail;

    }

    // 비밀번호 검증 Dto
    @Getter
    public static class VerifyPassword {
        private String userPwd;
    }

    // 회원탈퇴 Dto
    @Getter
    public static class DeleteUserRequest {
        private String refreshToken;
        private String userPwd;
    }

    // 장바구니 삭제 요청 Dto
    @Getter
    public static class RemoveItemRequest {
        private String userId;
    }

    // 장바구니 조회 요청 Dto
    @Getter
    public static class GetAllItemsRequest {
        private String userId;
    }

}
