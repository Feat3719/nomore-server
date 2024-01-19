package com.kimoi.nomore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kimoi.nomore.domain.user.User;

public interface UserRepository extends JpaRepository<User, String>{
    
}
