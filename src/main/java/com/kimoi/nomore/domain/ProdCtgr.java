package com.kimoi.nomore.domain;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "PROD_CTGR")
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProdCtgr {

    @Id
    @Column(name = "prod_ctgr_code", nullable = false)
    private String prodCtgrCode;

    @Column(name = "prod_ctgr_name", nullable = false)
    private String prodCtgrName;

    @OneToMany(mappedBy = "prodCtgr")
    private List<Prod> Pords = new ArrayList<>();

    @Builder
    public ProdCtgr(
            String prodCtgrCode,
            String prodCtgrName) {
        this.prodCtgrCode = prodCtgrCode;
        this.prodCtgrName = prodCtgrName;
    }

}
