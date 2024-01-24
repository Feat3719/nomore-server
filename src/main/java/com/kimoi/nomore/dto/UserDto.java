package com.kimoi.nomore.dto;

import java.time.LocalDate;
import com.kimoi.nomore.domain.User;

import lombok.Builder;
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

    // 비밀번호 찾기 RequestDto
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

    // 회원탈퇴 RequestDto
    @Getter
    public static class DeleteUserRequest {
        private String refreshToken;
        private String userPwd;
    }

    // 장바구니 삭제 요청 RequestDto
    @Getter
    public static class RemoveItemRequest {
        private String userId;
    }

    // 장바구니 조회 요청 RequestDto
    @Getter
    public static class GetAllItemsRequest {
        private String userId;
    }

    // 구매목록 조회 요청 RequestDto
    @Getter
    public static class GetAllBoughtProductListRequest {
        private String userId;
    }

    // 내 정보 조회 요청 RequestDto
    @Getter
    public static class GetUserInfoRequest {
        private String userId;
    }

    // 내 정보 조회 요청 ResponseDto
    @Getter
    @Builder
    public static class GetUserInfoResponse {
        private String userId;
        private String userNickName;
        private String userEmail;
        private String userTel;
        private String userAddress;
        private String userGender;
        private LocalDate userBirth;
        private String userFamilyCounts;
    }

    // 내 정보 저장 요청 RequestDto
    @Getter
    @Builder
    public static class SaveUserInfoRequest {
        private String userId;
        private String userPwd;
        private String userNickName;
        private String userTel;
        private String userAddress;
        private String userGender;
        private LocalDate userBirth;
        private String userFamilyCounts;
    }

}
