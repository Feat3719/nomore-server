package com.kimoi.nomore.domain;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "PROD")
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Prod {

    @Id
    @Column(name = "prod_id", nullable = false) // 상품아이디
    private String prodId;

    @Column(name = "prod_name", nullable = true) // 상품이름
    private String prodName;

    @Column(name = "prod_count", nullable = true) // 상품수량
    private int prodCount;

    @Column(name = "prod_prc", nullable = true) // 상품가격
    private int prodPrc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prod_ctcd") // 상품분류코드
    private ProdCtgr prodCtgr;

    @Column(name = "prod_img_url", nullable = true) // 상품이미지
    private String prodImgUrl;

    // 상품 디테일코드는 JDBCtemplit 사용해서 연결합니다.
    @Column(name = "prod_dtls") // 상품디테일코드
    private String prodDtls;

    @Column(name = "prod_company", nullable = true) // 상품제조회사
    private String prodCompany;

    @Column(name = "prod_date", nullable = true) // 상품등록년월
    private String prodDate;

    @Column(name = "prod_energy", nullable = true) // 상품에너지등급
    private String prodEnergy;

    @Column(name = "prod_power", nullable = true) // 상품소비전력
    private String prodPower;

    @OneToMany(mappedBy = "prod")
    private List<BuyDtls> buyDtlses = new ArrayList<>();

}
