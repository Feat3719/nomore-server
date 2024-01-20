package com.kimoi.nomore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kimoi.nomore.domain.Prod;

public interface ProdRepository extends JpaRepository<Prod, String> {

}
