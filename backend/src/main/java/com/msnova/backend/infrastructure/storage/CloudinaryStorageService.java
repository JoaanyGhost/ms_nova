package com.msnova.backend.infrastructure.storage;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.msnova.backend.application.exception.products.ImageUploadException;
import com.msnova.backend.domain.entity.images.UploadedImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryStorageService {

    private static final Logger logger =
            LoggerFactory.getLogger(CloudinaryStorageService.class);

    private final Cloudinary cloudinary;

    public CloudinaryStorageService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public UploadedImage uploadImage(
            MultipartFile file,
            String productId,
            String nameImage
    ) {

        try {

            Map<?, ?> result = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap(
                            "folder",
                            "ms-nova/products/" + productId,
                            "public_id",
                            nameImage
                    )
            );

            return new UploadedImage(
                    result.get("secure_url").toString(),
                    result.get("public_id").toString()
            );

        } catch (IOException | RuntimeException exception) {

            logger.error(
                    "Failed to upload image for product {}",
                    productId,
                    exception
            );

            throw new ImageUploadException(
                    "Failed to upload image for product: " + productId,
                    exception
            );
        }
    }


    public void deleteImage(String publicId) {
        try {
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            logger.info("Successfully deleted image with public ID: {}", publicId);
        } catch (IOException | RuntimeException exception) {
            logger.error(
                    "Failed to delete image with public ID: {}",
                    publicId,
                    exception
            );
            throw new ImageUploadException(
                    "Failed to delete image with public ID: " + publicId,
                    exception
            );
        }
    }
}