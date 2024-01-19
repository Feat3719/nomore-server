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

@Table(name = "ORDER_DTLS")
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderDtls {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordr_dtls_nm", nullable = false)
    private String ordrDtlsNm;

    @Column(name = "ordr_pd_nm", nullable = false)
    private String ordrPdNm;

    @Column(name = "ordr_dtls_cnt", nullable = false)
    private int ordrDtlsCnt;

    @Column(name = "ordr_dtls_prc", nullable = false)
    private int ordrDtlsPrc;

    @Builder
    public OrderDtls(
            String ordrDtlsNm,
            String ordrPdNm,
            int ordrDtlsCnt,
            int ordrDtlsPrc) {
        this.ordrDtlsNm = ordrDtlsNm;
        this.ordrPdNm = ordrPdNm;
        this.ordrDtlsCnt = ordrDtlsCnt;
        this.ordrDtlsPrc = ordrDtlsPrc;
    }

}
