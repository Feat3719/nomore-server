package com.kimoi.nomore.dto.user;

import com.kimoi.nomore.domain.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private String userId;
    private String userPwd;
    private String userEmail;
    
    public UserResponse(User entity) {
    	this.userId = entity.getUserId();
        this.userPwd = entity.getUserPwd();
        this.userEmail = entity.getUserEmail();
    }
}