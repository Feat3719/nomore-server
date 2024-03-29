package com.kimoi.nomore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kimoi.nomore.domain.Prod;

@Repository
public interface ProdRepository extends JpaRepository<Prod, String> {

    List<Prod> findByProdCtgr_prodCtgrCode(String prodCtgrCode);

    Optional<Prod> findByProdId(String prodId);
}