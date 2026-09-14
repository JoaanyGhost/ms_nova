package com.msnova.backend.application.usecase.product;

import com.msnova.backend.application.exception.products.ProductCreationException;
import com.msnova.backend.domain.entity.images.UploadedImage;
import com.msnova.backend.domain.entity.product.Product;
import com.msnova.backend.domain.repository.ProductRepository;
import com.msnova.backend.infrastructure.storage.CloudinaryStorageService;
import com.msnova.backend.presentation.dto.products.CreateProductRequest;
import com.msnova.backend.presentation.dto.products.ProductResponse;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Objects;

@Service
public class CreateProductUseCase {

    private final ProductRepository productRepository;
    private final CloudinaryStorageService cloudinaryStorageService;
    private static final Logger logger =
            LoggerFactory.getLogger(CloudinaryStorageService.class);

    public CreateProductUseCase(ProductRepository productRepository, CloudinaryStorageService cloudinaryStorageService) {
        this.productRepository = productRepository;
        this.cloudinaryStorageService = cloudinaryStorageService;
    }

    // Example of a use case method that creates a product
    public ProductResponse execute(CreateProductRequest request, List<MultipartFile> images) {
        //TODO: MAPEAR MEJOR EL MENSAJE DE ERROR
        if (request.getPrice() <= 0) {
            throw new IllegalArgumentException(
                    "El precio debe ser mayor a 0"
            );
        }

        List<UploadedImage> imageUrls = new java.util.ArrayList<>();
        String productId = new ObjectId().toHexString();
        System.out.println(">>> Se creo el id: " + productId);

        try{
            for (MultipartFile image : images) {
                String originalName = Objects.requireNonNull(
                        image.getOriginalFilename()
                );
                String fileName = originalName.replace(" ", "_");

                int extensionIndex = fileName.lastIndexOf(".");
                String name = extensionIndex > 0
                        ? fileName.substring(0, extensionIndex)
                        : fileName;

                String extension = extensionIndex > 0
                        ? fileName.substring(extensionIndex)
                        : "";

                String nameImage = name + "_" + productId + extension;


                UploadedImage imageUrl = cloudinaryStorageService.uploadImage(
                        image,
                        productId,
                        nameImage
                );
                System.out.println(">>> Se subió la imagen: " + nameImage);
                imageUrls.add(imageUrl);
            }

            Product product = new Product(
                    productId,
                    request.getName(),
                    request.getDescription(),
                    request.getPrice(),
                    request.getCategoryId(),
                    imageUrls,
                    request.getVariants(),
                    true,
                    java.time.LocalDateTime.now()
            );
            System.out.println(">>> UseCase recibió producto: " + product.getName() + " con precio: " + product.getPrice());


            Product savedProduct = productRepository.save(product);

            return ProductResponse.fromDomain(savedProduct);
        }catch (Exception exception) {

            // Si algo falla, limpiar Cloudinary
            cleanupImages(imageUrls);

            //TODO: MAPEAR MEJOR EL MENSAJE DE ERROR
            throw new ProductCreationException(
                    "Failed to create product",
                    exception
            );
        }
    }

    private void cleanupImages(List<UploadedImage> uploadedImages) {

        for (UploadedImage image : uploadedImages) {

            try {
                cloudinaryStorageService.deleteImage(
                        image.getPublicId()
                );
            } catch (Exception exception) {

                logger.error(
                        "Failed to cleanup Cloudinary image: {}",
                        image.getPublicId(),
                        exception
                );
            }
        }
    }
}
