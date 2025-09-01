package com.apiservice.application;

import com.apiservice.domain.ImageDescription;
import com.apiservice.infrastructure.OpenAiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DescriptionGeneratorService {

    private final OpenAiService service;

    public ImageDescription generateDescription(Resource image) throws JsonProcessingException {
        return service.generateDescription(image);
    }
}
