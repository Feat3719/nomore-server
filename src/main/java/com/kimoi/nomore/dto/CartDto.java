package com.kimoi.nomore.dto;

import java.util.List;

import lombok.Getter;

public class CartDto {

    // 장바구니 추가 AddToCartRequest
    @Getter
    public static class AddToCartRequest {
        private String userId;
        private String prodId;
        private Long cartCount;
    }

    // 장바구니 추가 AddToCartResponse
    @Getter
    @lombok.Builder
    public static class GetAllItemsResponse {
        private String prodId;
        private String prodDtls;
        private String prodName;
        private String prodImgUrl;
        private int prodPrc;
        private Long cartCount;
    }

    // 장바구니 저장 Request
    @Getter
    public static class SaveCartRequest {
        private String userId;
        private List<Item> items;

        @Getter
        public static class Item {
            private String prodId;
            private Long cartCount;
        }
    }

}
