package com.kimoi.nomore.domain;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Table(name = "BUY")
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Buy {

    @Id
    @Column(name = "buy_id", nullable = false) // 주문번호
    private String buyId;

    @Column(name = "buy_mbr_id", nullable = false) // 주문회원아이디
    private String buyMbrId;

    @CreatedDate
    @Column(name = "buy_ymd", nullable = false) // 주문일자
    private LocalDate buyYmd;

    @Column(name = "buy_addr", nullable = false) // 주문일자
    private String buyAddr;

    @Column(name = "buy_stts", nullable = false) // 주문상태
    private String buyStts;

    @PrePersist
    public void prePersist() {
        // 현재 날짜를 yyyyMMdd 형식으로 포매팅
        String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        // UUID 생성 후 첫 5자리 추출
        String uuidPart = UUID.randomUUID().toString().substring(0, 5);
        // 두 문자열을 결합
        this.buyId = datePart + uuidPart;
    }

    @Builder
    public Buy(
            String buyMbrId,
            LocalDate buyYmd,
            String buyAddr,
            String buyStts) {
        this.buyMbrId = buyMbrId;
        this.buyYmd = buyYmd;
        this.buyAddr = buyAddr;
        this.buyStts = buyStts;
    }

}
