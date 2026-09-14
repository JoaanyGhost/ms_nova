package com.msnova.backend.presentation.controller;

import com.msnova.backend.domain.entity.images.UploadedImage;
import com.msnova.backend.infrastructure.storage.CloudinaryStorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/test/cloudinary")
public class CloudinaryTestController {

    private final CloudinaryStorageService cloudinaryStorageService;

    public CloudinaryTestController(
            CloudinaryStorageService cloudinaryStorageService
    ) {
        this.cloudinaryStorageService = cloudinaryStorageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadImage(
            @RequestParam("image") MultipartFile image,
            @RequestParam("productId") String productId
    ) throws IOException {

        UploadedImage imageUrl = cloudinaryStorageService.uploadImage(
                image,
                productId,
                "PRUEBA_NOMBRE_" + productId // TODO: Cambiar el nombre de la imagen a algo más personalizado,
        );

        return ResponseEntity.ok(
                Map.of("url", imageUrl.getUrl())
        );
    }
}