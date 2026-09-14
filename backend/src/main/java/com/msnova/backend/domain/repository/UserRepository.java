package com.msnova.backend.domain.repository;

import com.msnova.backend.domain.entity.user.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByEmail(String email);
}