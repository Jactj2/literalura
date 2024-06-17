package com.jonacruz.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "books_data")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column(unique = true)
    private String title;
    private List<String> subjects;
    private List<String> languages;
    private Integer downloadCount;

    @ManyToOne
    private PersonModel author;

    public Books() {
    }
    public Books(BookData data) {
    this.title = data.title();
    this.subjects = data.subjects();
    this.languages = data.languages();
    this.downloadCount = data.downloadCount();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public PersonModel getAuthor() {
        return author;
    }

    public void setAuthor(PersonModel author) {
       this.author = author;
    }

    @Override
    public String toString() {
        return String.format( """
                ========= Book =========
                Titulo: %s
                Autor : %s
                Idioma: %s
                Numero de descargas: %d
                """,title,author.getName(),languages,downloadCount);
    }
}
