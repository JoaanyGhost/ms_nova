package com.msnova.backend.application.usecase.product;

import com.msnova.backend.domain.entity.product.Product;
import com.msnova.backend.domain.repository.ProductRepository;
import com.msnova.backend.presentation.dto.products.CreateProductRequest;
import com.msnova.backend.presentation.dto.products.ProductResponse;
import org.springframework.stereotype.Service;

@Service
public class CreateProductUseCase {

    private final ProductRepository productRepository;

    public CreateProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Example of a use case method that creates a product
    public ProductResponse execute(CreateProductRequest request) {

        Product product = new Product(
                null,
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                request.getCategoryId(),
                request.getImages(),
                request.getVariants(),
                request.isActive(),
                request.getCreatedAt()
        );
        System.out.println(">>> UseCase recibió producto: " + product.getName() + " con precio: " + product.getPrice());
        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException(
                    "El precio debe ser mayor a 0"
            );
        }
        Product savedProduct = productRepository.save(product);

        return ProductResponse.fromDomain(savedProduct);
    }
}
