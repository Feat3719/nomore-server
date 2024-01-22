package com.kimoi.nomore.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buy_mbr_id") // 주문회원아이디
    private User user;
    // private String buyMbrId;

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

    @OneToMany(mappedBy = "buy")
    private List<BuyDtls> buyDtlses = new ArrayList<>();

    @Builder
    public Buy(
            User user,
            LocalDate buyYmd,
            String buyAddr,
            String buyStts) {
        this.user = user;
        this.buyYmd = buyYmd;
        this.buyAddr = buyAddr;
        this.buyStts = buyStts;
    }

}
