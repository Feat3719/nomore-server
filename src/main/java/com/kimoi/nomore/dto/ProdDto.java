package com.kimoi.nomore.dto;

import lombok.Builder;
import lombok.Getter;

public class ProdDto {

    @Getter
    @Builder
    public static class ProductViewRequest {

        private String prodId;
        private String prodName;
        private Integer prodCount;
        private Integer prodPrc;
        private String prodImgUrl;
        private String prodDtls;
        private String prodEnergy;

    }

    @Getter
    public static class ProductUserViewRequest {
        private String UserId;
    }

}
