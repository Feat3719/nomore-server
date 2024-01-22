package com.kimoi.nomore.domain.proddetail;

import org.hibernate.annotations.Immutable;

import com.kimoi.nomore.domain.Prod;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;

@Table(name = "COOKER_DTLS")
@Getter
@Entity
@Immutable
public class CookerDtls {

    @Id
    @Column(name = "dtls_id", nullable = false) // 세부정보코드
    private String dtlsId;

    @Column(name = "dtls_innerqual", nullable = true) // 내솥재질
    private String dtlsInnerqual;

    @Column(name = "dtls_innercot", nullable = true) // 내솥코팅
    private String dtlsInnercot;

    @Column(name = "dtls_white", nullable = true) // 백미쾌속
    private String dtlsWhite;

    @Column(name = "dtls_mix", nullable = true) // 잡곡쾌속
    private String dtlsMix;

    @Column(name = "dtls_twopressure", nullable = true) // 2기압취사
    private String dtlsTwopressure;

    @Column(name = "dtls_switch", nullable = true) // 대기전력 차단스위치
    private String dtlsSwitch;

    @Column(name = "dtls_ksone", nullable = true) // 적합성평가인증
    private String dtlsKsone;

    @Column(name = "dtls_kstwo", nullable = true) // 안전확인인증
    private String dtlsKstwo;

    // @OneToMany(mappedBy = "cookerDtls")
    // private List<Prod> prods = new ArrayList<>();
}