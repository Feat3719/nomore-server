package com.kimoi.nomore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kimoi.nomore.domain.Cart;

import jakarta.transaction.Transactional;

public interface CartRepository extends JpaRepository<Cart, String> {
    @Transactional
    void deleteAllByCartUserId(String cartUserId);
 
}
