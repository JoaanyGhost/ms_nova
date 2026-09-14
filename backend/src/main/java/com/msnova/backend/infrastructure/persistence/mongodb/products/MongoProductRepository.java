package com.msnova.backend.infrastructure.persistence.mongodb.products;

import com.msnova.backend.application.exception.products.ProductCreationException;
import com.msnova.backend.domain.entity.product.Product;
import com.msnova.backend.domain.repository.ProductRepository;
import org.bson.types.ObjectId;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Repository
public class MongoProductRepository implements ProductRepository {

    private static final Logger logger =
            LoggerFactory.getLogger(MongoProductRepository.class);
    private final MongoProductRepositorySpring repository;

    public MongoProductRepository(MongoProductRepositorySpring repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = repository.findAll()
                .stream()
                .map(doc -> new Product(
                        doc.getId().toString(),   // getter convierte ObjectId → String
                        doc.getName(),
                        doc.getDescription(),
                        doc.getPrice(),
                        doc.getCategoryId(),
                        doc.getImages(),
                        doc.getVariants(),
                        doc.isActive(),
                        doc.getCreatedAt()
                ))
                .toList();

        System.out.println(">>> Mongo devolvió " + products.size() + " productos");
        return products;
    }


    @Override
    public Product save(Product product) {

        try {
            if (!ObjectId.isValid(product.getId())) {
                throw new IllegalArgumentException(
                        "Invalid product ID: " + product.getId()
                );
            }
            // Convertir Product → ProductDocument
            ProductDocument doc = createDoc(product);

            // Guardar en MongoDB
            ProductDocument saved = repository.save(doc);

            // Convertir ProductDocument → Product
            return new Product(
                    saved.getId().toString(),
                    saved.getName(),
                    saved.getDescription(),
                    saved.getPrice(),
                    saved.getCategoryId(),
                    saved.getImages(),
                    saved.getVariants(),
                    saved.isActive(),
                    saved.getCreatedAt()
            );

        } catch (RuntimeException exception) {

            logger.error(
                    "Failed to save product: {}",
                    product.getName(),
                    exception
            );

            throw new ProductCreationException(
                    "Failed to save product",
                    exception
            );
        }
    }

    private static @NonNull ProductDocument createDoc(Product product) {
        ProductDocument doc = new ProductDocument();
        doc.setId(new ObjectId(product.getId()));
        doc.setName(product.getName());
        doc.setDescription(product.getDescription());
        doc.setPrice(product.getPrice());
        doc.setCategoryId(product.getCategoryId());
        doc.setImages(product.getImages());
        doc.setVariants(product.getVariants());
        doc.setActive(product.isActive());
        doc.setCreatedAt(product.getCreatedAt());
        return doc;
    }

    @Override
    public Optional<Product> findById(String id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(String id) {

    }
}
