package com.kimoi.nomore.dto.jwt;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateTokensResponse {
    
    private String refreshToken;
    private String accessToken;

    @Builder
    public CreateTokensResponse(String refreshToken, String accessToken) {
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
    }
}
