package com.example.Cadastro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<com.example.Cadastro.models.User, UUID> {
    Optional<com.example.Cadastro.models.User> findByName(String name);
}