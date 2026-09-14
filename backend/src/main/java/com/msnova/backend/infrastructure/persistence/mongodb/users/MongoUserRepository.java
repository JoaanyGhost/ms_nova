package com.msnova.backend.infrastructure.persistence.mongodb.users;

import com.msnova.backend.domain.entity.user.User;
import com.msnova.backend.domain.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MongoUserRepository implements UserRepository {

    private final MongoUserRepositorySpring repository;

    public MongoUserRepository(
            MongoUserRepositorySpring repository
    ) {
        this.repository = repository;
    }

    @Override
    public Optional<User> findByEmail(String email) {

        return repository.findByEmail(email)
                .map(document -> new User(
                        document.getId().toString(),
                        document.getEmail(),
                        document.getPassword(),
                        document.getRole(),
                        document.isActive()
                ));
    }
}