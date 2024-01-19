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
    private String prod_ctgr_id;

    @Column(name = "prod_ctgr_name", nullable = false)
    private String prod_ctgr_name;

    @Column(name = "prod_ctgr_code", nullable = false)
    private String prod_ctgr_code;

    @Builder
    public ProdCtgr(String prod_ctgr_id,
            String prod_ctgr_name,
            String prod_ctgr_code) {
        this.prod_ctgr_id = prod_ctgr_id;
        this.prod_ctgr_name = prod_ctgr_name;
        this.prod_ctgr_code = prod_ctgr_code;
    }

}
