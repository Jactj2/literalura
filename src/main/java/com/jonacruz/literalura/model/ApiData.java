package com.jonacruz.literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApiData(
        Integer count,
        List<BookData> results
) {
}
