package com.kimoi.nomore.repository.jwt;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kimoi.nomore.domain.RefreshToken;

import jakarta.transaction.Transactional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByUserId(String userId);
    Optional<RefreshToken> findByRefreshToken(String refreshToken);

    @Transactional
    void deleteByUserId(String userId);


}