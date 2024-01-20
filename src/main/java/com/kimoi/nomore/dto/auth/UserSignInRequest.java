package com.kimoi.nomore.dto.auth;

import lombok.Getter;

@Getter
public class UserSignInRequest {
    private String userId;
    private String userPwd;
    
}
