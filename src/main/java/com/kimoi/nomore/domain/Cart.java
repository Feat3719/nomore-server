package com.kimoi.nomore.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "CART")
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_no", nullable = false)
    private Long cartNo;

    @Column(name = "cart_user_id", nullable = false)
    private String cartUserId;

    @Column(name = "cart_prod_id", nullable = false)
    private String cartProdId;

    @Column(name = "cart_count", nullable = false)
    private Long cartCount;

    @Builder
    public Cart(Long cartNo, String cartUserId, String cartProdId, Long cartCount) {
        this.cartNo = cartNo;
        this.cartUserId = cartUserId;
        this.cartProdId = cartProdId;
        this.cartCount = cartCount;

    }

}
