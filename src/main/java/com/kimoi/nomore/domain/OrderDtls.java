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
public class OrderDtls {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordr_dtls_nm", nullable = false)
    private String ordr_dtls_nm;

    @Column(name = "ordr_pd_nm", nullable = false)
    private String ordr_pd_nm;

    @Column(name = "ordr_dtls_cnt", nullable = false)
    private int ordr_dtls_cnt;

    @Column(name = "ordr_dtls_prc", nullable = false)
    private String ordr_dtls_prc;

    @Builder
    public OrderDtls(
            String ordr_dtls_nm,
            String ordr_pd_nm,
            int ordr_dtls_cnt,
            String ordr_dtls_prc) {
        this.ordr_dtls_nm = ordr_dtls_nm;
        this.ordr_pd_nm = ordr_pd_nm;
        this.ordr_dtls_cnt = ordr_dtls_cnt;
        this.ordr_dtls_prc = ordr_dtls_prc;
    }

}
