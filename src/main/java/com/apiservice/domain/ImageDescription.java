package com.apiservice.domain;

import java.util.List;

public record ImageDescription(
    String title,
    String description,
    List<String> keywords
) {}
