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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "user_pwd", nullable = false)
    private String userPwd;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @Column(name = "user_tel", nullable = false)
    private String userTel;

    @Column(name = "user_address", nullable = false)
    private String userAddress;

    @Column(name = "user_joined_ymd", nullable = false)
    private String userJoinedYmd;

    @Column(name = "user_gender", nullable = false)
    private String userGender;

    @Column(name = "user_age", nullable = false)
    private Long userAge;

    @Column(name = "user_family_counts", nullable = false)
    private String userFamilyCounts;

    @Builder
    public User(
            String userId,
            String userPwd,
            String userName,
            String userEmail,
            String userTel,
            String userAddress,
            String userJoinedYmd,
            String userGender,
            Long userAge,
            String userFamilyCounts) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userTel = userTel;
        this.userAddress = userAddress;
        this.userJoinedYmd = userJoinedYmd;
        this.userGender = userGender;
        this.userAge = userAge;
        this.userFamilyCounts = userFamilyCounts;
    }
}
