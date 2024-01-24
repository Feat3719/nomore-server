package com.kimoi.nomore.dto;

import java.util.Date;

import lombok.Getter;

public class BuyDto {

    // 세부정보 페이지에서 구매하기 Requestdto
    @Getter
    public static class BuyRequest {
        private String userId;
        private String prodId;
        private Long count;
    }

    // 장바구니 페이지에서 구매하기 Requestdto
    @Getter
    public static class BuyAllRequest {
        private String userId;
        private String prodId;
        private Long cartCount;
    }

    // 장바구니 페이지에서 구매하기 ResponseDto
    @lombok.Builder
    @Getter
    public static class BuyAllResponse {
        private String buyId; // 주문번호
        private String userId; // 유저아이디
        private String buyStts; // 배송상태
        private Date buyYmd; // 주문일자
        private String buyAddr; // 배송주소(유저주소)
        private String prodId; // 주문상품번호
        private String prodImgUrl; // 상품이미지주소
        private Integer price; // 제품가격
        private Long count; // 구매수량
    }

}
