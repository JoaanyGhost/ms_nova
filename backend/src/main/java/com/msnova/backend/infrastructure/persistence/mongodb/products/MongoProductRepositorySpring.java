package com.msnova.backend.infrastructure.persistence.mongodb.products;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoProductRepositorySpring extends MongoRepository<ProductDocument, String> {}
