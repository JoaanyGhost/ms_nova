package com.msnova.backend.presentation.controller;

import com.msnova.backend.application.usecase.product.CreateProductUseCase;
import com.msnova.backend.application.usecase.product.ListProductsUseCase;
import com.msnova.backend.infrastructure.persistence.mongodb.products.MongoProductRepositorySpring;
import com.msnova.backend.infrastructure.persistence.mongodb.products.ProductDocument;
import com.msnova.backend.presentation.dto.products.CreateProductRequest;
import com.msnova.backend.presentation.dto.products.ProductResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final ListProductsUseCase listProductsUseCase;

    public ProductController(CreateProductUseCase createProductUseCase,
                              ListProductsUseCase listProductsUseCase,
                              MongoProductRepositorySpring repository) {
        this.createProductUseCase = createProductUseCase;
        this.listProductsUseCase = listProductsUseCase;
    }

    @PostMapping(
            value = "/save",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ProductResponse createProduct(
            @RequestPart("product") CreateProductRequest request,
            @RequestPart("images") List<MultipartFile> images) {

        return createProductUseCase.execute(request, images);
    }


    @GetMapping
    public List<ProductResponse> getAllProducts() {
        List<ProductResponse> responses = listProductsUseCase.execute()
                .stream()
                .map(ProductResponse::fromDomain)
                .toList();

        System.out.println(">>> Controller devuelve " + responses.size() + " productos al frontend");
        return responses;
    }


}
