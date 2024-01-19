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
    private String user_id;

    @Column(name = "user_pwd", nullable = false)
    private String user_pwd;

    @Column(name = "user_name", nullable = false)
    private String user_name;

    @Column(name = "user_email", nullable = false)
    private String user_email;

    @Column(name = "user_tel", nullable = false)
    private String user_tel;

    @Column(name = "user_address", nullable = false)
    private String user_address;

    @Column(name = "user_joined_ymd", nullable = false)
    private String user_joined_ymd;

    @Column(name = "user_gender", nullable = false)
    private String user_gender;

    @Column(name = "user_age", nullable = false)
    private int user_age;

    @Column(name = "user_family_counts", nullable = false)
    private String user_family_counts;

    @Builder
    public User(
            String user_id,
            String user_pwd,
            String user_name,
            String user_email,
            String user_tel,
            String user_address,
            String user_joined_ymd,
            String user_gender,
            int user_age,
            String user_family_counts) {
        this.user_id = user_id;
        this.user_pwd = user_pwd;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_tel = user_tel;
        this.user_address = user_address;
        this.user_joined_ymd = user_joined_ymd;
        this.user_gender = user_gender;
        this.user_age = user_age;
        this.user_family_counts = user_family_counts;
    }
}
