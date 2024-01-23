package com.kimoi.nomore.dto;

import lombok.Getter;

public class BuyDto {

    // 세부정보 페이지에서 구매하기 dto
    @Getter
    public static class GetAllBuyListRequest {
        private String userId;
        private String prodId;
        private Long cartCount;
    }

}
