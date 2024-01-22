package com.kimoi.nomore.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class BuyDtlsId implements Serializable {

    @Column(name = "buy_pd_nm")
    private String buyPdNm;

}
