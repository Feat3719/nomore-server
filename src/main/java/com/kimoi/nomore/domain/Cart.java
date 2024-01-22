package com.kimoi.nomore.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_user_id")
    private User user;

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
    public Cart(User user, String cartProdId, Long cartCount) {
        this.user = user;
        this.cartProdId = cartProdId;
        this.cartCount = cartCount;
    }

}
