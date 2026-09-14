package com.msnova.backend.presentation.dto.products;

import com.msnova.backend.domain.entity.images.UploadedImage;
import com.msnova.backend.domain.entity.product.Product;
import com.msnova.backend.domain.entity.product.ProductVariant;

import java.time.LocalDateTime;
import java.util.List;

public class ProductResponse {

    private String id;
    private String name;
    private String description;
    private double price;
    private String categoryId;
    private List<UploadedImage> images;
    private List<ProductVariant> variants;
    private boolean active;
    private LocalDateTime createdAt;

    public ProductResponse(String id, String name, String description, double price, String categoryId, List<UploadedImage> images, List<ProductVariant> variants, boolean active, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
        this.images = images;
        this.variants = variants;
        this.active = active;
        this.createdAt = createdAt;
    }

    // Método estático para transformar un Product (dominio) en ProductResponse (DTO)
    public static ProductResponse fromDomain(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategoryId(),
                product.getImages(),
                product.getVariants(),
                product.isActive(),
                product.getCreatedAt()
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public List<UploadedImage> getImages() {
        return images;
    }

    public void setImages(List<UploadedImage> images) {
        this.images = images;
    }

    public List<ProductVariant> getVariants() {
        return variants;
    }

    public void setVariants(List<ProductVariant> variants) {
        this.variants = variants;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
