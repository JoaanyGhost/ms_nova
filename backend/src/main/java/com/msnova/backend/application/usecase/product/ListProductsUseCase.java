package com.msnova.backend.application.usecase.product;

import com.msnova.backend.domain.entity.product.Product;
import com.msnova.backend.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListProductsUseCase {

    private final ProductRepository repository;

    // Inyección de dependencias por constructor
    public ListProductsUseCase(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> execute() {
        // Coordinamos la operación de listar productos
        // No hay lógica de negocio aquí, solo delegamos al repositorio
        List<Product> products = repository.findAll();
        System.out.println(">>> UseCase recibió " + products.size() + " productos");
        return products;
    }
}
