package com.jonacruz.literalura.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PersonsData(
        @JsonAlias("birth_year") int birthYear,
        @JsonAlias("death_year") int deathYear,
        String name
) {
}
