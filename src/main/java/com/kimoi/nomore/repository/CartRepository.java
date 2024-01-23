package com.kimoi.nomore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kimoi.nomore.domain.Cart;
import com.kimoi.nomore.domain.embedded.CartId;

import jakarta.transaction.Transactional;

public interface CartRepository extends JpaRepository<Cart, CartId> {
    @Transactional
    boolean deleteAllByUser_UserId(String userId);

    @Transactional
    int deleteByCartId(CartId cartId);

    Optional<List<Cart>> findOptionalAllByCartId_CartUserId(String cartUserId);

    List<Cart> findAllByCartId_CartUserId(String cartUserId);

}
