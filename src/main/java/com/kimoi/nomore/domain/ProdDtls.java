package com.kimoi.nomore.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

// @Getter
// @Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProdDtls {

    // prod detail 테스트 & 붙여넣기용 입니다.
    @Id
    @Column(name = "dtls_id", nullable = false)
    private String dtls_id;

    @Column(name = "dtls", nullable = false)
    private String dtls;

    @Builder
    public ProdDtls(String dtls_id, String dtls) {
        this.dtls_id = dtls_id;
        this.dtls = dtls;
    }

}
