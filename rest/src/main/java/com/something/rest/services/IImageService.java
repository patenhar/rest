package com.something.rest.services;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

public interface IImageService {
    String saveImage(Long id, MultipartFile file) throws IOException;

    ResponseEntity<Resource> getImage(Long id) throws MalformedURLException;
}

