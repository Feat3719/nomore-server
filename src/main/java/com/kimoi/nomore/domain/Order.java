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
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordr_nm", nullable = false)
    private String ordr_nm;

    @Column(name = "ordr_mbr_id", nullable = false)
    private String ordr_mbr_id;

    @Column(name = "ordr_ymd", nullable = false)
    private String ordr_ymd;

    @Column(name = "ordr_addr", nullable = false)
    private String ordr_addr;

    @Column(name = "ordr_stts", nullable = false)
    private String ordr_stts;

    @Builder
    public Order(
            String ordr_nm,
            String ordr_mbr_id,
            String ordr_ymd,
            String ordr_addr,
            String ordr_stts) {
        this.ordr_nm = ordr_nm;
        this.ordr_mbr_id = ordr_mbr_id;
        this.ordr_ymd = ordr_ymd;
        this.ordr_addr = ordr_addr;
        this.ordr_stts = ordr_stts;
    }

}
