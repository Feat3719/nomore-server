package com.kimoi.nomore.domain.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;

@Builder
@Embeddable
@AllArgsConstructor
public class BuyDtlsId implements Serializable {

    @Column(name = "buy_pd_nm")
    private String buyPdNm;

    @Column(name = "buy_dtls_nm")
    private String buyDtlsNm;

    protected BuyDtlsId() {
    }

}
