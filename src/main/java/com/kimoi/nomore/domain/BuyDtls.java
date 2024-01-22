package com.kimoi.nomore.domain;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Table(name = "BUY_DTLS")
@Getter
@Entity
@Immutable
public class BuyDtls {

    @Id
    @Column(name = "buy_pd_nm", nullable = false)
    private String buyPdNm;

    @Column(name = "buy_dtls_nm", nullable = false)
    private String buyDtlsNm;

    @Column(name = "buy_dtls_cnt", nullable = false)
    private int buyDtlsCnt;

    @Column(name = "buy_dtls_prc", nullable = false)
    private int buyDtlsPrc;

}
