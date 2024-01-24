package com.kimoi.nomore.domain;

import org.hibernate.annotations.Immutable;

import com.kimoi.nomore.domain.embedded.BuyDtlsId;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;

@Table(name = "BUY_DTLS")
@Getter
@Entity
@Immutable
public class BuyDtls {

    @EmbeddedId
    private BuyDtlsId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("buyPdNm")
    @JoinColumn(name = "buy_pd_nm")
    private Prod prod;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("buyDtlsNm")
    @JoinColumn(name = "buy_dtls_nm")
    private Buy buy;

    @Column(name = "buy_dtls_cnt", nullable = false)
    private int buyDtlsCnt;

    @Column(name = "buy_dtls_prc", nullable = false)
    private int buyDtlsPrc;

}