package com.something.rest.controllers;

import com.something.rest.services.ImageService;
import com.something.rest.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {

    private final ImageService imageService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
        String filePath = imageService.saveImage(id, file);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Image uploaded successfully: " + filePath, null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getImage(@PathVariable Long id) throws MalformedURLException {
            return imageService.getImage(id);
    }
}
