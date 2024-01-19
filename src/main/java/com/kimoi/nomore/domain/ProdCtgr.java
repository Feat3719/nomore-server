package com.kimoi.nomore.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Table(name = "PROD_CTGR")
@Entity
@Getter
public class ProdCtgr {

    @Id
    @GeneratedValue
    @Column(name = "prod_ctgr_id")
    private String prodCtgrId;

    @Column(name = "prod_ctgr_name")
    private String prodCtgrName;

    @Column(name = "prod_ctgr_code")
    private String prodCtgrCode;

    

}