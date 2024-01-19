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
public class ProdCtgr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_ctgr_id", nullable = false)
    private Long prodCtgrId;

    @Column(name = "prod_ctgr_name", nullable = false)
    private String prodCtgrName;

    @Column(name = "prod_ctgr_code", nullable = false)
    private String prodCtgrCode;

    @Builder
    public ProdCtgr(Long prodCtgrId,
            String prodCtgrName,
            String prodCtgrCode) {
        this.prodCtgrId = prodCtgrId;
        this.prodCtgrName = prodCtgrName;
        this.prodCtgrCode = prodCtgrCode;
    }

}
