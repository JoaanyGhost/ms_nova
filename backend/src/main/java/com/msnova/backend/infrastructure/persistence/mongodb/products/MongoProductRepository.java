package com.msnova.backend.infrastructure.persistence.mongodb.products;

import com.msnova.backend.domain.entity.product.Product;
import com.msnova.backend.domain.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MongoProductRepository implements ProductRepository {

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
        // convertir Producto → documento Mongo
        ProductDocument doc = new ProductDocument();
        doc.setName(product.getName());
        doc.setDescription(product.getDescription());
        doc.setPrice(product.getPrice());
        doc.setCategoryId(product.getCategoryId());
        doc.setImages(product.getImages());
        doc.setVariants(product.getVariants());
        doc.setActive(product.isActive());
        doc.setCreatedAt(product.getCreatedAt());

        // guardar en MongoDB (Mongo genera el _id automáticamente)
        ProductDocument saved = repository.save(doc);

        // convertir documento → Producto
        return new Product(
                saved.getId().toString(),  // ObjectId → String
                saved.getName(),
                saved.getDescription(),
                saved.getPrice(),
                saved.getCategoryId(),
                saved.getImages(),
                saved.getVariants(),
                saved.isActive(),
                saved.getCreatedAt()
                );
    }

    @Override
    public Optional<Product> findById(String id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(String id) {

    }
}
