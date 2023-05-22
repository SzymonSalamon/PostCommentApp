package com.example.demo.repositories;

import com.example.demo.models.CustomUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<CustomUserEntity, Long> {
    Optional<CustomUserEntity> findByUsername(String username);
    boolean existsByUsername(String username);
}

