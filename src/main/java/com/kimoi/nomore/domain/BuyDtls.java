package com.kimoi.nomore.domain;

import com.kimoi.nomore.domain.embedded.BuyDtlsId;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "BUY_DTLS")
@Getter
@Entity
@NoArgsConstructor
public class BuyDtls {

    @EmbeddedId
    private BuyDtlsId id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @MapsId("buyPdNm")
    @JoinColumn(name = "buy_pd_nm")
    private Prod prod;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("buyDtlsNm")
    @JoinColumn(name = "buy_dtls_nm")
    private Buy buy;

    @Column(name = "buy_dtls_cnt", nullable = false)
    private Long buyDtlsCnt;

    @Column(name = "buy_dtls_prc", nullable = false)
    private Integer buyDtlsPrc;

    @Builder
    public BuyDtls(BuyDtlsId id, Prod prod, Buy buy, Long buyDtlsCnt, Integer buyDtlsPrc) {
        this.id = id;
        this.prod = prod;
        this.buy = buy;
        this.buyDtlsCnt = buyDtlsCnt;
        this.buyDtlsPrc = buyDtlsPrc;
    }
}