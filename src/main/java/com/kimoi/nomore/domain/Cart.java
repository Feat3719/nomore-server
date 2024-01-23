package com.kimoi.nomore.domain;

import com.kimoi.nomore.domain.embedded.CartId;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

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

    @EmbeddedId
    private CartId cartId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "cart_count", nullable = false)
    private Long cartCount;

    @Builder
    public Cart(User user, String cartUserId, String carProdId, Long cartCount) {
        this.user = user;
        this.cartId = new CartId(cartUserId, carProdId);
        this.cartCount = cartCount;
    }

}
