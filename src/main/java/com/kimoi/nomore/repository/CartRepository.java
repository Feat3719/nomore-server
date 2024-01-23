package com.kimoi.nomore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kimoi.nomore.domain.Cart;
import com.kimoi.nomore.domain.embedded.CartId;

import jakarta.transaction.Transactional;

public interface CartRepository extends JpaRepository<Cart, CartId> {
    @Transactional
    void deleteAllByUser_UserId(String userId);
}
