package com.jonacruz.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookData(
        String title,
        List<String> subjects,
        List<PersonsData> authors,
        List<String> languages,
        @JsonAlias("download_count") int downloadCount
) {

    @Override
    public String toString() {
        return String.format( """
                ======== Book ========
                Titulo: %s
                Autor : %s
                Idioma: %s
                Numero de descargas: %d
                """,title,authors.get(0).name(),languages,downloadCount);
    }
}
