package com.kimoi.nomore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kimoi.nomore.domain.Prod;

public interface ProdRepository extends JpaRepository<Prod, String> {
    Optional<Prod> findByProdId(String prodId);
}
