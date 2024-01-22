package com.kimoi.nomore.domain.proddetail;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;

@Table(name = "WASH_DTLS")
@Getter
@Entity
@Immutable
public class WashDtls {

    @Id
    @Column(name = "dtls_id", nullable = false) // 세부정보코드
    private String dtlsId;

    @Column(name = "dtls_item", nullable = true) // 품목
    private String dtlsItem;

    @Column(name = "dtls_capacity", nullable = true) // 세탁용량
    private String dtlsCapacity;

    @Column(name = "dtls_ksone", nullable = true) // 적합성평가인증
    private String dtlsKsone;

    @Column(name = "dtls_kstwo", nullable = true) // 안전확인인증
    private String dtlsKstwo;

    // @OneToMany(mappedBy = "washDtls")
    // private List<Prod> prods = new ArrayList<>();
}
