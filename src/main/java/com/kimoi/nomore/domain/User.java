package com.kimoi.nomore.domain;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "USER")
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements UserDetails {

    @Id
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "user_pwd", nullable = false)
    private String userPwd;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @Column(name = "user_tel")
    private String userTel;

    @Column(name = "user_address")
    private String userAddress;

    @Column(name = "user_joined_ymd", nullable = false)
    @CreatedBy
    private LocalDate userJoinedYmd;

    @Column(name = "user_gender")
    private String userGender;

    @Column(name = "user_birth")
    private String userBirth;

    @Column(name = "user_family_counts")
    private String userFamilyCounts;

    @OneToMany(mappedBy = "user")
    private List<Cart> carts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Buy> buys = new ArrayList<>();

    @Builder
    public User(
            String userId,
            String userPwd,
            String userEmail,
            LocalDate userJoinedYmd) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.userEmail = userEmail;
        this.userJoinedYmd = userJoinedYmd;
    };

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public String getPassword() {
        return userPwd;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
