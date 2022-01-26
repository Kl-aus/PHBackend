package com.ph.phbackend.controllers;

import com.ph.phbackend.payload.request.ImageRequest;
import com.ph.phbackend.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/files")
public class ImageController {
    private static final String UPLOADED_FOLDER = "/home/phBackend/images";
    private static final String UPLOADED_FOLDER_WIN = "C:\\Users\\Klaus\\Desktop\\images\\";

    @Autowired
    ImageService imageService;

    @PostMapping("/saveImg")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<?> saveImages(@ModelAttribute ImageRequest imageRequest) throws IOException {
        return ResponseEntity.ok(imageService.saveImage(imageRequest));
    }

    @GetMapping(value = "/getImg", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> getImages(@Valid String filename) throws IOException {
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageService.getImages(filename));
    }
}
