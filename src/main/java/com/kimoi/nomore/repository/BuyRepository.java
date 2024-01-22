package com.kimoi.nomore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kimoi.nomore.domain.Buy;

import jakarta.transaction.Transactional;

public interface BuyRepository extends JpaRepository<Buy, String> {
    @Transactional
    void deleteAllByBuyMbrId(String buyMbrId);
}
