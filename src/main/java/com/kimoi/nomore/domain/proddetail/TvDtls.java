package com.kimoi.nomore.domain.proddetail;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Table(name = "TV_DTLS")
@Getter
@Entity
@Immutable
public class TvDtls {

    @Id
    @Column(name = "dtls_id", nullable = false) // 세부정보코드
    private String dtlsId;

    @Column(name = "dtls_screensize", nullable = true) // 화면크기
    private String dtlsScreensize;

    @Column(name = "dtls_display", nullable = true) // 디스플레이
    private String dtlsDisplay;

    @Column(name = "dtls_resolution", nullable = true) // 해상도
    private String dtlsResolution;

    @Column(name = "dtls_panel", nullable = true) // 패널
    private String dtlsPanel;

    @Column(name = "dtls_fourksr", nullable = true) // 4K주사율
    private String dtlsFourksr;

    @Column(name = "dtls_fourkup", nullable = true) // 4K업스케일링
    private String dtlsFourkup;

    @Column(name = "dtls_processor", nullable = true) // 프로세서
    private String dtlsProcessor;

    @Column(name = "dtls_smart", nullable = true) // 스마트
    private String dtlsSmart;

    @Column(name = "dtls_dolby", nullable = true) // 돌비애트모스
    private String dtlsDolby;

    @Column(name = "dtls_hdmi", nullable = true) // HDMI
    private String dtlsHdmi;

    @Column(name = "dtls_wifi", nullable = true) // WIFI
    private String dtlsWifi;

    @Column(name = "dtls_lan", nullable = true) // LAN
    private String dtlsLan;

    @Column(name = "dtls_hdr", nullable = true) // HDR
    private String dtlsHdr;

    @Column(name = "dtls_size", nullable = true) // 크기
    private String dtlsSize;

    @Column(name = "dtls_ksone", nullable = true) // 적합성평가인증
    private String dtlsKsone;

    @Column(name = "dtls_kstwo", nullable = true) // 안전확인인증
    private String dtlsKstwo;

}
