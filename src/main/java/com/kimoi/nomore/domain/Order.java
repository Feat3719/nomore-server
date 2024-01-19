package com.kimoi.nomore.domain;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "ORDER")
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordr_id", nullable = false) // 주문번호
    private Long ordrId;

    @Column(name = "ordr_mbr_id", nullable = false) // 주문회원아이디
    private String ordrMbrId;

    @CreatedDate
    @Column(name = "ordr_ymd", nullable = false) // 주문일자
    private LocalDate ordrYmd;

    @Column(name = "ordr_addr", nullable = false) // 주문일자
    private String ordrAddr;

    @Column(name = "ordr_stts", nullable = false) // 주문상태
    private String ordrStts;

    @Builder
    public Order(
        Long ordrId,
            String ordrMbrId,
            LocalDate ordrYmd,
            String ordrAddr,
            String ordrStts) {
        this.ordrId = ordrId;
        this.ordrMbrId = ordrMbrId;
        this.ordrYmd = ordrYmd;
        this.ordrAddr = ordrAddr;
        this.ordrStts = ordrStts;
    }

}
