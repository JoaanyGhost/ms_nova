package com.msnova.backend.infrastructure.persistence.mongodb.users;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.bson.types.ObjectId;

public interface MongoUserRepositorySpring
        extends MongoRepository<UserDocument, ObjectId> {

    Optional<UserDocument> findByEmail(String email);
}