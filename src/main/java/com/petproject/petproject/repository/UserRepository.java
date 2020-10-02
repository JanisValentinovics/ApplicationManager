package com.petproject.petproject.repository;

import com.petproject.petproject.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
