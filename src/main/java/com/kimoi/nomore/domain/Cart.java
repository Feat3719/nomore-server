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
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_no", nullable = false)
    private Long cart_no;

    @Column(name = "cart_user_id", nullable = false)
    private String cart_user_id;

    @Column(name = "cart_prod_id", nullable = false)
    private String cart_prod_id;

    @Column(name = "cart_count", nullable = false)
    private int cart_count;

    @Builder
    public Cart(Long cart_no, String cart_user_id, String cart_prod_id, int cart_count) {
        this.cart_no = cart_no;
        this.cart_user_id = cart_user_id;
        this.cart_prod_id = cart_prod_id;
        this.cart_count = cart_count;

    }

}
