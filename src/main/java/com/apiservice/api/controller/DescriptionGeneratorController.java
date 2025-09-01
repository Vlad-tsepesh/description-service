package com.apiservice.api.controller;

import com.apiservice.application.DescriptionGeneratorService;
import com.apiservice.domain.ImageDescription;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class DescriptionGeneratorController {

    private final DescriptionGeneratorService service;

    @PostMapping("/generate-description")
    public ResponseEntity<ImageDescription> getDescription(@RequestParam("image") MultipartFile imageFile) throws JsonProcessingException {
        Resource image = imageFile.getResource();
        return ResponseEntity.ok(service.generateDescription(image));
    }
}


