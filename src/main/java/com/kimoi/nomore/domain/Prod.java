// package com.kimoi.nomore.domain;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;
// import lombok.AccessLevel;
// import lombok.Builder;
// import lombok.Getter;
// import lombok.NoArgsConstructor;

// @Table(name = "PROD")
// @Getter
// @Entity
// @NoArgsConstructor(access = AccessLevel.PROTECTED)
// public class Prod {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @Column(name = "prod_id", nullable = false) // 상품아이디
//     private Long prodId;

//     @Column(name = "prod_name", nullable = false) // 상품이름
//     private String prodName;

//     @Column(name = "prod_dscr", nullable = false) // 상품설명
//     private String prodDscr;

//     @Column(name = "prod_count", nullable = false) // 상품수량
//     private int prodCount;

//     @Column(name = " prod_prc", nullable = false) // 상품 가격
//     private int prodPrc;

//     @Column(name = "prod_ctcd", nullable = false) // 상품분류코드
//     private String prodCtcd;

//     @Column(name = "prod_img_url", nullable = false) // 상품이미지
//     private String prodImgUrl;

//     @Column(name = "prod_dtls", nullable = false) // 상품세부정보코드
//     private String prodDtls;

//     @Builder
//     public Prod(Long prodId,
//             String prodName,
//             String prodDscr,
//             int prodCount,
//             int prodPrc,
//             String prodCtcd,
//             String prodImgUrl,
//             String prodDtls) {
//         this.prodId = prodId;
//         this.prodName = prodName;
//         this.prodDscr = prodDscr;
//         this.prodCount = prodCount;
//         this.prodPrc = prodPrc;
//         this.prodCtcd = prodCtcd;
//         this.prodImgUrl = prodImgUrl;
//         this.prodDtls = prodDtls;
//     }

// }
