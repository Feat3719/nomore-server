package com.kimoi.nomore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kimoi.nomore.domain.User;

public interface UserRepository extends JpaRepository<User, String>{
    Optional<User> findByUserId(String userId);
    Optional<User> findByUserEmail(String userEmail);
    boolean existsByUserId(String userId);
}
