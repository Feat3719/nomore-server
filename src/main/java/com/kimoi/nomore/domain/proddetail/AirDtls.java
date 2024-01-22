package com.kimoi.nomore.domain.proddetail;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Table(name = "AIR_DTLS")
@Getter
@Entity
@Immutable
public class AirDtls {

    @Id
    @Column(name = "dtls_id", nullable = false) // 세부정보코드
    private String dtlsId;

    @Column(name = "dtls_inverter", nullable = true) // 인버터
    private Long dtlsInverter;

    @Column(name = "dtls_cool", nullable = true) // 냉방능력
    private String dtlsCool;

    @Column(name = "dtls_sleep", nullable = true) // 취침운전
    private Long dtlsSleep;

    @Column(name = "dtls_dehumid", nullable = true) // 제습운전
    private Long dtlsDehumid;

    @Column(name = "dtls_ksone", nullable = true) // 적합성평가인증
    private String dtlsKsone;

    @Column(name = "dtls_kstwo", nullable = true) // 안전확인인증
    private String dtlsKstwo;

}
