package com.kimoi.nomore.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Table(name = "USER")
@Entity
@Getter
public class User {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_pwd")
    private String userPwd;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_tel")
    private String userTel;

    @Column(name = "user_address")
    private String userAddress;

    @CreatedDate
    @Column(name = "user_joined_ymd")
    private LocalDateTime userJoinedYmd;

    @Column(name = "user_age")
    private int userAge;

    @Column(name = "user_family_counts")
    private int userFailmyCounts;
    

    

}