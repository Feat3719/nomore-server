package com.kimoi.nomore.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Prod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_id", nullable = false) // 상품아이디
    private String prod_id;

    @Column(name = "prod_name", nullable = false) // 상품이름
    private String prod_name;

    @Column(name = "prod_dscr", nullable = false) // 상품설명
    private String prod_dscr;

    @Column(name = "prod_count", nullable = false) // 상품수량
    private int prod_count;

    @Column(name = " prod_prc", nullable = false) // 상품 가격
    private int prod_prc;

    @Column(name = "prod_ctcd", nullable = false) // 상품분류코드
    private String prod_ctcd;

    @Column(name = "prod_img_url", nullable = false) // 상품이미지
    private String prod_img_url;

    @Column(name = "prod_dtls", nullable = false) // 상품세부정보코드
    private String prod_dtls;

    @Builder
    public Prod(String prod_id,
            String prod_name,
            String prod_dscr,
            int prod_count,
            int prod_prc,
            String prod_ctcd,
            String prod_img_url,
            String prod_dtls) {
        this.prod_id = prod_id;
        this.prod_name = prod_name;
        this.prod_dscr = prod_dscr;
        this.prod_count = prod_count;
        this.prod_prc = prod_prc;
        this.prod_ctcd = prod_ctcd;
        this.prod_img_url = prod_img_url;
        this.prod_dtls = prod_dtls;
    }

}
