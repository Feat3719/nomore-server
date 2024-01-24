package com.kimoi.nomore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kimoi.nomore.domain.BuyDtls;
import com.kimoi.nomore.domain.embedded.BuyDtlsId;

public interface BuyDtlsRepository extends JpaRepository<BuyDtls, BuyDtlsId> {
    List<BuyDtls> findByBuy_User_UserId(String userId);
}
