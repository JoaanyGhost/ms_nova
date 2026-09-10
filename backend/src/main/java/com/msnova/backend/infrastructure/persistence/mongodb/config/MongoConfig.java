package com.msnova.backend.infrastructure.persistence.mongodb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

@Configuration
public class MongoConfig {

    @Bean
    public MappingMongoConverter mappingMongoConverter(
            org.springframework.data.mongodb.MongoDatabaseFactory mongoDatabaseFactory,
            org.springframework.data.mongodb.core.mapping.MongoMappingContext mongoMappingContext) {

        MappingMongoConverter converter = new MappingMongoConverter(
                mongoDatabaseFactory,
                mongoMappingContext
        );

        converter.setTypeMapper(new DefaultMongoTypeMapper(null));

        return converter;
    }
}