package com.kimoi.nomore.dto.user;

import com.kimoi.nomore.domain.User;

import lombok.Getter;

@Getter
public class AddUserRequest {
    private String userId;
    private String userPwd;
    private String userEmail;
    
    public User toEntity() {
        return User.builder()
                .userId(this.userId)
                .userPwd(this.userPwd)
                .userEmail(this.userEmail)
                .build();
    }
}
