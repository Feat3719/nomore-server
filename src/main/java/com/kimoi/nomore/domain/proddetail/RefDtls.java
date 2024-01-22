package com.kimoi.nomore.domain.proddetail;

import org.hibernate.annotations.Immutable;

import java.util.ArrayList;
import java.util.List;

import com.kimoi.nomore.domain.Prod;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;

@Table(name = "REF_DTLS")
@Entity
@Getter
@Immutable
public class RefDtls {

    @Id
    @Column(name = "dtls_id", nullable = false) // 세부정보코드
    private String dtlId;

    @Column(name = "dtls_item", nullable = true) // 제조국
    private String dtlsItem;

    @Column(name = "dtls_capacity", nullable = true) // 총용량
    private String dtlsCapacity;

    @Column(name = "dtls_ksone", nullable = true) // 적합성평가인증
    private String dtlsKsone;

    @Column(name = "dtls_kstwo", nullable = true) // 안전확인인증
    private String dtlsKstwo;

    // @OneToMany(mappedBy = "refDtls")
    // private List<Prod> prods = new ArrayList<>();

}
