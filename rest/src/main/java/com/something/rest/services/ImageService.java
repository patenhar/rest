package com.something.rest.services;

import com.something.rest.entities.Employee;
import com.something.rest.repos.EmployeeRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ImageService implements IImageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    private final EmployeeRepo employeeRepo;

    public ImageService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public String saveImage(Long id, MultipartFile file) throws IOException {
        Employee e = employeeRepo.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = e.getId() + '_' + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        e.setImagePath(filePath.toString());
        employeeRepo.save(e);

        return filePath.toString();
    }

    @Override
    public ResponseEntity<Resource> getImage(Long id) throws MalformedURLException {
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));

        if (employee.getImagePath() == null)
            return ResponseEntity.notFound().build();

        Path path = Paths.get(employee.getImagePath());
        Resource resource = new UrlResource(path.toUri());
        if (!resource.exists())
            return ResponseEntity.notFound().build();

        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl("public, max-age=3600");
        headers.setContentType(MediaType.IMAGE_JPEG);

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }
}
