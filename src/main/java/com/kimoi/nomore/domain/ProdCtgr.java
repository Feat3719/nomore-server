package com.kimoi.nomore.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "PROD_CTGR")
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProdCtgr {

    @Id
    @Column(name = "prod_ctgr_code", nullable = false)
    private Long prodCtgrCode;

    @Column(name = "prod_ctgr_name", nullable = false)
    private String prodCtgrName;

}
