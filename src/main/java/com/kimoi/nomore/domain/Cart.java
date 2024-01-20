package com.kimoi.nomore.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Table(name = "CART")
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart {
    @Id
    @Column(name = "cart_no", nullable = false)
    private String cartNo;

    @Column(name = "cart_user_id", nullable = false)
    private String cartUserId;

    @Column(name = "cart_prod_id", nullable = false)
    private String cartProdId;

    @Column(name = "cart_count", nullable = false)
    private Long cartCount;

    @PrePersist
    public void prePersist() {
        // 현재 날짜를 yyyyMMdd 형식으로 포매팅
        String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        // UUID 생성 후 첫 5자리 추출
        String uuidPart = UUID.randomUUID().toString().substring(0, 5);

        // 두 문자열을 결합
        this.cartNo = datePart + uuidPart;
    }

    @Builder
    public Cart(String cartUserId, String cartProdId, Long cartCount) {
        this.cartUserId = cartUserId;
        this.cartProdId = cartProdId;
        this.cartCount = cartCount;

    }

}
