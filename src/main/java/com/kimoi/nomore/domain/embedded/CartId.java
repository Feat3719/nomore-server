package com.kimoi.nomore.domain.embedded;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;

@Embeddable
@AllArgsConstructor
public class CartId implements Serializable {

    @Column(name = "cart_user_id")
    private String cartUserId;

    @Column(name = "cart_prod_id")
    private String cartProdId;

}
