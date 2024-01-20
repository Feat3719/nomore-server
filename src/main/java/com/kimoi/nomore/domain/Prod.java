package com.kimoi.nomore.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "PROD")
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Prod {

    @Id
    @Column(name = "prod_id", nullable = true) // 상품아이디
    private String prodId;

    @Column(name = "prod_name", nullable = true) // 상품이름
    private String prodName;

    @Column(name = "prod_count", nullable = true) // 상품수량
    private int prodCount;

    @Column(name = " prod_prc", nullable = true) // 상품가격
    private int prodPrc;

    @Column(name = "prod_ctcd", nullable = true) // 상품분류코드
    private String prodCtcd;

    @Column(name = "prod_img_url", nullable = true) // 상품이미지
    private String prodImgUrl;

    @Column(name = "prod_dtls", nullable = false) // 상품디테일코드
    private String prodDtls;

    @Column(name = "prod_company", nullable = true) // 상품제조회사
    private String prodCompany;

    @Column(name = "prod_date", nullable = true) // 상품등록년월
    private String prodDate;

    @Column(name = "prod_energy", nullable = true) // 상품에너지등급
    private String prodEnergy;

    @Column(name = "prod_power", nullable = true) // 상품소비전력
    private String prodPower;

    @Builder
    public Prod(
            String prodId,
            String prodName,
            String prodDscr,
            int prodCount,
            int prodPrc,
            String prodCtcd,
            String prodImgUrl,
            String prodDtls,
            String prodCompany,
            String prodDate,
            String prodEnergy,
            String prodPower) {
        this.prodId = prodId;
        this.prodName = prodName;
        this.prodCount = prodCount;
        this.prodPrc = prodPrc;
        this.prodCtcd = prodCtcd;
        this.prodImgUrl = prodImgUrl;
        this.prodDtls = prodDtls;
        this.prodCompany = prodCompany;
        this.prodDate = prodDate;
        this.prodEnergy = prodEnergy;
        this.prodPower = prodPower;
    }

}
