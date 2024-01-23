package com.kimoi.nomore.dto;

import lombok.Getter;

public class ProdDto {

    @Getter
    @lombok.Builder
    public static class ProductViewRequest {

        private String prodId;
        private String prodName;
        private String prodCount;
        private int prodPrc;
        private String prodImgUrl;
        private String prodDtls;
        private String prodEnergy;

    }

}
